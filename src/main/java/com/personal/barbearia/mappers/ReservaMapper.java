package com.personal.barbearia.mappers;

import com.personal.barbearia.dtos.ReservaDto;
import com.personal.barbearia.models.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    ReservaMapper INSTANCE = Mappers.getMapper(ReservaMapper.class);

    ReservaDto toReservaDto(Reserva reserva);

    Reserva toReservaEntity(ReservaDto reservaDto);
}
