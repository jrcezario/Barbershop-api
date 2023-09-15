package com.personal.barbearia.services.impl;

import com.personal.barbearia.dtos.ClienteDTO;
import com.personal.barbearia.enums.TabelaDeErros;
import com.personal.barbearia.exceptions.ErroDeNegocioException;
import com.personal.barbearia.mappers.IClienteMapper;
import com.personal.barbearia.models.Cliente;
import com.personal.barbearia.repositories.ClienteRepository;
import com.personal.barbearia.services.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService {

    private final ClienteRepository clienteRepository;
    private final IClienteMapper clienteMapper;

    @Override
    public List<ClienteDTO> list() {
        return clienteRepository.findAll()
                .stream()
                .map(cliente -> clienteMapper.toClienteDTO(cliente))
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO getOne(Long id) {
        return clienteRepository.findById(id)
                .map(cliente -> clienteMapper.toClienteDTO(cliente))
                .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO));
    }

    @Override
    public ClienteDTO create(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toClienteEntity(clienteDTO);
        return clienteMapper.toClienteDTO(clienteRepository.save(cliente));
    }

    @Override
    public void delete(Long id) {
        clienteRepository.delete(
                clienteRepository.findById(id)
                        .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO)));
    }

    @Override
    public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
        return clienteRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(clienteDTO.nomeCliente());
                    recordFound.setTelefone(clienteDTO.telefoneCliente());
                    return clienteMapper.toClienteDTO(clienteRepository.save(recordFound));
                }).orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO));
    }
}
