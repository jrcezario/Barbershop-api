package com.personal.barbearia.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.personal.barbearia.models.Cliente;
import com.personal.barbearia.models.Profissional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record ReservaDTO(
        UUID id,
        @NotNull(message = "campo obrigatório") Cliente cliente,
        @NotNull(message = "campo obrigatório") LocalDate dataReserva,
        @NotNull(message = "campo obrigatório") @JsonFormat(pattern = "HH:mm") LocalTime horaReserva,
        @NotNull(message = "campo obrigatório") Profissional profissional,
        @NotBlank(message = "campo obrigatório") String descricao) {
}
