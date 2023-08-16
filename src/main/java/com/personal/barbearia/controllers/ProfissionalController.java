package com.personal.barbearia.controllers;

import com.personal.barbearia.dtos.ProfissionalDTO;
import com.personal.barbearia.services.ProfissionalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/profissionais")
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProfissionalDTO> listarProfissionais() {
        return profissionalService.listar();
    }

    @GetMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProfissionalDTO pegarProfissional(@PathVariable UUID id) {
        return profissionalService.pegarUm(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfissionalDTO salvarProfissional(@RequestBody @Valid ProfissionalDTO profissionalDTO) {
        return profissionalService.salvar(profissionalDTO);
    }

    @DeleteMapping("id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirProfissional(@PathVariable UUID id) {
        profissionalService.deletar(id);
    }

    @PutMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProfissionalDTO atualizarProfissional(@PathVariable UUID id, @RequestBody @Valid ProfissionalDTO profissionalDTO) {
       return profissionalService.atualizar(id, profissionalDTO);
    }
}
