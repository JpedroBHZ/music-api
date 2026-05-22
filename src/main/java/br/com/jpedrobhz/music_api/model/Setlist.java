package br.com.jpedrobhz.music_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_setlist")
public class Setlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate eventDate; // Guarda a data do show (ano, mês e dia)

    // O @ManyToMany indica o relacionamento Muitos para Muitos
    @ManyToMany
    // O @JoinTable configura a tabela de junção que o Spring vai criar no banco
    @JoinTable(
            name = "tb_setlist_song", // Nome da tabela intermediária
            joinColumns = @JoinColumn(name = "setlist_id"), // Nome da coluna que aponta para o Setlist
            inverseJoinColumns = @JoinColumn(name = "song_id") // Nome da coluna que aponta para a Música
    )
    private List<Song> songs = new ArrayList<>(); // Iniciamos a lista vazia para evitar NullPointerException

    // Construtor padrão exigido pelo Hibernate
    public Setlist() {
    }

    // Construtor completo (repare que não passamos a lista de músicas aqui no construtor)
    public Setlist(Long id, String name, LocalDate eventDate) {
        this.id = id;
        this.name = name;
        this.eventDate = eventDate;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public List<Song> getSongs() {
        return songs;
    }

    // No relacionamento muitos para muitos, é melhor não termos um "setSongs" que substitui a lista inteira.
    // Em vez disso, deixamos apenas o getSongs() para podermos fazer .add() ou .remove() diretamente nela.

    // Equals e HashCode baseados apenas no ID para o Hibernate trabalhar bem na memória

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Setlist setlist = (Setlist) o;
        return Objects.equals(id, setlist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}