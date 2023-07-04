package com.personal.barbearia.mappers;

import com.personal.barbearia.dtos.request.ReservaDtoRequest;
import com.personal.barbearia.dtos.response.ReservaDtoResponse;
import com.personal.barbearia.models.ReservaModel;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {

    @Autowired
    ModelMapper modelMapper;

    public ReservaModel toReservaModel(ReservaDtoRequest reservaDTORequest) {
        return modelMapper.map(reservaDTORequest, ReservaModel.class);
    }

    public ReservaDtoResponse toReservaDtoResponse(ReservaModel reservaModel) {
        return modelMapper.map(reservaModel, ReservaDtoResponse.class);
    }

    public void toCopyProperties(ReservaDtoRequest reservaDTORequest, ReservaModel reserva) {
        modelMapper.map(reservaDTORequest, reserva);
    }
}
