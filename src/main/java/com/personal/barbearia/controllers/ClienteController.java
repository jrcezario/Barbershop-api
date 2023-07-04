package com.personal.barbearia.controllers;

import com.personal.barbearia.dtos.request.ClienteDtoRequest;
import com.personal.barbearia.dtos.response.ClienteDtoResponse;
import com.personal.barbearia.mappers.ClienteMapper;
import com.personal.barbearia.models.ClienteModel;
import com.personal.barbearia.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ClienteMapper clienteMapper;

    @GetMapping
    public ResponseEntity<List<ClienteDtoResponse>> listarClientes() {
        List<ClienteModel> listaClientes = clienteService.listar();
        List<ClienteDtoResponse> listResponse = new ArrayList<>();

        if (!listaClientes.isEmpty()) {
            for(ClienteModel cliente : listaClientes) {
                ClienteDtoResponse response = clienteMapper.toClienteDtoResponse(cliente);
                UUID id = response.getId();
                response.add(linkTo(methodOn(ClienteController.class).pegarCliente(id)).withSelfRel());
                listResponse.add(response);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(listResponse);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<ClienteDtoResponse> pegarCliente(@PathVariable UUID id) {
        ClienteModel cliente = clienteService.pegarUm(id);
        ClienteDtoResponse response = clienteMapper.toClienteDtoResponse(cliente);
        Link link = linkTo(methodOn(ClienteController.class).listarClientes()).withRel("Lista de Cliente");
        response.add(link);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ClienteDtoResponse> salvarCliente(@RequestBody @Valid ClienteDtoRequest clienteDTORequest) {
        ClienteModel cliente = clienteMapper.toClienteModel(clienteDTORequest);
        ClienteDtoResponse response = clienteMapper.toClienteDtoResponse(clienteService.salvar(cliente));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable UUID id) {
        clienteService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("id/{id}")
    public ResponseEntity<ClienteDtoResponse> atualizarCliente(@PathVariable UUID id, @RequestBody @Valid ClienteDtoRequest clienteDTORequest) {
        ClienteModel cliente = clienteService.pegarUm(id);
        clienteMapper.toCopyProperties(clienteDTORequest, cliente);
        ClienteDtoResponse response = clienteMapper.toClienteDtoResponse(clienteService.salvar(cliente));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
