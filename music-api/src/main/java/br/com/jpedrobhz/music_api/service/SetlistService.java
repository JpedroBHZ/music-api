package br.com.jpedrobhz.music_api.service;

import br.com.jpedrobhz.music_api.dto.SetlistRequestDTO;
import br.com.jpedrobhz.music_api.dto.SetlistResponseDTO;
import br.com.jpedrobhz.music_api.dto.SongResponseDTO;
import br.com.jpedrobhz.music_api.model.Setlist;
import br.com.jpedrobhz.music_api.model.Song;
import br.com.jpedrobhz.music_api.repository.SetlistRepository;
import br.com.jpedrobhz.music_api.repository.SongRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetlistService {

    //Precisamos dos dois repositórios para fazer a mágica do relacionamento acontecer
    private final SetlistRepository setlistRepository;
    private final SongRepository songRepository;

    //Construtor para o Spring injetar os componentes automaticamente
    public SetlistService(SetlistRepository setlistRepository, SongRepository songRepository) {
        this.setlistRepository = setlistRepository;
        this.songRepository = songRepository;
    }

    @Transactional
    public SetlistResponseDTO saveSetlist(SetlistRequestDTO dto){
        //Passo 1: Instanciar o nosso modelo Setlist e carregar os dados básico do DTO
        Setlist setlist = new Setlist();
        setlist.setName(dto.getName());
        setlist.setEventDate(dto.getEventDate());

        //Passo 2: Se o cliente enviou IDs de músicas, vamos buscá-las no banco
        if (dto.getSongIds() != null && !dto.getSongIds().isEmpty()) {
            for(Long songId : dto.getSongIds()) {
                //Buscamos a música. Se o ID não existir, lançamos um erro usando a nossa estrutura de exceptions!
                Song song = songRepository.findById(songId)
                        .orElseThrow(()-> new RuntimeException("Música com ID" + songId + " não encontrada."));

                //Adicionamos a música encontrada na lista do nosso setlist
                setlist.getSongs().add(song);
            }
        }

        //Passo 3: Salvamos o Setlist completo no banco (o Hibernate vai preencher a tabela tb_setlist_song sozinho)
        Setlist savedSetlist = setlistRepository.save(setlist);

        //Passo 4: Converter o resultado para o DTO da saída
        //Primeiro, convertemos a lista de entidades 'Song' para uma lista de 'SongResponseDTO'
        List<SongResponseDTO> songDTOs = savedSetlist.getSongs().stream()
                .map(song -> new SongResponseDTO(
                        song.getId(),
                        song.getTitle(),
                        song.getArtist(),
                        song.getAlbum(),
                        song.getReleaseYear()
                ))
                .collect(Collectors.toList());

        //Por fim, montamos e retornamos o DTO completo do Setlist
        return new SetlistResponseDTO(
                savedSetlist.getId(),
                savedSetlist.getName(),
                savedSetlist.getEventDate(),
                songDTOs
        );
    }
}
