package com.personal.barbearia.controllers;

import com.personal.barbearia.dtos.request.ReservaDtoRequest;
import com.personal.barbearia.dtos.response.ReservaDtoResponse;
import com.personal.barbearia.mappers.ReservaMapper;
import com.personal.barbearia.models.ReservaModel;
import com.personal.barbearia.services.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("reservas")
public class ReservaController {

    @Autowired
    ReservaService reservaService;

    @Autowired
    ReservaMapper reservaMapper;

    @GetMapping
    public ResponseEntity<List<ReservaDtoResponse>> listarReservas() {
        List<ReservaModel> listaReservas = reservaService.listar();
        List<ReservaDtoResponse> listResponse = new ArrayList<>();

        if(!listaReservas.isEmpty()) {
            for(ReservaModel reserva : listaReservas) {
                ReservaDtoResponse response = reservaMapper.toReservaDtoResponse(reserva);
                UUID id = response.getId();
                response.add(linkTo(methodOn(ReservaController.class).pegarReserva(id)).withSelfRel());
                listResponse.add(response);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(listResponse);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<ReservaDtoResponse> pegarReserva(@PathVariable UUID id) {
        ReservaModel reserva = reservaService.pegarUma(id);
        ReservaDtoResponse response = reservaMapper.toReservaDtoResponse(reserva);
        Link link = linkTo(methodOn(ReservaController.class).listarReservas()).withRel("Lista de Reservas");
        response.add(link);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<ReservaDtoResponse> salvarReserva(@RequestBody @Valid ReservaDtoRequest reservaDTORequest) {
        ReservaModel reserva = reservaMapper.toReservaModel(reservaDTORequest);
        ReservaDtoResponse response = reservaMapper.toReservaDtoResponse(reservaService.salvar(reserva));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> excluirReserva(@PathVariable UUID id) {
        reservaService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("id/{id}")
    public ResponseEntity<ReservaDtoResponse> atualizarReserva(@PathVariable UUID id, @RequestBody @Valid ReservaDtoRequest reservaDTORequest) {
        ReservaModel reserva = reservaService.pegarUma(id);
        reservaMapper.toCopyProperties(reservaDTORequest, reserva);
        ReservaDtoResponse response = reservaMapper.toReservaDtoResponse(reservaService.salvar(reserva));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
