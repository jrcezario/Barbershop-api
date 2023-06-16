package com.personal.barbearia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public record ServicoRecordDTO(

        @NotBlank(message = "campo obrigatório")
        String nome,

        @NotEmpty(message = "campo obrigatório")
        BigDecimal valor,

        @NotBlank(message = "campo obrigatório")
        String descricao
) {
}
