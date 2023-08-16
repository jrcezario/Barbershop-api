package com.personal.barbearia.controllers;

import com.personal.barbearia.dtos.ServicoDTO;
import com.personal.barbearia.services.ServicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ServicoDTO> listarServicos() {
       return servicoService.listar();
    }

    @GetMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServicoDTO pegarServico(@PathVariable UUID id) {
        return servicoService.pegarUm(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoDTO salvarServico(@RequestBody @Valid ServicoDTO servicoDTO) {
        return servicoService.salvar(servicoDTO);
    }

    @DeleteMapping("id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirServico(@PathVariable UUID id) {
        servicoService.deletar(id);
    }

    @PutMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServicoDTO atualizarServico(@PathVariable UUID id, @RequestBody @Valid ServicoDTO servicoDTO) {
       return servicoService.atualizar(id, servicoDTO);
    }
}
