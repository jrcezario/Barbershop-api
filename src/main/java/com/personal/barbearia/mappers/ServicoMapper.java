package com.personal.barbearia.mappers;

import com.personal.barbearia.dtos.ServicoDTO;
import com.personal.barbearia.models.Servico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface IServicoMapper {

    @Mapping(target = "nomeServico", source = "nome")
    @Mapping(target = "valorServico", source = "valor")
    @Mapping(target = "descricaoServico", source = "descricao")
    ServicoDTO toServicoDTO(Servico servico);

    @Mapping(target = "nome", source = "nomeServico")
    @Mapping(target = "valor", source = "valorServico")
    @Mapping(target = "descricao", source = "descricaoServico")
    @Mapping(target = "status", ignore = true)
    Servico toServicoEntity(ServicoDTO servicoDTO);
}
