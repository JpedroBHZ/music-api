package br.com.jpedrobhz.music_api.repository;

import br.com.jpedrobhz.music_api.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // O JpaRepository já nos dá métodos prontos como save(), findById(), deleteById() e findAll() paginado.
}