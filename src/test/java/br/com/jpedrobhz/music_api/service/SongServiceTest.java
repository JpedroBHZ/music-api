package br.com.jpedrobhz.music_api.service;

import br.com.jpedrobhz.music_api.model.Song;
import br.com.jpedrobhz.music_api.repository.SongRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SongServiceTest {

    @Mock
    private SongRepository songRepository; // Banco de dados "de mentira"

    @InjectMocks
    private SongService songService; // Injeta o repository mockado aqui dentro

    @Test
    @DisplayName("Deve retornar um Optional com a musica quando o ID existir")
    void getSongByIdSuccess() {
        // --- 1. ARRANJAR ---
        Long songId = 1L;
        Song fakeSong = new Song();
        fakeSong.setId(songId);
        fakeSong.setTitle("Fear of the Dark");

        // Ensina o mock: quando o repository for chamado, devolve o Optional com a música fake
        Mockito.when(songRepository.findById(songId)).thenReturn(Optional.of(fakeSong));

        // --- 2. AGIR ---
        Optional<Song> result = songService.getSongById(songId); // Nome correto do seu método!

        // --- 3. ASSERTAR ---
        Assertions.assertTrue(result.isPresent()); // Confere se o Optional não veio vazio
        Assertions.assertEquals("Fear of the Dark", result.get().getTitle()); // Confere o título

        // Verifica se o repositório foi acionado exatamente 1 vez
        Mockito.verify(songRepository, Mockito.times(1)).findById(songId);
    }

    @Test
    @DisplayName("Deve retornar um Optional vazio quando o ID da musica nao existir")
    void getSongByIdNotFound() {
        // --- 1. ARRANJAR ---
        Long invalidId = 999L;

        // Ensina o mock: quando o ID não existir, o banco real devolveria um Optional vazio
        Mockito.when(songRepository.findById(invalidId)).thenReturn(Optional.empty());

        // --- 2. AGIR ---
        Optional<Song> result = songService.getSongById(invalidId);

        // --- 3. ASSERTAR ---
        Assertions.assertTrue(result.isEmpty()); // O JUnit confere se o Optional realmente veio vazio (sem disparar erro)

        Mockito.verify(songRepository, Mockito.times(1)).findById(invalidId);
    }
}