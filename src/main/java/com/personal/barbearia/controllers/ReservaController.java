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
    public List<ReservaDTO> reservaList() {
        return reservaService.list();
    }

    @GetMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservaDTO getReserva(@PathVariable UUID id) {
        return reservaService.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservaDTO createReserva(@RequestBody @Valid ReservaDTO reservaDTO) {
       return reservaService.create(reservaDTO);
    }

    @DeleteMapping("id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReserva(@PathVariable UUID id) {
        reservaService.delete(id);
    }

    @PutMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservaDTO updateReserva(@PathVariable UUID id, @RequestBody @Valid ReservaDTO reservaDTO) {
        return reservaService.update(id, reservaDTO);
    }
}
