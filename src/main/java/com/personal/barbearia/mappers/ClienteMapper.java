package com.personal.barbearia.mappers;

import com.personal.barbearia.dtos.request.ClienteDtoRequest;
import com.personal.barbearia.dtos.response.ClienteDtoResponse;
import com.personal.barbearia.models.ClienteModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    @Autowired
    ModelMapper modelMapper;

    public ClienteModel toClienteModel(ClienteDtoRequest clienteDTORequest) {
        return modelMapper.map(clienteDTORequest, ClienteModel.class);
    }

    public ClienteDtoResponse toClienteDtoResponse(ClienteModel clienteModel) {
        return modelMapper.map(clienteModel, ClienteDtoResponse.class);
    }

    public void toCopyProperties(ClienteDtoRequest clienteDTORequest, ClienteModel cliente) {
        modelMapper.map(clienteDTORequest, cliente);
    }
}
