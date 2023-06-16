package com.personal.barbearia.service;

import com.personal.barbearia.model.ServicoModel;
import com.personal.barbearia.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public List<ServicoModel> list() {
        return servicoRepository.findAll();
    }

    public ServicoModel pegarUm(UUID id) {
        return servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro"));
    }

    public ServicoModel salvar(ServicoModel servicoModel) {
        return servicoRepository.save(servicoModel);
    }

    public void deletar(UUID id) {
        servicoRepository.delete(
                servicoRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Erro")));
    }
}
