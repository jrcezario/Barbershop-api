package com.personal.barbearia.controller;

import com.personal.barbearia.dto.ServicoRecordDTO;
import com.personal.barbearia.model.ServicoModel;
import com.personal.barbearia.service.ServicoService;
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
@RequestMapping("servicos")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public ResponseEntity<List<ServicoModel>> listarServicos() {
        List<ServicoModel> listaServicos = servicoService.list();
        if(!listaServicos.isEmpty()) {
            for(ServicoModel servico : listaServicos) {
                UUID id = servico.getId();
                servico.add(linkTo(methodOn(ServicoController.class).pegarServico(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(listaServicos);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<ServicoModel> pegarServico(@PathVariable UUID id) {
        ServicoModel servico = servicoService.pegarUm(id);
        servico.add(linkTo(methodOn(ServicoController.class).listarServicos()).withRel("Lista de Serviços"));
        return ResponseEntity.status(HttpStatus.OK).body(servico);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ServicoModel> salvarServico(@RequestBody @Valid ServicoRecordDTO servicoRecordDTO) {
        var servico = new ServicoModel();
        BeanUtils.copyProperties(servicoRecordDTO, servico);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoService.salvar(servico));
    }

    @DeleteMapping("id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> excluirServico(@PathVariable UUID id) {
        servicoService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ServicoModel> atualizarServico(@PathVariable UUID id, @RequestBody @Valid ServicoRecordDTO servicoRecordDTO) {
        ServicoModel servico = servicoService.pegarUm(id);
        BeanUtils.copyProperties(servicoRecordDTO, servico);
        return ResponseEntity.status(HttpStatus.OK).body(servicoService.salvar(servico));
    }



}
