package com.personal.barbearia.services;

import com.personal.barbearia.dtos.ReservaDTO;
import com.personal.barbearia.enums.TabelaDeErros;
import com.personal.barbearia.exceptions.ErroDeNegocioException;
import com.personal.barbearia.mappers.ReservaMapper;
import com.personal.barbearia.models.Reserva;
import com.personal.barbearia.repositories.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;

    ReservaMapper reservaMapper = Mappers.getMapper(ReservaMapper.class);

    public List<ReservaDTO> list() {
        return reservaRepository.findAll()
                .stream()
                .map(reserva -> reservaMapper.toReservaDTO(reserva))
                .collect(Collectors.toList());
    }

    public ReservaDTO getOne(Long id) {
        return reservaRepository.findById(id)
                .map(reserva -> reservaMapper.toReservaDTO(reserva))
                .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.RESERVA_NAO_ENCONTRADA));
    }

    public ReservaDTO create(ReservaDTO reservaDTO) {
        Reserva reserva = reservaMapper.toReservaEntity(reservaDTO);
        return reservaMapper.toReservaDTO(reservaRepository.save(reserva));
    }

    public void delete(Long id) {
        reservaRepository.delete(
                reservaRepository.findById(id)
                        .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.RESERVA_NAO_ENCONTRADA)));
    }

    public ReservaDTO update(Long id, ReservaDTO reservaDTO) {
        return reservaRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setCliente(reservaDTO.cliente());
                    recordFound.setDataReserva(reservaDTO.data());
                    recordFound.setHoraReserva(reservaDTO.hora());
                    recordFound.setProfissional(reservaDTO.profissional());
                    return reservaMapper.toReservaDTO(reservaRepository.save(recordFound));
                }).orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.RESERVA_NAO_ENCONTRADA));
    }
}
