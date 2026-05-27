package br.com.jpedrobhz.music_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;      // Ex: "Tour 2026 - Belo Horizonte"
    private String location;   // Ex: "Sesc Palladium"
    private LocalDate date;    // A data que antes ficava na Setlist

    /* O @OneToOne indica que cada evento tem uma única Setlist.
     O CascadeType.ALL garante que se salvarmos/deletarmos um Evento,
     a Setlist vinculada será salva/deletada junto automaticamente.
     O orphanRemoval = true garante que se a setlist for desvinculada, ela é apagada do banco.
    */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "setlist_id", referencedColumnName = "id")
    private Setlist setlist;

    public Event() {
    }

    public Event(Long id, String title, String location, LocalDate date, Setlist setlist) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.date = date;
        this.setlist = setlist;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Setlist getSetlist() { return setlist; }
    public void setSetlist(Setlist setlist) { this.setlist = setlist; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}