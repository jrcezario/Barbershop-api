package com.personal.barbearia.service;

import com.personal.barbearia.model.ReservaModel;
import com.personal.barbearia.repository.ReservaRepository;
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
                .orElseThrow(() -> new RuntimeException("Erro"));
    }

    public ReservaModel salvar(ReservaModel reservaModel) {
        return reservaRepository.save(reservaModel);
    }

    public void deletar(UUID id) {
        reservaRepository.delete(
                reservaRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Erro")));
    }
}
