package com.personal.barbearia.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.personal.barbearia.models.ServicoModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ReservaDtoRequest {

        @NotNull(message = "campo obrigatório")
        private UUID clienteModelId;

        @NotNull(message = "campo obrigatório")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        private LocalDate dataReserva;

        @NotNull(message = "campo obrigatório")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
        private LocalTime horaReserva;

        @NotNull(message = "campo obrigatório")
        private List<ServicoModel> servicos;

        @NotNull(message = "campo obrigatório")
        private UUID profissionalModelId;

        @NotBlank(message = "campo obrigatório")
        private String descricao;
}
