package br.com.jpedrobhz.music_api.dto;

/**
 * DTO (Data Transfer Object) de Saída.
 * Aqui o dado apenas sai moldado para o cliente em formato de leitura pura. Sem anotações de validação
 * Usado para moldar a resposta que enviamos de volta para a internet (GET).
 */
public class SongResponseDTO {

    private Long id; // Chave exposta de forma segura apenas para leitura
    private String title;
    private String artist;
    private String album;
    private int releaseYear;
    private int durationInSeconds;

    // Construtor padrão necessário para o Spring
    public SongResponseDTO() {
    }

    // Construtor completo atualizado
    public SongResponseDTO(Long id, String title, String artist, String album, int releaseYear, int durationInSeconds) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.releaseYear = releaseYear;
        this.durationInSeconds = durationInSeconds;
    }

    // Getters e Setters
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

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }
}