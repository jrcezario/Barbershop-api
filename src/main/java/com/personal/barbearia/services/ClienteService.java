package com.personal.barbearia.services;

import com.personal.barbearia.dtos.ClienteDto;
import com.personal.barbearia.exceptions.RecordNotFoundException;
import com.personal.barbearia.mappers.ClienteMapper;
import com.personal.barbearia.models.Cliente;
import com.personal.barbearia.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<ClienteDto> list() {
        return clienteRepository.findAll()
                .stream()
                .map(cliente -> ClienteMapper.INSTANCE.toClienteDto(cliente))
                .collect(Collectors.toList());
    }

    public ClienteDto getOne(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
        return ClienteMapper.INSTANCE.toClienteDto(cliente);
    }

    public ClienteDto create(ClienteDto clienteDTO) {
        Cliente cliente = ClienteMapper.INSTANCE.toClienteEntity(clienteDTO);
        return ClienteMapper.INSTANCE.toClienteDto(clienteRepository.save(cliente));
    }

    public void delete(Long id) {
        clienteRepository.delete(
                clienteRepository.findById(id)
                        .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    public ClienteDto update(Long id, ClienteDto clienteDTO) {
        return clienteRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(clienteDTO.nome());
                    recordFound.setTelefone(clienteDTO.telefone());
                    return ClienteMapper.INSTANCE.toClienteDto(clienteRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }
}
