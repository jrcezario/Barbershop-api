package com.personal.barbearia.services;

import com.personal.barbearia.enums.TabelaDeErros;
import com.personal.barbearia.exceptions.ErroDeNegocioException;
import com.personal.barbearia.models.ClienteModel;
import com.personal.barbearia.repositories.ClienteRepository;
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
        return clienteRepository.findAll();
    }

    public ClienteModel pegarUm(UUID id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO));
    }

    public ClienteModel salvar(ClienteModel clienteModel) {
        return clienteRepository.save(clienteModel);
    }

    public void deletar(UUID id) {
        clienteRepository.delete(
                clienteRepository.findById(id)
                        .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO)));
    }

}
