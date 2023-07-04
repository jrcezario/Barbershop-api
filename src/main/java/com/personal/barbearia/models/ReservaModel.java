package com.personal.barbearia.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "TB_RESERVAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaModel implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteModel cliente;

    private LocalDate dataReserva;

    private LocalTime horaReserva;

    @ManyToMany
    @JoinTable(name = "TB_RESERVA_SERVICO",
            joinColumns = @JoinColumn(name = "reserva_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id"))
    private List<ServicoModel> servicos;

    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private ProfissionalModel profissional;

    @Column(nullable = false, length = 130)
    private String descricao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ReservaModel that = (ReservaModel) o;
        return Objects.equals(id, that.id) && Objects.equals(cliente, that.cliente) && Objects.equals(dataReserva, that.dataReserva) && Objects.equals(horaReserva, that.horaReserva) && Objects.equals(servicos, that.servicos) && Objects.equals(profissional, that.profissional) && Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, cliente, dataReserva, horaReserva, servicos, profissional, descricao);
    }

    @Override
    public String toString() {
        return "ReservaModel{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", dataReserva=" + dataReserva +
                ", horaReserva=" + horaReserva +
                ", servico=" + servicos +
                ", profissional=" + profissional +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
