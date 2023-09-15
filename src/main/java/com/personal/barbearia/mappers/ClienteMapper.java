package com.personal.barbearia.mappers;

import com.personal.barbearia.dtos.ClienteDTO;
import com.personal.barbearia.models.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteMapper {

    @Mapping(target = "nome", source = "nomeCliente")
    @Mapping(target = "telefone", source = "telefoneCliente")
    @Mapping(target = "status", source = "status")
    ClienteDTO toClienteDTO(Cliente cliente);

    @Mapping(target = "nomeCliente", source = "nome")
    @Mapping(target = "telefoneCliente", source = "telefone")
    @Mapping(target = "status", ignore = true)
    Cliente toClienteEntity(ClienteDTO clienteDTO);
}
