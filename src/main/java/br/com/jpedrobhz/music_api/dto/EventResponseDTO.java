package br.com.jpedrobhz.music_api.dto;

import br.com.jpedrobhz.music_api.model.Event;
import java.time.LocalDate;

//Este DTO define o que a API vai devolver para o cliente
// escondendo dados internos do banco e trazendo os dados resumidos da setlist vinculada.
public record EventResponseDTO(
        Long id,
        String title,
        String location,
        LocalDate date,
        Long setlistId,
        String setlistName
) {
    // Construtor compacto para transformar facilmente a Entidade do banco neste DTO
    public EventResponseDTO(Event event) {
        this(
                event.getId(),
                event.getTitle(),
                event.getLocation(),
                event.getDate(),
                event.getSetlist() != null ? event.getSetlist().getId() : null,
                event.getSetlist() != null ? event.getSetlist().getName() : null
        );
    }
}