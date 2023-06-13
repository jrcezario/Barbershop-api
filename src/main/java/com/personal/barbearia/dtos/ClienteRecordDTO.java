package com.personal.barbearia.dtos;

import jakarta.validation.constraints.NotBlank;

public record ClienteRecordDTO(@NotBlank String nome, @NotBlank String telefone) {
}
