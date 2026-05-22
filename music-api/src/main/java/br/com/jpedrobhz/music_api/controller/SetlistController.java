package br.com.jpedrobhz.music_api.controller;

import br.com.jpedrobhz.music_api.dto.SetlistRequestDTO;
import br.com.jpedrobhz.music_api.dto.SetlistResponseDTO;
import br.com.jpedrobhz.music_api.service.SetlistService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/setlists")
public class SetlistController {

    private final SetlistService setlistService;

    //Injeção de dependencia via construtor
    public SetlistController(SetlistService setlistService) {
        this.setlistService = setlistService;
    }

    @PostMapping
    public ResponseEntity<SetlistResponseDTO> createSetlist(@Valid @RequestBody SetlistRequestDTO setlistRequestDTO) {
        // Chamei a service para rodar a regra de negócio e salvar o show com as músicas
        SetlistResponseDTO responseDTO = setlistService.saveSetlist(setlistRequestDTO);

        // Retornamos o status 201 Created e o JSON do setlist completo com as músicas dentro
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
