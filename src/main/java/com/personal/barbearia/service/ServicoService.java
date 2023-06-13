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
        return this.servicoRepository.findAll();
    }

    public ServicoModel pegarUm(UUID id) {
        return this.servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));
    }

    public ServicoModel salvar(ServicoModel servicoModel) {
        return this.servicoRepository.save(servicoModel);
    }

    public void deletar(UUID id) {
        this.servicoRepository.delete(
                this.servicoRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Serviço não encontrado")));
    }

    public void atualizar(UUID id, ServicoModel servicoModel) {
        ServicoModel servicoModelSelecionado = this.servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        servicoModel.setId(servicoModelSelecionado.getId());

        this.servicoRepository.save(servicoModel);
    }
}
