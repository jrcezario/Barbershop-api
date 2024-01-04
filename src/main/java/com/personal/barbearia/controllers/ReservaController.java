package com.personal.barbearia.controllers;

import com.personal.barbearia.dtos.ReservaDto;
import com.personal.barbearia.dtos.ReservaPageDTO;
import com.personal.barbearia.services.ReservaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ReservaPageDTO reservaList(@RequestParam @PositiveOrZero int page, @RequestParam @Positive @Max(100) int size) {
        return reservaService.list(page, size);
    }

    @GetMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservaDto getReserva(@PathVariable @NotNull @Positive Long id) {
        return reservaService.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservaDto createReserva(@RequestBody @Valid @NotNull ReservaDto reservaDTO) {
        return reservaService.create(reservaDTO);
    }

    @DeleteMapping("id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReserva(@PathVariable @NotNull @Positive Long id) {
        reservaService.delete(id);
    }

    @PutMapping("id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservaDto updateReserva(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull ReservaDto reservaDTO) {
        return reservaService.update(id, reservaDTO);
    }
}
