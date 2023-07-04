package com.personal.barbearia.controllers;

import com.personal.barbearia.dtos.request.ProfissionalDtoRequest;
import com.personal.barbearia.dtos.response.ProfissionalDtoResponse;
import com.personal.barbearia.mappers.ProfissionalMapper;
import com.personal.barbearia.models.ProfissionalModel;
import com.personal.barbearia.services.ProfissionalService;
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
@RequestMapping("profissionais")
public class ProfissionalController {

    @Autowired
    ProfissionalService profissionalService;

    @Autowired
    ProfissionalMapper profissionalMapper;

    @GetMapping
    public ResponseEntity<List<ProfissionalDtoResponse>> listarProfissionais() {
        List<ProfissionalModel> listaProfissionais = profissionalService.listar();
        List<ProfissionalDtoResponse> listResponse = new ArrayList<>();

        if(!listaProfissionais.isEmpty()) {
            for(ProfissionalModel profissional : listaProfissionais) {
                ProfissionalDtoResponse response = profissionalMapper.toProfissionalDtoResponse(profissional);
                UUID id = response.getId();
                response.add(linkTo(methodOn(ProfissionalController.class).pegarProfissional(id)).withSelfRel());
                listResponse.add(response);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(listResponse);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<ProfissionalDtoResponse> pegarProfissional(@PathVariable UUID id) {
        ProfissionalModel profissional = profissionalService.pegarUm(id);
        ProfissionalDtoResponse response = profissionalMapper.toProfissionalDtoResponse(profissional);
        Link link = linkTo(methodOn(ProfissionalController.class).listarProfissionais()).withRel("Lista de Profissionais");
        response.add(link);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ProfissionalDtoResponse> salvarProfissional(@RequestBody @Valid ProfissionalDtoRequest profissionalDTORequest) {
        ProfissionalModel profissional = profissionalMapper.toProfissionalModel(profissionalDTORequest);

        ProfissionalDtoResponse response = profissionalMapper.toProfissionalDtoResponse(profissionalService.salvar(profissional));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> excluirProfissional(@PathVariable UUID id) {
        profissionalService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("id/{id}")
    public ResponseEntity<ProfissionalDtoResponse> atualizarProfissional(@PathVariable UUID id, @RequestBody @Valid ProfissionalDtoRequest profissionalDTORequest) {
        ProfissionalModel profissional = profissionalService.pegarUm(id);
        profissionalMapper.toCopyProperties(profissionalDTORequest, profissional);
        ProfissionalDtoResponse response = profissionalMapper.toProfissionalDtoResponse(profissionalService.salvar(profissional));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
