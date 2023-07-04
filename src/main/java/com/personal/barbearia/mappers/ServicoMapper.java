package com.personal.barbearia.mappers;

import com.personal.barbearia.dtos.request.ServicoDtoRequest;
import com.personal.barbearia.dtos.response.ServicoDtoResponse;
import com.personal.barbearia.models.ServicoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServicoMapper {

    @Autowired
    ModelMapper modelMapper;

    public ServicoModel toServicoModel(ServicoDtoRequest servicoDTORequest) {
        return modelMapper.map(servicoDTORequest, ServicoModel.class);
    }

    public ServicoDtoResponse toServicoDtoResponse(ServicoModel servicoModel) {
        return modelMapper.map(servicoModel, ServicoDtoResponse.class);
    }

    public void toCopyProperties(ServicoDtoRequest servicoDTORequest, ServicoModel servico) {
        modelMapper.map(servicoDTORequest, servico);
    }
}
