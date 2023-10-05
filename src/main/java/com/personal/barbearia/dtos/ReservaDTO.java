package com.personal.barbearia.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.personal.barbearia.enums.Status;
import com.personal.barbearia.models.Cliente;
import com.personal.barbearia.models.Profissional;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservaDTO(
        Long id,
        @NotNull(message = "campo obrigatório") Cliente cliente,
        @NotNull(message = "campo obrigatório") @JsonFormat(pattern = "dd/MM/yyyy") LocalDate data,
        @NotNull(message = "campo obrigatório") @JsonFormat(pattern = "HH:mm") LocalTime hora,
        @NotNull(message = "campo obrigatório") Profissional profissional,
        Status status) {
}
