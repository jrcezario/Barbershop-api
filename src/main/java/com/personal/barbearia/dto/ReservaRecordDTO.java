package com.personal.barbearia.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record ReservaRecordDTO(

        @NotNull(message = "campo obrigatório")
        UUID clienteId,

        @NotNull(message = "campo obrigatório")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataReserva,

        @NotNull(message = "campo obrigatório")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
        LocalTime horaReserva,

        @NotNull(message = "campo obrigatório")
        UUID servicoId,

        @NotNull(message = "campo obrigatório")
        UUID profissionalId,

        @NotBlank(message = "campo obrigatório")
        String descricao) {
}
