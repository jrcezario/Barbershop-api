package com.personal.barbearia.controllers;

import com.personal.barbearia.dtos.ClienteDTO;
import com.personal.barbearia.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDTO> clienteList() {
        return clienteService.list();
    }

    @GetMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO getCliente(@PathVariable UUID id) {
        return clienteService.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO createCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        return clienteService.create(clienteDTO);
    }

    @DeleteMapping("id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable UUID id) {
        clienteService.delete(id);
    }

    @PutMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO updateCliente(@PathVariable UUID id, @RequestBody @Valid ClienteDTO clienteDTO) {
       return clienteService.update(id, clienteDTO);
    }
}
