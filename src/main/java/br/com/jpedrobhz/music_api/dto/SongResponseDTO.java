package br.com.jpedrobhz.music_api.dto;

/**
 * DTO (Data Transfer Object) de Saída.
 * Usado para moldar a resposta que enviamos de volta para a internet (GET).
 * Aqui o 'id' é incluído porque o Front-end precisa dele para renderizar botões de ação (Editar/Deletar).
 * Não possui anotações de validação (@NotBlank, etc), pois dados de saída não precisam ser validados.
 */
public class SongResponseDTO {

    private Long id; // Chave exposta de forma segura apenas para leitura
    private String title;
    private String artist;
    private String album;
    private int releaseYear;

    // Construtor padrão necessário para o Spring
    public SongResponseDTO() {
    }

    // Construtor completo incluindo o ID para mapear a Entidade do banco para cá
    public SongResponseDTO(Long id, String title, String artist, String album, int releaseYear) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.releaseYear = releaseYear;
    }

    // Getters e Setters (Necessários para o Spring transformar a classe em JSON)
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