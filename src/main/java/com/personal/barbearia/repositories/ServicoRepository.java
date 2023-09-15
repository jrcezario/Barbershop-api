package com.personal.barbearia.repositories;

import com.personal.barbearia.models.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}
