package com.personal.barbearia.models;

import com.personal.barbearia.enums.Status;
import com.personal.barbearia.enums.converters.StatusConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_SERVICOS")
@Data
@NoArgsConstructor
@AllArgsConstructor

//hibernate faz um soft delete, apenas alterando o status do objeto
@SQLDelete(sql = "UPDATE tb_servicos SET status = 'Inativo' WHERE id = ?")
//hibernate verifica toda vez q é feito um select no BD e insere a clausula WHERE para filtrar
@Where(clause = "status = 'Ativo'")
public class Servico implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nomeServico;

    @Column(nullable = false, length = 10)
    private BigDecimal valorServico;

    @Column(nullable = false, length = 130)
    private String descricaoServico;

    @Column(nullable = false, length = 10)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ATIVO;

}
