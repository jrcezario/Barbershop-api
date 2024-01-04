package com.personal.barbearia.services;

import com.personal.barbearia.dtos.ReservaDto;
import com.personal.barbearia.dtos.ReservaPageDTO;
import com.personal.barbearia.exceptions.RecordNotFoundException;
import com.personal.barbearia.mappers.ReservaMapper;
import com.personal.barbearia.models.Reserva;
import com.personal.barbearia.repositories.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaPageDTO list(int page, int size) {
        Page<Reserva> pageReserva = reservaRepository.findAll(PageRequest.of(page, size));
        List<ReservaDto> reservas = pageReserva.get().map(
                reserva -> ReservaMapper.INSTANCE.toReservaDto(reserva)
        ).collect(Collectors.toList());
        return new ReservaPageDTO(reservas, pageReserva.getTotalElements(), pageReserva.getTotalPages());
    }

    public ReservaDto getOne(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
        return ReservaMapper.INSTANCE.toReservaDto(reserva);
    }

    public ReservaDto create(ReservaDto reservaDTO) {
        Reserva reserva = ReservaMapper.INSTANCE.toReservaEntity(reservaDTO);
        return ReservaMapper.INSTANCE.toReservaDto(reservaRepository.save(reserva));
    }

    public void delete(Long id) {
        reservaRepository.delete(
                reservaRepository.findById(id)
                        .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    public ReservaDto update(Long id, ReservaDto reservaDTO) {
        return reservaRepository.findById(id)
                .map(recordFound -> {
                    Reserva reserva = ReservaMapper.INSTANCE.toReservaEntity(reservaDTO);
                    recordFound.setCliente(reservaDTO.cliente());
                    recordFound.setData(reservaDTO.data());
                    recordFound.setHora(reservaDTO.hora());
                    recordFound.setProfissional(reservaDTO.profissional());
                    return ReservaMapper.INSTANCE.toReservaDto(reservaRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }
}
