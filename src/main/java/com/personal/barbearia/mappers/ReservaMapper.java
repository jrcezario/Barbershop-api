package com.personal.barbearia.mappers;

import com.personal.barbearia.dtos.ReservaDTO;
import com.personal.barbearia.models.Reserva;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservaMapper {

    @Mapping(target = "cliente", source = "cliente")
    @Mapping(target = "data", source = "dataReserva")
    @Mapping(target = "hora", source = "horaReserva")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "profissional", source = "profissional")
    ReservaDTO toReservaDTO(Reserva reserva);

    @Mapping(target = "cliente", source = "cliente")
    @Mapping(target = "dataReserva", source = "data")
    @Mapping(target = "horaReserva", source = "hora")
    @Mapping(target = "profissional", source = "profissional")
    @Mapping(target = "status", ignore = true)
    Reserva toReservaEntity(ReservaDTO reservaDTO);
}
