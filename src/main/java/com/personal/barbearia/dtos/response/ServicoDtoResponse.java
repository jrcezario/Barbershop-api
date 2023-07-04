package com.personal.barbearia.dtos.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ServicoDtoResponse extends RepresentationModel<ServicoDtoResponse> {

    private UUID id;

    private String nome;

    private BigDecimal valor;

    private String descricao;
}
