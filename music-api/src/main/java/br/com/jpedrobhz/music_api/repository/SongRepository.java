package br.com.jpedrobhz.music_api.repository;

import br.com.jpedrobhz.music_api.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica ao Spring que esta interface cuida do acesso aos dados
public interface SongRepository extends JpaRepository<Song, Long> {
    // Song: É a entidade que ela gerencia
    // Long: É o tipo do ID daquela entidade
}