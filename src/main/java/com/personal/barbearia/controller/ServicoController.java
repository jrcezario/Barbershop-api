package com.personal.barbearia.controller;

import com.personal.barbearia.model.ServicoModel;
import com.personal.barbearia.service.ServicoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("servicos")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public List<ServicoModel> listarServicos() {
        return this.servicoService.list();
    }

    @GetMapping("id/{id}")
    public ServicoModel pegarServico(@PathVariable UUID id) {
       return this.servicoService.pegarUm(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoModel salvarServico(@RequestBody ServicoModel servicoModel) {
       return this.servicoService.salvar(servicoModel);
    }

    @DeleteMapping("id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirServico(@PathVariable UUID id) {
        this.servicoService.deletar(id);
    }

    @PutMapping("id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarServico(@PathVariable UUID id, @RequestBody ServicoModel servicoModel) {
        this.servicoService.atualizar(id, servicoModel);
    }



}
