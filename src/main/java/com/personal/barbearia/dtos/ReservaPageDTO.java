package com.personal.barbearia.dtos;

import java.util.List;

public record ReservaPageDTO(
        List<ReservaDto> reservas, long totalElements, int totalPages) {
}
