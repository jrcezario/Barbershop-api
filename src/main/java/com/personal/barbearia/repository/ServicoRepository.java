package com.personal.barbearia.repository;

import com.personal.barbearia.model.ServicoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServicoRepository extends JpaRepository<ServicoModel, UUID> {
}
