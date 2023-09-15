package com.personal.barbearia.mappers;

import com.personal.barbearia.dtos.ServicoDTO;
import com.personal.barbearia.models.Servico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServicoMapper {

    @Mapping(target = "nome", source = "nomeServico")
    @Mapping(target = "valor", source = "valorServico")
    @Mapping(target = "descricao", source = "descricaoServico")
    @Mapping(target = "status", source = "status")
    ServicoDTO toServicoDTO(Servico servico);

    @Mapping(target = "nomeServico", source = "nome")
    @Mapping(target = "valorServico", source = "valor")
    @Mapping(target = "descricaoServico", source = "descricao")
    @Mapping(target = "status", ignore = true)
    Servico toServicoEntity(ServicoDTO servicoDTO);
}
