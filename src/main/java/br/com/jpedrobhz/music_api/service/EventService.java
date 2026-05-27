package br.com.jpedrobhz.music_api.service;

import br.com.jpedrobhz.music_api.dto.EventRequestDTO;
import br.com.jpedrobhz.music_api.dto.EventResponseDTO;
import br.com.jpedrobhz.music_api.model.Event;
import br.com.jpedrobhz.music_api.model.Setlist;
import br.com.jpedrobhz.music_api.repository.EventRepository;
import br.com.jpedrobhz.music_api.repository.SetlistRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Crie a classe dentro do pacote service.
// Aqui aplicamos a lógica de buscar a setlist e fazer o vínculo orquestrado do @OneToOne
@Service
public class EventService {

    private final EventRepository eventRepository;
    private final SetlistRepository setlistRepository; // Injetado para buscar a setlist existente

    // Injeção de dependência via construtor (Boa prática padrão de mercado)
    public EventService(EventRepository eventRepository, SetlistRepository setlistRepository) {
        this.eventRepository = eventRepository;
        this.setlistRepository = setlistRepository;
    }

    @Transactional
    public EventResponseDTO create(EventRequestDTO dto) {
        // 1. Busca a setlist que será associada ao evento. Se não achar, joga a exceção que seu Handler Global já captura.
        Setlist setlist = setlistRepository.findById(dto.setlistId())
                .orElseThrow(() -> new EntityNotFoundException("Setlist not found with ID: " + dto.setlistId()));

        // 2. Instancia a nova entidade Event e injeta os dados do DTO e a Setlist encontrada
        Event event = new Event();
        event.setTitle(dto.title());
        event.setLocation(dto.location());
        event.setDate(dto.date());
        event.setSetlist(setlist); // Estabelece o vínculo do relacionamento

        // 3. Salva no banco. O JPA vai gerar a Foreign Key (setlist_id) na tabela tb_event automaticamente.
        event = eventRepository.save(event);

        // 4. Retorna a resposta convertida em DTO
        return new EventResponseDTO(event);
    }

    @Transactional(readOnly = true)
    public Page<EventResponseDTO> findAll(Pageable pageable) {
        // Busca os dados paginados e faz um map usando o construtor compacto do DTO
        return eventRepository.findAll(pageable).map(EventResponseDTO::new);
    }

    @Transactional
    public void delete(Long id) {
        // Verifica se o evento existe antes de deletar
        if (!eventRepository.existsById(id)) {
            throw new EntityNotFoundException("Event not found with ID: " + id);
        }

        /* Devido ao CascadeType.ALL e orphanRemoval configurados na entidade,
          ao deletar o Evento, a Setlist associada também será removida automaticamente do banco,
          porém as músicas que pertencem à setlist permanecerão salvas intactas.
        */
        eventRepository.deleteById(id);
    }
}