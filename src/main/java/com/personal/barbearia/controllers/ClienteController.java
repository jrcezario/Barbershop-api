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
    public List<ClienteDTO> listarClientes() {
        return clienteService.listar();
    }

    @GetMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO pegarCliente(@PathVariable UUID id) {
        return clienteService.pegarUm(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO salvarCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        return clienteService.salvar(clienteDTO);
    }

    @DeleteMapping("id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirCliente(@PathVariable UUID id) {
        clienteService.deletar(id);
    }

    @PutMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO atualizarCliente(@PathVariable UUID id, @RequestBody @Valid ClienteDTO clienteDTO) {
       return clienteService.atualizar(id, clienteDTO);
    }
}
