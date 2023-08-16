package com.personal.barbearia.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ServicoDTO(
        UUID id,
        @NotBlank(message = "campo obrigatório") String nome,
        @NotNull(message = "campo obrigatório") BigDecimal valor,
        @NotBlank(message = "campo obrigatório") String descricao) {
}