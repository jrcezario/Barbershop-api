package com.personal.barbearia.controllers;

import com.personal.barbearia.dtos.ProfissionalDto;
import com.personal.barbearia.services.ProfissionalService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("api/profissionais")
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProfissionalDto> profissionalList() {
        return profissionalService.list();
    }

    @GetMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProfissionalDto getProfissional(@PathVariable @Positive @NotNull Long id) {
        return profissionalService.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfissionalDto createProfissional(@RequestBody @Valid @NotNull ProfissionalDto profissionalDTO) {
        return profissionalService.create(profissionalDTO);
    }

    @DeleteMapping("id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfissional(@PathVariable @Positive @NotNull Long id) {
        profissionalService.delete(id);
    }

    @PutMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProfissionalDto updateProfissional(@PathVariable @Positive @NotNull Long id, @RequestBody @Valid @NotNull ProfissionalDto profissionalDTO) {
        return profissionalService.update(id, profissionalDTO);
    }
}
