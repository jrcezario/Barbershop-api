package com.personal.barbearia.controllers;

import com.personal.barbearia.dtos.ServicoDTO;
import com.personal.barbearia.services.ServicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ServicoDTO> servicoList() {
       return servicoService.list();
    }

    @GetMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServicoDTO getServico(@PathVariable Long id) {
        return servicoService.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoDTO createServico(@RequestBody @Valid ServicoDTO servicoDTO) {
        return servicoService.create(servicoDTO);
    }

    @DeleteMapping("id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServico(@PathVariable Long id) {
        servicoService.delete(id);
    }

    @PutMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServicoDTO updateServico(@PathVariable Long id, @RequestBody @Valid ServicoDTO servicoDTO) {
       return servicoService.update(id, servicoDTO);
    }
}
