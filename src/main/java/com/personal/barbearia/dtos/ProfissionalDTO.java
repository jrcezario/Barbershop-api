package com.personal.barbearia.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ProfissionalDTO(
        UUID id,
        @NotBlank(message = "campo obrigatório") String nome,
        @NotBlank(message = "campo obrigatório") String telefone,
        @NotBlank(message = "campo obrigatório") String especialidade) {
}
