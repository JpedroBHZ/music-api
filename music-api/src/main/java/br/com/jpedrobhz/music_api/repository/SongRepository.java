package br.com.jpedrobhz.music_api.repository;

import br.com.jpedrobhz.music_api.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// CONTRATO DO BANCO: Herda o "pacotão" do JpaRepository e o customiza para a entidade Song com ID Long.
// O Spring lerá essa interface e criará, nos bastidores, a classe real com as querys SQL prontas.
@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
}