package com.personal.barbearia.controllers;

import com.personal.barbearia.dtos.ProfissionalDTO;
import com.personal.barbearia.services.ProfissionalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/profissionais")
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProfissionalDTO> profissionalList() {
        return profissionalService.list();
    }

    @GetMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProfissionalDTO getProfissional(@PathVariable Long id) {
        return profissionalService.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfissionalDTO createProfissional(@RequestBody @Valid ProfissionalDTO profissionalDTO) {
        return profissionalService.create(profissionalDTO);
    }

    @DeleteMapping("id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfissional(@PathVariable Long id) {
        profissionalService.delete(id);
    }

    @PutMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProfissionalDTO updateProfissional(@PathVariable Long id, @RequestBody @Valid ProfissionalDTO profissionalDTO) {
       return profissionalService.update(id, profissionalDTO);
    }
}
