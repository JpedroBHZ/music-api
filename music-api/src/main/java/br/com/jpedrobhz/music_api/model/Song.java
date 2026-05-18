package br.com.jpedrobhz.music_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único gerado automaticamente pelo banco

    @Column(nullable = false)
    private String title; // Título da música, obrigatório

    @Column(nullable = false)
    private String artist; // Nome do artista ou banda, obrigatório

    private String album; // Nome do álbum (opcional)

    private int releaseYear; // Ano de lançamento

    // Construtor padrão necessário para o JPA
    public Song() {
    }

    // Construtor cheio para facilitar a criação de objetos
    public Song(String title, String artist, String album, int releaseYear) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.releaseYear = releaseYear;
    }

    // Getters e Setters (Padrão para encapsulamento)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}