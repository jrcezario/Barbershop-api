package com.personal.barbearia.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_PROFISSIONAIS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfissionalModel extends RepresentationModel<ProfissionalModel> implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 15, unique = true)
    private String telefone;

    @Column(nullable = false, length = 100)
    private String especialidade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProfissionalModel that = (ProfissionalModel) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(telefone, that.telefone) && Objects.equals(especialidade, that.especialidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, nome, telefone, especialidade);
    }

    @Override
    public String toString() {
        return "ProfissionalModel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", especialidade='" + especialidade + '\'' +
                '}';
    }
}
