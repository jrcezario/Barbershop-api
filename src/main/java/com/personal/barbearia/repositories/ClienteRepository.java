package com.personal.barbearia.repositories;

import com.personal.barbearia.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

}
