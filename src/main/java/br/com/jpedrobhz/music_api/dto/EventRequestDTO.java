package br.com.jpedrobhz.music_api.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

//Este DTO captura os dados enviados pelo cliente para criar um evento.
// Note que ele já recebe o ID da Setlist que será associada a ele.
public record EventRequestDTO(
        @NotBlank(message = "The event title is required.")
        String title,

        @NotBlank(message = "The event location is required.")
        String location,

        @NotNull(message = "The event date is required.")
        @FutureOrPresent(message = "The event date must be today or in the future.")
        LocalDate date,

        @NotNull(message = "A setlist ID must be assigned to this event.")
        Long setlistId
) {}