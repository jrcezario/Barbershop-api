package com.personal.barbearia.repositories;

import com.personal.barbearia.models.ServicoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServicoRepository extends JpaRepository<ServicoModel, UUID> {
}
