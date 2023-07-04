package com.personal.barbearia.dtos.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Getter
@Setter
public class ProfissionalDtoResponse extends RepresentationModel<ProfissionalDtoResponse> {

    private UUID id;

    private String nome;

    private String telefone;

    private String especialidade;
}
