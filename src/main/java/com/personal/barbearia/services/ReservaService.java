package com.personal.barbearia.services.impl;

import com.personal.barbearia.dtos.ReservaDTO;
import com.personal.barbearia.enums.TabelaDeErros;
import com.personal.barbearia.exceptions.ErroDeNegocioException;
import com.personal.barbearia.mappers.IReservaMapper;
import com.personal.barbearia.models.Reserva;
import com.personal.barbearia.repositories.ReservaRepository;
import com.personal.barbearia.services.IReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements IReservaService {

    ReservaRepository reservaRepository;
    IReservaMapper reservaMapper;

    @Override
    public List<ReservaDTO> list() {
        return reservaRepository.findAll()
                .stream()
                .map(reserva -> reservaMapper.toReservaDTO(reserva))
                .collect(Collectors.toList());
    }

    @Override
    public ReservaDTO getOne(Long id) {
        return reservaRepository.findById(id)
                .map(reserva -> reservaMapper.toReservaDTO(reserva))
                .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.RESERVA_NAO_ENCONTRADA));
    }

    @Override
    public ReservaDTO create(ReservaDTO reservaDTO) {
        Reserva reserva = reservaMapper.toReservaEntity(reservaDTO);
        return reservaMapper.toReservaDTO(reservaRepository.save(reserva));
    }

    @Override
    public void delete(Long id) {
        reservaRepository.delete(
                reservaRepository.findById(id)
                        .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.RESERVA_NAO_ENCONTRADA)));
    }

    @Override
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
