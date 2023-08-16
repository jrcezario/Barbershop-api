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

//hibernate faz um soft delete, apenas alterando o status do objeto
@SQLDelete(sql = "UPDATE tb_reservas SET status = 'Inativo' WHERE id = ?")
//hibernate verifica toda vez q é feito um select no BD e insere a clausula WHERE para filtrar
@Where(clause = "status = 'Ativo'")
public class Reserva implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private LocalDate dataReserva;

    private LocalTime horaReserva;

//    @ManyToMany
//    @JoinTable(name = "TB_RESERVA_SERVICO",
//            joinColumns = @JoinColumn(name = "reserva_id"),
//            inverseJoinColumns = @JoinColumn(name = "servico_id"))
//    private List<ServicoModel> servicos;

    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;

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
        Reserva that = (Reserva) o;
        return Objects.equals(id, that.id) && Objects.equals(cliente, that.cliente) && Objects.equals(dataReserva, that.dataReserva) && Objects.equals(horaReserva, that.horaReserva) && Objects.equals(profissional, that.profissional) && Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, cliente, dataReserva, horaReserva, profissional, descricao);
    }

    @Override
    public String toString() {
        return "ReservaModel{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", dataReserva=" + dataReserva +
                ", horaReserva=" + horaReserva +
                ", profissional=" + profissional +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
