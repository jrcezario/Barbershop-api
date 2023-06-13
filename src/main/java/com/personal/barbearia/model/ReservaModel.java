package com.personal.barbearia.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "TB_RESERVAS")
@Data
public class ReservaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private ClienteModel clienteModel;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataReserva;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horaReserva;

    @OneToOne
    @JoinColumn(name = "servico_id")
    private ServicoModel servicoModel;

    @OneToOne
    @JoinColumn(name = "profissional_id")
    private ProfissionalModel profissionalModel;

    private String descricao;
}
