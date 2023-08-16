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
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_PROFISSIONAIS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

//hibernate faz um soft delete, apenas alterando o status do objeto
@SQLDelete(sql = "UPDATE tb_profissionais SET status = 'Inativo' WHERE id = ?")
//hibernate verifica toda vez q é feito um select no BD e insere a clausula WHERE para filtrar
@Where(clause = "status = 'Ativo'")
public class Profissional implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 15, unique = true)
    private String telefone;

    @Column(nullable = false, length = 100)
    private String especialidade;

    @Column(nullable = false, length = 10)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ATIVO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profissional that = (Profissional) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(telefone, that.telefone) && Objects.equals(especialidade, that.especialidade) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, telefone, especialidade, status);
    }

    @Override
    public String toString() {
        return "ProfissionalModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", especialidade='" + especialidade + '\'' +
                ", status=" + status +
                '}';
    }
}
