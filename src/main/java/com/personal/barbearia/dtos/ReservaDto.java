package com.personal.barbearia.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.personal.barbearia.models.Cliente;
import com.personal.barbearia.models.Profissional;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservaDto(
        @NotNull Cliente cliente,
        @NotNull @JsonFormat(pattern = "dd/MM/yyyy") LocalDate data,
        @NotNull @JsonFormat(pattern = "HH:mm") LocalTime hora,
        @NotNull Profissional profissional) {
}
