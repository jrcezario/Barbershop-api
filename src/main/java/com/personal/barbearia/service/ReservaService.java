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
        return this.reservaRepository.findAll();
    }

    public ReservaModel pegarUma(UUID id) {
        return this.reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro"));
    }

    public ReservaModel salvar(ReservaModel reservaModel) {
        return this.reservaRepository.save(reservaModel);
    }

    public void deletar(UUID id) {
        this.reservaRepository.delete(
                this.reservaRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Erro")));
    }

    public void atualizar(UUID id, ReservaModel reservaModel) {
        ReservaModel reservaModelSelecionada = this.reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro"));

       reservaModel.setId(reservaModelSelecionada.getId());

        this.reservaRepository.save(reservaModel);
    }
}
