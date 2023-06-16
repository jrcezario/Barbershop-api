package com.personal.barbearia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClienteRecordDTO(

        @NotBlank(message = "campo obrigatório")
        String nome,

        @NotBlank(message = "campo obrigatório")
        @Pattern(regexp = "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$", message = "formato incorreto, tente da seguinte maneira: (xx) xxxxx-xxxx, " +
                "onde os dois primeiros digitos são o DDD e logo após o número desejado")
        String telefone) {
}
