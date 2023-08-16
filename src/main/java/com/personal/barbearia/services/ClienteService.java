package com.personal.barbearia.services;

import com.personal.barbearia.dtos.ClienteDTO;
import com.personal.barbearia.enums.TabelaDeErros;
import com.personal.barbearia.exceptions.ErroDeNegocioException;
import com.personal.barbearia.mappers.ClienteMapper;
import com.personal.barbearia.models.Cliente;
import com.personal.barbearia.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public List<ClienteDTO> listar() {
        return clienteRepository.findAll()
                .stream()
                .map(cliente -> clienteMapper.toDTO(cliente))
                .collect(Collectors.toList());
    }

    public ClienteDTO pegarUm(UUID id) {
        return clienteRepository.findById(id)
                .map(cliente -> clienteMapper.toDTO(cliente))
                .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO));
    }

    public ClienteDTO salvar(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        return clienteMapper.toDTO(clienteRepository.save(cliente));
    }

    public void deletar(UUID id) {
        clienteRepository.delete(
                clienteRepository.findById(id)
                        .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO)));
    }

    public ClienteDTO atualizar(UUID id, ClienteDTO clienteDTO) {
        return clienteRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(clienteDTO.nome());
                    recordFound.setTelefone(clienteDTO.telefone());
                    return clienteMapper.toDTO(clienteRepository.save(recordFound));
                }).orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO));
    }
}
