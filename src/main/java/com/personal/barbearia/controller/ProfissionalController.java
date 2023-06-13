package com.personal.barbearia.controller;

import com.personal.barbearia.model.ProfissionalModel;
import com.personal.barbearia.service.ProfissionalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("profissionais")
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    @GetMapping
    public List<ProfissionalModel> listarProfissionais() {
        return this.profissionalService.listar();
    }

    @GetMapping("id/{id}")
    public ProfissionalModel pegarProfissional(@PathVariable UUID id) {
        return this.profissionalService.pegarUm(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProfissionalModel salvarProfissional(@RequestBody ProfissionalModel profissionalModel) {
        return this.profissionalService.salvar(profissionalModel);
    }

    @DeleteMapping("id/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluirProfissional(@PathVariable UUID id) {
        this.profissionalService.deletar(id);
    }

    @PutMapping("id/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void atualizarProfissional(@PathVariable UUID id, @RequestBody ProfissionalModel profissionalModel) {
        this.profissionalService.atualizar(id, profissionalModel);
    }
}
