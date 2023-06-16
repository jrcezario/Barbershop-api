package com.personal.barbearia.controller;

import com.personal.barbearia.dto.ReservaRecordDTO;
import com.personal.barbearia.model.ReservaModel;
import com.personal.barbearia.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<ReservaModel>> listarReservas() {
        List<ReservaModel> listaReservas = reservaService.listar();
        if(!listaReservas.isEmpty()) {
            for(ReservaModel reserva : listaReservas) {
                UUID id = reserva.getId();
                reserva.add(linkTo(methodOn(ReservaController.class).pegarReserva(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(listaReservas);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<ReservaModel> pegarReserva(@PathVariable UUID id) {
        ReservaModel reserva = reservaService.pegarUma(id);
        reserva.add(linkTo(methodOn(ReservaController.class).listarReservas()).withRel("Lista de Reservas"));
        return ResponseEntity.status(HttpStatus.OK).body(reserva);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<ReservaModel> salvarReserva(@RequestBody @Valid ReservaRecordDTO reservaRecordDTO) {
        var reserva = new ReservaModel();
        BeanUtils.copyProperties(reservaRecordDTO, reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.salvar(reserva));
    }

    @DeleteMapping("id/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> excluirReserva(@PathVariable UUID id) {
        reservaService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("id/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public ResponseEntity<ReservaModel> atualizarReserva(@PathVariable UUID id, @RequestBody @Valid ReservaRecordDTO reservaRecordDTO) {
        ReservaModel reserva = reservaService.pegarUma(id);
        BeanUtils.copyProperties(reservaRecordDTO, reserva);
        return ResponseEntity.status(HttpStatus.OK).body(reservaService.salvar(reserva));
    }

}
