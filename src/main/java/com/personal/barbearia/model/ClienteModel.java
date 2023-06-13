package com.personal.barbearia.model;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_CLIENTES")
@Data
public class ClienteModel extends RepresentationModel<ClienteModel> implements Serializable {

    public static  final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    private String telefone;

}
