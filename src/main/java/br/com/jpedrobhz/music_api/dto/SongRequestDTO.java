package br.com.jpedrobhz.music_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

/**
 * DTO (Data Transfer Object) de Entrada.
 * Usado exclusivamente para receber dados enviados pelo cliente (POST/PUT).
 * Não possui o campo 'id', pois a responsabilidade de gerar a chave primária é do banco de dados.
 */
public class SongRequestDTO {

    // As anotações de validação ficam aqui, blindando a entrada da API
    @NotBlank(message = "O título da música é obrigatório.")
    private String title;

    @NotBlank(message = "O nome do artista é obrigatório.")
    private String artist;

    private String album;

    @Positive(message = "O ano de lançamento deve ser um número positivo válido.")
    private int releaseYear;

    // Construtor padrão obrigatório para o Spring/Jackson deserializar o JSON
    public SongRequestDTO() {
    }

    // Construtor completo para facilitar testes e instanciações manuais
    public SongRequestDTO(String title, String artist, String album, int releaseYear) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.releaseYear = releaseYear;
    }

    // Getters e Setters (Necessários para o Spring ler os dados do JSON)
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