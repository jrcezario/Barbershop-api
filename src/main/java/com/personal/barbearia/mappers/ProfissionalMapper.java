package com.personal.barbearia.mappers;

import com.personal.barbearia.dtos.request.ProfissionalDtoRequest;
import com.personal.barbearia.dtos.response.ProfissionalDtoResponse;
import com.personal.barbearia.models.ProfissionalModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfissionalMapper {

    @Autowired
    ModelMapper modelMapper;

    public ProfissionalModel toProfissionalModel(ProfissionalDtoRequest profissionalDTORequest) {
        return modelMapper.map(profissionalDTORequest, ProfissionalModel.class);
    }

    public ProfissionalDtoResponse toProfissionalDtoResponse(ProfissionalModel profissionalModel) {
        return modelMapper.map(profissionalModel, ProfissionalDtoResponse.class);
    }

    public void toCopyProperties(ProfissionalDtoRequest profissionalDTORequest, ProfissionalModel profissional) {
        modelMapper.map(profissionalDTORequest, profissional);
    }
}
