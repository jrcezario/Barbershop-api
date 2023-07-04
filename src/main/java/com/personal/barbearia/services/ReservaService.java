package com.personal.barbearia.services;

import com.personal.barbearia.enums.TabelaDeErros;
import com.personal.barbearia.exceptions.ErroDeNegocioException;
import com.personal.barbearia.models.ClienteModel;
import com.personal.barbearia.models.ReservaModel;
import com.personal.barbearia.repositories.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<ReservaModel> listar() {
        return reservaRepository.findAll();
    }

    public ReservaModel pegarUma(UUID id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.RESERVA_NAO_ENCONTRADA));
    }

    public ReservaModel salvar(ReservaModel reservaModel) {
        return reservaRepository.save(reservaModel);
    }

    public void deletar(UUID id) {
        reservaRepository.delete(
                reservaRepository.findById(id)
                        .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.RESERVA_NAO_ENCONTRADA)));
    }

}
