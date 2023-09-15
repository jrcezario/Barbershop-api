package com.personal.barbearia.mappers;

import com.personal.barbearia.dtos.ReservaDTO;
import com.personal.barbearia.models.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IReservaMapper {

    @Mapping(target = "data", source = "dataReserva")
    @Mapping(target = "hora", source = "horaReserva")
    ReservaDTO toReservaDTO(Reserva reserva);

    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "dataReserva", source = "data")
    @Mapping(target = "horaReserva", source = "hora")
    @Mapping(target = "profissional", ignore = true)
    @Mapping(target = "status", ignore = true)
    Reserva toReservaEntity(ReservaDTO reservaDTO);
}
