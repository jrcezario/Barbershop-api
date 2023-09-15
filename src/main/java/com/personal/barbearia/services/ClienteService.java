package com.personal.barbearia.services;

import com.personal.barbearia.dtos.ClienteDTO;
import com.personal.barbearia.enums.TabelaDeErros;
import com.personal.barbearia.exceptions.ErroDeNegocioException;
import com.personal.barbearia.mappers.ClienteMapper;
import com.personal.barbearia.models.Cliente;
import com.personal.barbearia.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    ClienteMapper clienteMapper = Mappers.getMapper(ClienteMapper.class);

    public List<ClienteDTO> list() {
        return clienteRepository.findAll()
                .stream()
                .map(cliente -> clienteMapper.toClienteDTO(cliente))
                .collect(Collectors.toList());
    }

    public ClienteDTO getOne(Long id) {
        return clienteRepository.findById(id)
                .map(cliente -> clienteMapper.toClienteDTO(cliente))
                .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO));
    }

    public ClienteDTO create(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toClienteEntity(clienteDTO);
        return clienteMapper.toClienteDTO(clienteRepository.save(cliente));
    }

    public void delete(Long id) {
        clienteRepository.delete(
                clienteRepository.findById(id)
                        .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO)));
    }

    public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
        return clienteRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNomeCliente(clienteDTO.nome());
                    recordFound.setTelefoneCliente(clienteDTO.telefone());
                    return clienteMapper.toClienteDTO(clienteRepository.save(recordFound));
                }).orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.CLIENTE_NAO_ENCONTRADO));
    }
}
