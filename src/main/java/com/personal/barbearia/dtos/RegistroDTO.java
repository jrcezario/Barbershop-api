package com.personal.barbearia.dtos;

import com.personal.barbearia.enums.Role;

public record RegistroDTO(String username, String password, Role role) {
}
