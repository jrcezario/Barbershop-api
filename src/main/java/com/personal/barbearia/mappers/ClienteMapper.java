package com.personal.barbearia.mappers;

import com.personal.barbearia.dtos.ClienteDTO;
import com.personal.barbearia.models.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteDTO toDTO(Cliente cliente) {
        if(cliente == null) {
            return null;
        }
        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getTelefone());
    }

    public Cliente toEntity(ClienteDTO clienteDTO) {
        if(clienteDTO == null) {
            return null;
        }

        Cliente cliente = new Cliente();
        if(clienteDTO.id() != null) {
            cliente.setId(clienteDTO.id());
        }
        cliente.setNome(clienteDTO.nome());
        cliente.setTelefone(clienteDTO.telefone());
        return cliente;
    }
}
