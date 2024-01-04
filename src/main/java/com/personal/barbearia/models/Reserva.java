package com.personal.barbearia.models;

import com.personal.barbearia.enums.Status;
import com.personal.barbearia.enums.converters.StatusConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "TB_RESERVAS")
@Data
@NoArgsConstructor
@AllArgsConstructor

//hibernate faz um soft delete, apenas alterando o status do objeto
@SQLDelete(sql = "UPDATE tb_reservas SET status = 'Inativo' WHERE id = ?")
//hibernate verifica toda vez q é feito um select no BD e insere a clausula WHERE para filtrar
@Where(clause = "status = 'Ativo'")
public class Reserva extends RepresentationModel<Reserva> implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private LocalTime hora;

    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = false)
    private Profissional profissional;

    @Column(nullable = false, length = 10)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ATIVO;

}
