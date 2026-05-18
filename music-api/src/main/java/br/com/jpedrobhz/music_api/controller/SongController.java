package br.com.jpedrobhz.music_api.controller;

import br.com.jpedrobhz.music_api.model.Song;
import br.com.jpedrobhz.music_api.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Define que esta classe é um controlador REST que lida com requisições HTTP
@RequestMapping("/api/songs") // Define a rota base da URL para este controlador
public class SongController {

    @Autowired // Injeta o serviço de músicas para usarmos suas funções
    private SongService songService;

    // Endpoint para buscar todas as músicas (GET http://localhost:8080/api/songs)
    @GetMapping
    public ResponseEntity<List<Song>> getAllSongs() {
        List<Song> songs = songService.getAllSongs();
        return ResponseEntity.ok(songs); // Retorna a lista com status 200 OK
    }

    // Endpoint para buscar por ID (GET http://localhost:8080/api/songs/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        Optional<Song> song = songService.getSongById(id);

        // Se a música existir, retorna 200 OK com o corpo. Se não, retorna 404 Not Found
        return song.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para criar uma nova música (POST http://localhost:8080/api/songs)
    @PostMapping
    public ResponseEntity<Song> createSong(@RequestBody Song song) {
        Song savedSong = songService.saveSong(song);
        // Retorna status 21 Created junto com a música recém criada no corpo
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSong);
    }

    // Endpoint para deletar uma música (DELETE http://localhost:8080/api/songs/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        boolean deleted = songService.deleteSong(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se foi deletado com sucesso
        }
        return ResponseEntity.notFound().build(); // Retorna 404 se o ID não existia
    }

    // Endpoint para atualizar uma música (PUT http://localhost:8080/api/songs/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable Long id, @RequestBody Song song) {
        Optional<Song> updated = songService.updateSong(id, song);

        // Se a música foi encontrada e atualizada, retorna 200 OK com ela modificada
        // Se o ID não existir no banco, retorna 404 Not Found
        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}