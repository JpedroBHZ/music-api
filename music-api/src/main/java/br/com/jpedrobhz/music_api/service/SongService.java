package br.com.jpedrobhz.music_api.service;

import br.com.jpedrobhz.music_api.dto.SongRequestDTO;
import br.com.jpedrobhz.music_api.dto.SongResponseDTO;
import br.com.jpedrobhz.music_api.model.Song;
import br.com.jpedrobhz.music_api.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SongService {

    //Traz a classe, instancia e gerencia pra ti
    //Nesse caso, pegou a classe abstrata já criada pela interface e deu um start nela
    @Autowired
    private SongRepository songRepository;

    //Esse metodo retorna uma list. O metodo foi criado pela interface
    //Dica o return será sempre uma List<T>, é só parar o mouse no metodo para confirmar os retornos
    //Conveterndo lista de entidades para uma lista de dto
    public List<SongResponseDTO> findAllSongs(){
        List<Song> songs = songRepository.findAll();

        return songs.stream()
                .map(song -> new SongResponseDTO(
                        song.getId(),
                        song.getTitle(),
                        song.getArtist(),
                        song.getAlbum(),
                        song.getReleaseYear()
                ))
                .collect(Collectors.toList());
    }

    //Esse optional, indica que ao buscar o id, pode ou não ser encontrado
    //Evita que caso seja null, retorne um erro ao ser apontado. É um lembrete para ter cuidado com esse dado
    public Optional<Song> getSongById(Long id){
        return songRepository.findById(id);
    }

    // Método de salvar: recebe o RequestDTO e devolve o ResponseDTO
    public SongResponseDTO saveSong(SongRequestDTO dto) {
        Song song = new Song();
        song.setTitle(dto.getTitle());
        song.setArtist(dto.getArtist());
        song.setAlbum(dto.getAlbum());
        song.setReleaseYear(dto.getReleaseYear());

        Song savedSong = songRepository.save(song);

        return new SongResponseDTO(
                savedSong.getId(),
                savedSong.getTitle(),
                savedSong.getArtist(),
                savedSong.getAlbum(),
                savedSong.getReleaseYear()
        );
    }

    //Já que não retorna um metodo, podemos só testar se é null e deletar direto
    public boolean deleteSong(Long id){
        if (songRepository.existsById(id)) {
            songRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //Esse .map é uma função exclusiva do optional, é basicamente uma estrutura condicinal
    //Se a musica existia você pega os dados que vieram da internet "updatesong" e substitui pelos antigos "existingsong"
    public Optional<Song> updateSong(Long id, Song updateSong) {
        return songRepository.findById(id).map(existingSong ->{
            existingSong.setTitle(updateSong.getTitle());
            existingSong.setArtist(updateSong.getArtist());
            existingSong.setAlbum(updateSong.getAlbum());
            existingSong.setReleaseYear(updateSong.getReleaseYear());

            return songRepository.save(existingSong);
        });
    }

}