package br.com.jpedrobhz.music_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

// CLASSE DE DADO: Representa as informações que mudam e têm "prazo de validade" curto.
// Aqui você SEMPRE usará o "new" manualmente para instanciar novos objetos.
@Entity
@Table(name = "tb_songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Chave primária gerada automaticamente pelo MySQL (1, 2, 3...)

    //Removidas as validações e feitas agora na classe songDTO
    private String title;
    private String artist;
    private String album;
    private int releaseYear;
    private Integer durationInSeconds;

    // Construtor padrão: Obrigatório para o JPA conseguir ler os dados do banco e converter em Java
    public Song() {
    }

    // Construtor cheio: Facilita a nossa vida na hora de dar "new Song(...)" no código
    public Song(String title, String artist, String album, int releaseYear, int durationInSeconds) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.releaseYear = releaseYear;
        this.durationInSeconds = durationInSeconds;
    }

    // Getters e Setters: Encapsulamento padrão para ler e modificar os dados de forma segura
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
    public Integer getDurationInSeconds() {return durationInSeconds;}
    public void setDurationInSeconds(Integer durationInSeconds) {this.durationInSeconds = durationInSeconds;}

    // equals: Compara o CONTEÚDO de duas músicas (título, artista, etc.) e não a posição delas na memória
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song song)) return false;
        return getReleaseYear() == song.getReleaseYear() &&
                getDurationInSeconds() == song.getDurationInSeconds() &&
                Objects.equals(getId(), song.getId()) &&
                Objects.equals(getTitle(), song.getTitle()) &&
                Objects.equals(getArtist(), song.getArtist()) &&
                Objects.equals(getAlbum(), song.getAlbum());
    }

    // hashCode: Gera um código numérico único baseado nos dados da música para o Java encontrá-la rápido em coleções
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getArtist(), getAlbum(), getReleaseYear(), getDurationInSeconds());
    }
}