package br.com.jpedrobhz.music_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

//DTO que representa os dados de uma musica trafegando pela internet
//Repare que ele não tem a notação @Entity e nem o campo "id", pois o id e gerado pelo banco
public class SongDTO {

    //As anotacoes de validacao migram do Model para ca, que e a porta de entrada da internet
    @NotBlank(message = "O titulo da musica é obrigatorio")
    private String title;

    @NotBlank(message = "O nome do artista é obrigatório")
    private String artist;

    private String album;

    @Positive(message = "O ano de lançamento deve ser um número positivo válido")
    private int releaseYear;

    //Construtor padrao (obrigatorio para o spring)
    public SongDTO() {}

    //Construtor completo
    public SongDTO(String title, String artist, String album, int releaseYear) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.releaseYear = releaseYear;
    }

    //Getters e Setters
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
