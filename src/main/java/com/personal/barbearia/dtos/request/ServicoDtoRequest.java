package com.personal.barbearia.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ServicoDtoRequest {

        @NotBlank(message = "campo obrigatório")
        private String nome;

        @NotNull(message = "campo obrigatório")
        private BigDecimal valor;

        @NotBlank(message = "campo obrigatório")
        private String descricao;
}
