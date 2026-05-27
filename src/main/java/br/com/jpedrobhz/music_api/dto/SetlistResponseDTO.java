package br.com.jpedrobhz.music_api.dto;

import java.util.List;

// DTO de saida para o setlist
// Retorna os dados do show e a lista completa de dtos das músicas vinculadas
public class SetlistResponseDTO {

    private Long id;
    private String name;

    // Reutilizamos o songResponseDTO para entregar os dados das músicas mastigados
    private List<SongResponseDTO> songs;

    public SetlistResponseDTO() {
    }

    public SetlistResponseDTO(Long id, String name, List<SongResponseDTO> songs) {
        this.id = id;
        this.name = name;
        this.songs = songs;
    }

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

    public List<SongResponseDTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongResponseDTO> songs) {
        this.songs = songs;
    }
}