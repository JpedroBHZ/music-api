package br.com.jpedrobhz.music_api.repository;

import br.com.jpedrobhz.music_api.model.Setlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SetlistRepository extends JpaRepository<Setlist, Long> {
    //Herdando o JpaRepository, já ganhamos o save, findById, findAll, delete, etc.
}
