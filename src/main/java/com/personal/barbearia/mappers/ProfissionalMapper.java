package com.personal.barbearia.mappers;

import com.personal.barbearia.dtos.ProfissionalDto;
import com.personal.barbearia.models.Profissional;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfissionalMapper {

    ProfissionalMapper INSTANCE = Mappers.getMapper(ProfissionalMapper.class);

    ProfissionalDto toProfissionalDto(Profissional profissional);

    Profissional toProfissionalEntity(ProfissionalDto profissionalDto);
}
