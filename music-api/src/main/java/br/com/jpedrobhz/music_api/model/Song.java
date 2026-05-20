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

    //Garante que o titulo não venha nulo, vazio(""), ou cheio de espaços
    @NotBlank(message = "O título da música e obrigatório")
    @Column(nullable = false)
    private String title;

    //Garante que o nome do artista seja enviado obrigatoriamente
    @NotBlank(message = "O nome do artista é obrigatório")
    @Column(nullable = false)
    private String artist;

    private String album; // Continua opcional sem validação restritiva

    @Positive(message = "O ano de lançamento deve ser um número positivo válido")
    private int releaseYear;

    // Construtor padrão: Obrigatório para o JPA conseguir ler os dados do banco e converter em Java
    public Song() {
    }

    // Construtor cheio: Facilita a nossa vida na hora de dar "new Song(...)" no código
    public Song(String title, String artist, String album, int releaseYear) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.releaseYear = releaseYear;
    }

    // Getters e Setters: Encapsulamento padrão para ler e modificar os dados de forma segura
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }
    public String getAlbum() { return album; }
    public void setAlbum(String album) { this.album = album; }
    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }

    // equals: Compara o CONTEÚDO de duas músicas (título, artista, etc.) e não a posição delas na memória
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Song song)) return false;
        return getReleaseYear() == song.getReleaseYear() && Objects.equals(getId(), song.getId()) && Objects.equals(getTitle(), song.getTitle()) && Objects.equals(getArtist(), song.getArtist()) && Objects.equals(getAlbum(), song.getAlbum());
    }

    // hashCode: Gera um código numérico único baseado nos dados da música para o Java encontrá-la rápido em coleções
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getArtist(), getAlbum(), getReleaseYear());
    }
}