package com.personal.barbearia.service;

import com.personal.barbearia.model.ClienteModel;
import com.personal.barbearia.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteModel> listar() {
        return this.clienteRepository.findAll();
    }

    public ClienteModel pegarUm(UUID id) {
        return this.clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Erro"));
    }

    public ClienteModel salvar(ClienteModel clienteModel) {
        return this.clienteRepository.save(clienteModel);
    }

    public void deletar(UUID id) {
        this.clienteRepository.delete(
                this.clienteRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Erro")));
    }


}
