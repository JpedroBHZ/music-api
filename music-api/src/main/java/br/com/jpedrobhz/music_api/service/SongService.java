package br.com.jpedrobhz.music_api.service;

import br.com.jpedrobhz.music_api.model.Song;
import br.com.jpedrobhz.music_api.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Registra esta classe como um componente de serviço no ecossistema do Spring
public class SongService {

    @Autowired // Injeção de dependência automática do Spring para o Repository
    private SongRepository songRepository;

    // Método para listar todas as músicas
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    // Método para buscar uma música por ID específico
    public Optional<Song> getSongById(Long id) {
        return songRepository.findById(id);
    }

    // Método para salvar uma nova música no banco
    public Song saveSong(Song song) {
        return songRepository.save(song);
    }

    // Método para deletar uma música através do ID
    public boolean deleteSong(Long id) {
        if (songRepository.existsById(id)) {
            songRepository.deleteById(id);
            return true; // Retorna verdadeiro se a exclusão deu certo
        }
        return false; // Retorna falso se a música não existia
    }

    // Método para atualizar uma música existente
    public Optional<Song> updateSong(Long id, Song updatedSong) {
        // Buscamos a música no banco pelo ID
        return songRepository.findById(id).map(existingSong -> {
            // Se existir, atualizamos os campos com os novos dados que vieram na requisição
            existingSong.setTitle(updatedSong.getTitle());
            existingSong.setArtist(updatedSong.getArtist());
            existingSong.setAlbum(updatedSong.getAlbum());
            existingSong.setReleaseYear(updatedSong.getReleaseYear());

            // Salvamos as alterações de volta no MySQL
            return songRepository.save(existingSong);
        });
    }
}
