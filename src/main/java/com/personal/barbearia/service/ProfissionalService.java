package com.personal.barbearia.service;

import com.personal.barbearia.model.ProfissionalModel;
import com.personal.barbearia.repository.ProfissionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;

    public ProfissionalService(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }

    public List<ProfissionalModel> listar() {
        return this.profissionalRepository.findAll();
    }

    public ProfissionalModel pegarUm(UUID id) {
        return this.profissionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro"));
    }

    public ProfissionalModel salvar(ProfissionalModel profissionalModel) {
        return this.profissionalRepository.save(profissionalModel);
    }

    public void deletar(UUID id) {
        this.profissionalRepository.delete(
                this.profissionalRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Erro")));
    }

    public void atualizar(UUID id, ProfissionalModel profissionalModel) {
        ProfissionalModel profissionalModelSelecionado = this.profissionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro"));

        profissionalModel.setId(profissionalModelSelecionado.getId());

        this.profissionalRepository.save(profissionalModel);

    }
}
