package com.personal.barbearia.dtos.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ReservaDtoResponse extends RepresentationModel<ReservaDtoResponse> {

    private UUID id;

    private ClienteDtoResponse cliente;

    private LocalDate dataReserva;

    private LocalTime horaReserva;

    private List<ServicoDtoResponse> servicos;

    private ProfissionalDtoResponse profissional;

    private String descricao;
}
