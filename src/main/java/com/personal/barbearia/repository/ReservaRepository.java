package com.personal.barbearia.repository;

import com.personal.barbearia.model.ReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservaRepository extends JpaRepository<ReservaModel, UUID> {
}
