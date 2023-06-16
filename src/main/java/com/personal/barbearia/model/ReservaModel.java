package com.personal.barbearia.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_RESERVAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaModel extends RepresentationModel<ReservaModel> implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private ClienteModel clienteModel;

    private LocalDate dataReserva;

    private LocalTime horaReserva;

    @OneToOne
    @JoinColumn(name = "servico_id")
    private ServicoModel servicoModel;

    @OneToOne
    @JoinColumn(name = "profissional_id")
    private ProfissionalModel profissionalModel;

    @Column(nullable = false, length = 130)
    private String descricao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ReservaModel that = (ReservaModel) o;
        return Objects.equals(id, that.id) && Objects.equals(clienteModel, that.clienteModel) && Objects.equals(dataReserva, that.dataReserva) && Objects.equals(horaReserva, that.horaReserva) && Objects.equals(servicoModel, that.servicoModel) && Objects.equals(profissionalModel, that.profissionalModel) && Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, clienteModel, dataReserva, horaReserva, servicoModel, profissionalModel, descricao);
    }

    @Override
    public String toString() {
        return "ReservaModel{" +
                "id=" + id +
                ", clienteModel=" + clienteModel +
                ", dataReserva=" + dataReserva +
                ", horaReserva=" + horaReserva +
                ", servicoModel=" + servicoModel +
                ", profissionalModel=" + profissionalModel +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
