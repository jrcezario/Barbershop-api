package com.personal.barbearia.controllers;

import com.personal.barbearia.dtos.request.ServicoDtoRequest;
import com.personal.barbearia.dtos.response.ServicoDtoResponse;
import com.personal.barbearia.mappers.ServicoMapper;
import com.personal.barbearia.models.ServicoModel;
import com.personal.barbearia.services.ServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("servicos")
public class ServicoController {

    @Autowired
    ServicoService servicoService;

    @Autowired
    ServicoMapper servicoMapper;

    @GetMapping
    public ResponseEntity<List<ServicoDtoResponse>> listarServicos() {
        List<ServicoModel> listaServicos = servicoService.listar();
        List<ServicoDtoResponse> listResponse = new ArrayList<>();

        if(!listaServicos.isEmpty()) {
            for(ServicoModel servico : listaServicos) {
                ServicoDtoResponse response = servicoMapper.toServicoDtoResponse(servico);
                UUID id = response.getId();
                response.add(linkTo(methodOn(ServicoController.class).pegarServico(id)).withSelfRel());
                listResponse.add(response);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(listResponse);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<ServicoDtoResponse> pegarServico(@PathVariable UUID id) {
        ServicoModel servico = servicoService.pegarUm(id);
        ServicoDtoResponse response = servicoMapper.toServicoDtoResponse(servico);
        Link link = linkTo(methodOn(ServicoController.class).listarServicos()).withRel("Lista de Serviços");
        response.add(link);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ServicoDtoResponse> salvarServico(@RequestBody @Valid ServicoDtoRequest servicoDTORequest) {
        ServicoModel servico = servicoMapper.toServicoModel(servicoDTORequest);
        ServicoDtoResponse response = servicoMapper.toServicoDtoResponse(servicoService.salvar(servico));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> excluirServico(@PathVariable UUID id) {
        servicoService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("id/{id}")
    public ResponseEntity<ServicoDtoResponse> atualizarServico(@PathVariable UUID id, @RequestBody @Valid ServicoDtoRequest servicoDTORequest) {
        ServicoModel servico = servicoService.pegarUm(id);
        servicoMapper.toCopyProperties(servicoDTORequest, servico);
        ServicoDtoResponse response = servicoMapper.toServicoDtoResponse(servicoService.salvar(servico));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
