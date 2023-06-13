package com.personal.barbearia.repository;

import com.personal.barbearia.model.ProfissionalModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfissionalRepository extends JpaRepository<ProfissionalModel, UUID> {
}
