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
        return profissionalRepository.findAll();
    }

    public ProfissionalModel pegarUm(UUID id) {
        return profissionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro"));
    }

    public ProfissionalModel salvar(ProfissionalModel profissionalModel) {
        return profissionalRepository.save(profissionalModel);
    }

    public void deletar(UUID id) {
        profissionalRepository.delete(
                profissionalRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Erro")));
    }
}
