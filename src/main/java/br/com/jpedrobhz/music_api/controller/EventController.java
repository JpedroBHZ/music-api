package br.com.jpedrobhz.music_api.controller;

import br.com.jpedrobhz.music_api.dto.EventRequestDTO;
import br.com.jpedrobhz.music_api.dto.EventResponseDTO;
import br.com.jpedrobhz.music_api.service.EventService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Crie a classe dentro do pacote controller. Ela expõe os endpoints para o Swagger e Postman
@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventResponseDTO> create(@RequestBody @Valid EventRequestDTO dto) {
        EventResponseDTO response = eventService.create(dto);
        // Retorna o HTTP Status 21 Created quando a entidade é salva com sucesso
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<EventResponseDTO>> findAll(
            @PageableDefault(size = 10, sort = "date") Pageable pageable) {
        Page<EventResponseDTO> page = eventService.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventService.delete(id);
        // Retorna o HTTP Status 204 No Content (Deleção feita com sucesso e sem corpo de retorno)
        return ResponseEntity.noContent().build();
    }
}