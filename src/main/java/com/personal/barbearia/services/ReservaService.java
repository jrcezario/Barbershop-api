package com.personal.barbearia.services;

import com.personal.barbearia.dtos.ReservaDTO;
import com.personal.barbearia.enums.TabelaDeErros;
import com.personal.barbearia.exceptions.ErroDeNegocioException;
import com.personal.barbearia.mappers.ReservaMapper;
import com.personal.barbearia.models.Reserva;
import com.personal.barbearia.repositories.ReservaRepository;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;

    public ReservaService(ReservaRepository reservaRepository, ReservaMapper reservaMapper) {
        this.reservaRepository = reservaRepository;
        this.reservaMapper = reservaMapper;
    }

    public List<ReservaDTO> listar() {
        return reservaRepository.findAll()
                .stream()
                .map(reserva -> reservaMapper.toDTO(reserva))
                .collect(Collectors.toList());
    }

    public ReservaDTO pegarUma(UUID id) {
        return reservaRepository.findById(id)
                .map(reserva -> reservaMapper.toDTO(reserva))
                .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.RESERVA_NAO_ENCONTRADA));
    }

    public ReservaDTO salvar(ReservaDTO reservaDTO) {
        Reserva reserva = reservaMapper.toEntity(reservaDTO);
        return reservaMapper.toDTO(reservaRepository.save(reserva));
    }

    public void deletar(UUID id) {
        reservaRepository.delete(
                reservaRepository.findById(id)
                        .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.RESERVA_NAO_ENCONTRADA)));
    }

    public ReservaDTO atualizar(UUID id, ReservaDTO reservaDTO) {
        return reservaRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setCliente(reservaDTO.cliente());
                    recordFound.setDataReserva(reservaDTO.dataReserva());
                    recordFound.setHoraReserva(reservaDTO.horaReserva());
                    recordFound.setProfissional(reservaDTO.profissional());
                    recordFound.setDescricao(reservaDTO.descricao());
                    return reservaMapper.toDTO(reservaRepository.save(recordFound));
                }).orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.RESERVA_NAO_ENCONTRADA));
    }
}
