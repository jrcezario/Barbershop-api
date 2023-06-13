package com.personal.barbearia.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "TB_PROFISSIONAIS")
@Data
public class ProfissionalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    private String telefone;

    private String especialidade;
}
