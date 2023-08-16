package com.personal.barbearia.models;

import com.personal.barbearia.enums.Status;
import com.personal.barbearia.enums.converters.StatusConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_SERVICOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//hibernate faz um soft delete, apenas alterando o status do objeto
@SQLDelete(sql = "UPDATE tb_servicos SET status = 'Inativo' WHERE id = ?")
//hibernate verifica toda vez q é feito um select no BD e insere a clausula WHERE para filtrar
@Where(clause = "status = 'Ativo'")
public class Servico implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 10)
    private BigDecimal valor;

    @Column(nullable = false, length = 130)
    private String descricao;

    @Column(nullable = false, length = 10)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ATIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Servico that = (Servico) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(valor, that.valor) && Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, nome, valor, descricao);
    }

    @Override
    public String toString() {
        return "ServicoModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
