package com.personal.barbearia.controller;

import com.personal.barbearia.dto.ProfissionalRecordDTO;
import com.personal.barbearia.model.ProfissionalModel;
import com.personal.barbearia.service.ProfissionalService;
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
@RequestMapping("profissionais")
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    @GetMapping
    public ResponseEntity<List<ProfissionalModel>> listarProfissionais() {
        List<ProfissionalModel> listaProfissionais = profissionalService.listar();
        if(!listaProfissionais.isEmpty()) {
            for(ProfissionalModel profissional : listaProfissionais) {
                UUID id = profissional.getId();
                profissional.add(linkTo(methodOn(ProfissionalController.class).pegarProfissional(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(profissionalService.listar());
    }

    @GetMapping("id/{id}")
    public ResponseEntity<ProfissionalModel> pegarProfissional(@PathVariable UUID id) {
        ProfissionalModel profissional = profissionalService.pegarUm(id);
        profissional.add(linkTo(methodOn(ProfissionalController.class).listarProfissionais()).withRel("Lista de Profissionais"));
        return ResponseEntity.status(HttpStatus.OK).body(profissional);
    }

    @PostMapping
    public ResponseEntity<ProfissionalModel> salvarProfissional(@RequestBody @Valid ProfissionalRecordDTO profissionalRecordDTO) {
        var profissional = new ProfissionalModel();
        BeanUtils.copyProperties(profissionalRecordDTO, profissional);
        return ResponseEntity.status(HttpStatus.CREATED).body(profissionalService.salvar(profissional));
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> excluirProfissional(@PathVariable UUID id) {
        profissionalService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("id/{id}")
    public ResponseEntity<ProfissionalModel> atualizarProfissional(@PathVariable UUID id, @RequestBody @Valid ProfissionalRecordDTO profissionalRecordDTO) {
        ProfissionalModel profissional = profissionalService.pegarUm(id);
        BeanUtils.copyProperties(profissionalRecordDTO, profissional);
        return ResponseEntity.status(HttpStatus.OK).body(profissionalService.salvar(profissional));
    }
}
