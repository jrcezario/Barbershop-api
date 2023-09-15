package com.personal.barbearia.mappers;

import com.personal.barbearia.dtos.ProfissionalDTO;
import com.personal.barbearia.models.Profissional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProfissionalMapper {

    @Mapping(target = "nome", source = "nomeProfissional")
    @Mapping(target = "telefone", source = "telefoneProfissional")
    @Mapping(target = "status", source = "status")
    ProfissionalDTO toProfissionalDTO(Profissional profissional);

    @Mapping(target = "nomeProfissional", source = "nome")
    @Mapping(target = "telefoneProfissional", source = "telefone")
    @Mapping(target = "status", ignore = true)
    Profissional toProfissionalEntity(ProfissionalDTO profissionalDTO);
}
