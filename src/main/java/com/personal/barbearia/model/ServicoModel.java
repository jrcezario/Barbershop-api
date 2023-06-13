package com.personal.barbearia.model;

import lombok.Data;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "TB_SERVICOS")
@Data
public class ServicoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    private Double valor;

    private String descricao;
}
