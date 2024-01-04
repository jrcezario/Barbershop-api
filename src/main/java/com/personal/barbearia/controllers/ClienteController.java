package com.personal.barbearia.controllers;

import com.personal.barbearia.dtos.ClienteDto;
import com.personal.barbearia.services.ClienteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDto> clienteList() {
        return clienteService.list();
    }

    @GetMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDto getCliente(@PathVariable @Positive @NotNull Long id) {
        return clienteService.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto createCliente(@RequestBody @Valid @NotNull ClienteDto clienteDTO) {
        return clienteService.create(clienteDTO);
    }

    @DeleteMapping("id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable @Positive @NotNull Long id) {
        clienteService.delete(id);
    }

    @PutMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDto updateCliente(@PathVariable @Positive @NotNull Long id, @RequestBody @Valid @NotNull ClienteDto clienteDTO) {
        return clienteService.update(id, clienteDTO);
    }
}
