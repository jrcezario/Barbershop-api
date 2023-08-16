package com.personal.barbearia.mappers;

import com.personal.barbearia.dtos.ReservaDTO;
import com.personal.barbearia.models.Reserva;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {

    public ReservaDTO toDTO(Reserva reserva) {
        if(reserva == null) {
            return null;
        }
        return new ReservaDTO(
                reserva.getId(),
                reserva.getCliente(),
                reserva.getDataReserva(),
                reserva.getHoraReserva(),
                reserva.getProfissional(),
                reserva.getDescricao());
    }

    public Reserva toEntity(ReservaDTO reservaDTO) {
        if(reservaDTO == null) {
            return null;
        }

        Reserva reserva = new Reserva();
        if(reservaDTO.id() != null) {
            reserva.setId(reservaDTO.id());
        }
        reserva.setCliente(reservaDTO.cliente());
        reserva.setDataReserva(reservaDTO.dataReserva());
        reserva.setHoraReserva(reservaDTO.horaReserva());
        reserva.setProfissional(reservaDTO.profissional());
        reserva.setDescricao(reservaDTO.descricao());
        return reserva;
    }
}
