package com.personal.barbearia.mappers;

import com.personal.barbearia.dtos.ClienteDto;
import com.personal.barbearia.models.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    ClienteDto toClienteDto(Cliente cliente);

    Cliente toClienteEntity(ClienteDto clienteDto);
}
