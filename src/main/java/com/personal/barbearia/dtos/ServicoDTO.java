package com.personal.barbearia.dtos;

import com.personal.barbearia.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ServicoDTO(
        Long id,
        @NotBlank(message = "campo obrigatório") String nome,
        @NotNull(message = "campo obrigatório") BigDecimal valor,
        @NotBlank(message = "campo obrigatório") String descricao,
        Status status) {
}