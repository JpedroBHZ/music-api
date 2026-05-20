package br.com.jpedrobhz.music_api.controller;

import br.com.jpedrobhz.music_api.model.Song;
import br.com.jpedrobhz.music_api.service.SongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// A RECEPCIONISTA DA API: Não mexe com regras de negócio ou banco.
// Sua única função é traduzir a internet (HTTP/JSON) para o Java e vice-versa.
@RestController
@RequestMapping("/api/songs") // Endereço base da API (Ex: http://localhost:8080/api/songs)
public class SongController {

    // INJEÇÃO DE DEPENDÊNCIA: O Spring traz a nossa "Máquina" de serviços pronta para uso.
    @Autowired
    private SongService songService;

    // GET /api/songs -> Lista todas as músicas
    // ResponseEntity.ok() envelopa a lista e define o status HTTP para 200 OK
    @GetMapping
    public ResponseEntity<List<Song>> getAllSongs() {
        List<Song> songs = songService.getAllSongs();
        return ResponseEntity.ok(songs);
    }

    // GET /api/songs/{id} -> Busca uma música específica pela URL
    // @PathVariable: Captura o número digitado na URL (ex: /songs/5) e injeta na variável "id".
    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable Long id) {
        Optional<Song> song = songService.getSongById(id);

        // O fluxo do Optional: Se a caixinha tiver dados, .map() envia 200 OK com a música em JSON.
        // Se estiver vazia, o .orElseGet() aciona a "receita" preguiçosa e devolve um 404 Not Found limpo.
        return song.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /api/songs -> Cadastra uma música nova
    // @RequestBody: Pega o texto JSON que o usuário enviou no corpo da requisição e transforma em um objeto Song.
    //@valid: Ordena ao spring que valide as regras da classe song antes de executar
    @PostMapping
    public ResponseEntity<Song> createSong(@Valid @RequestBody Song song) {
        Song savedSong = songService.saveSong(song);

        // Retorna o status 201 Created (padrão do mercado para criações com sucesso) com o dado salvo.
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSong);
    }

    // DELETE /api/songs/{id} -> Remove uma música do banco
    // ResponseEntity<Void>: O "Void" indica que o corpo da resposta HTTP irá totalmente vazio.
    // Se foi deletado, devolve o status 204 No Content (Deu certo, mas não há nada para te mostrar)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        boolean deleted = songService.deleteSong(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        // Se a música não existia na checagem da service, devolve 404 Not Found
        return ResponseEntity.notFound().build();
    }

    // PUT /api/songs/{id} -> Atualiza os dados de uma música existente
    // Recebe o ID na URL para saber QUEM atualizar e o @RequestBody com os dados novos.
    // @Valid: Garante que os dados novos da atualização também respeitem as regras de preenchimento
    @PutMapping("/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable Long id, @Valid @RequestBody Song song) {
        Optional<Song> updated = songService.updateSong(id, song);

        // Mesma lógica do GET por ID: se a service achou a música e atualizou, 200 OK com ela modificada.
        // Se a service retornar a caixinha do Optional vazia, devolve 404 Not Found.
        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}