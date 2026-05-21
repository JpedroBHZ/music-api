package br.com.jpedrobhz.music_api.service;

import br.com.jpedrobhz.music_api.dto.SongRequestDTO;
import br.com.jpedrobhz.music_api.dto.SongResponseDTO;
import br.com.jpedrobhz.music_api.model.Song;
import br.com.jpedrobhz.music_api.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    //Conveterndo lista de entidades para uma lista de dto
    // Mudamos o retorno de List para Page, e agora recebemos o objeto Pageable
    public Page<SongResponseDTO> findAllSongsPageable(Pageable pageable) {
        // O Repository busca do banco apenas a "fatia" de músicas que foi pedida
        Page<Song> songsPage = songRepository.findAll(pageable);

        // Convertemos a página de Entidades para uma página de DTOs.
        // objeto Page do Spring já tem um método .map() nativo, não precisa de .stream() e nem de .collect()!
        return songsPage.map(song -> new SongResponseDTO(
                song.getId(),
                song.getTitle(),
                song.getArtist(),
                song.getAlbum(),
                song.getReleaseYear()
        ));
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