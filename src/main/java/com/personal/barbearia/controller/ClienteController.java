package com.personal.barbearia.controller;

import com.personal.barbearia.dto.ClienteRecordDTO;
import com.personal.barbearia.model.ClienteModel;
import com.personal.barbearia.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteModel>> listarClientes() {
        List<ClienteModel> listaClientes = clienteService.listar();
        if(!listaClientes.isEmpty()) {
            for(ClienteModel cliente : listaClientes) {
                UUID id = cliente.getId();
                cliente.add(linkTo(methodOn(ClienteController.class).pegarCliente(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listar());
    }

    @GetMapping("id/{id}")
    public ResponseEntity<ClienteModel> pegarCliente(@PathVariable UUID id) {
        ClienteModel cliente = clienteService.pegarUm(id);
        cliente.add(linkTo(methodOn(ClienteController.class).listarClientes()).withRel("Lista de Cliente"));
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @PostMapping
    public ResponseEntity<ClienteModel> salvarCliente(@RequestBody @Valid ClienteRecordDTO clienteRecordDTO) {
        var cliente = new ClienteModel();
        BeanUtils.copyProperties(clienteRecordDTO, cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.salvar(cliente));
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable UUID id) {
        clienteService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("id/{id}")
    public ResponseEntity<ClienteModel> atualizarCliente(@PathVariable UUID id, @RequestBody @Valid ClienteRecordDTO clienteRecordDTO) {
        ClienteModel cliente = clienteService.pegarUm(id);
        BeanUtils.copyProperties(clienteRecordDTO, cliente);
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.salvar(cliente));
    }
}
