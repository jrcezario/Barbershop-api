package com.personal.barbearia.dtos;

import com.personal.barbearia.enums.Status;
import jakarta.validation.constraints.NotBlank;

public record ProfissionalDTO(
        Long id,
        @NotBlank(message = "campo obrigatório") String nome,
        @NotBlank(message = "campo obrigatório") String telefone,
        Status status) {
}
