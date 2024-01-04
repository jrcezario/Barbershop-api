package com.personal.barbearia.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteDto(
        @NotBlank @NotNull String nome,
        @NotBlank @NotNull String telefone) {
}
