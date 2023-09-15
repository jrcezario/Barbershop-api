package com.personal.barbearia.repositories;

import com.personal.barbearia.models.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
}
