package com.personal.barbearia.controllers;

import com.personal.barbearia.dtos.ReservaDTO;
import com.personal.barbearia.services.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReservaDTO> listarReservas() {
        return reservaService.listar();
    }

    @GetMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservaDTO pegarReserva(@PathVariable UUID id) {
        return reservaService.pegarUma(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservaDTO salvarReserva(@RequestBody @Valid ReservaDTO reservaDTO) {
       return reservaService.salvar(reservaDTO);
    }

    @DeleteMapping("id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirReserva(@PathVariable UUID id) {
        reservaService.deletar(id);
    }

    @PutMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservaDTO atualizarReserva(@PathVariable UUID id, @RequestBody @Valid ReservaDTO reservaDTO) {
        return reservaService.atualizar(id, reservaDTO);
    }
}
