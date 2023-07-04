package com.personal.barbearia.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfissionalDtoRequest {

        @NotBlank(message = "campo obrigatório")
        private String nome;

        @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$", message = "formato incorreto, tente da seguinte maneira: (xx) xxxxx-xxxx")
        private String telefone;

        @NotBlank(message = "campo obrigatório")
        private String especialidade;
}
