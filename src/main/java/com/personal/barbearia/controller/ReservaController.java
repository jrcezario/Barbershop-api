package com.personal.barbearia.controller;

import com.personal.barbearia.model.ReservaModel;
import com.personal.barbearia.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public List<ReservaModel> listarReservar() {
        return this.reservaService.listar();
    }

    @GetMapping("id/{id}")
    public ReservaModel pegarReserva(@PathVariable UUID id) {
        return  this.reservaService.pegarUma(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ReservaModel salvarReserva(@RequestBody ReservaModel reservaModel) {
        return this.reservaService.salvar(reservaModel);
    }

    @DeleteMapping("id/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void excluirReserva(@PathVariable UUID id) {
        this.reservaService.deletar(id);
    }

    @PutMapping("id/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void atualizarReserva(@PathVariable UUID id, @RequestBody ReservaModel reservaModel) {
        this.reservaService.atualizar(id, reservaModel);
    }

}
