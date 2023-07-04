package com.personal.barbearia.repositories;

import com.personal.barbearia.models.ReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReservaRepository extends JpaRepository<ReservaModel, UUID> {
}
