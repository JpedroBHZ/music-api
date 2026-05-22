package br.com.jpedrobhz.music_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

//DTO de entrada para criar/atualizar um setlist
//Recebemos apenas os ids das músicas songIds para fazer a vinculação
public class SetlistRequestDTO {

    @NotBlank(message = "O nome do setlist é obrigatório.")
    private String name;

    @NotNull(message = "A data do evento é obrigatória.")
    private LocalDate eventDate;

    //Recebemos apenas uma liste de IDs ds músicas que farão parte do show
    private List<Long> songIds;

    //Construtores
    public SetlistRequestDTO(){

    }

    public SetlistRequestDTO(String name, LocalDate eventDate, List<Long> songIds) {
        this.name = name;
        this.eventDate = eventDate;
        this.songIds = songIds;
    }

    //Getters e Setters
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

    public List<Long> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<Long> songIds) {
        this.songIds = songIds;
    }
}
