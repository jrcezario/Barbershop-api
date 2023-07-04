package com.personal.barbearia.services;

import com.personal.barbearia.enums.TabelaDeErros;
import com.personal.barbearia.exceptions.ErroDeNegocioException;
import com.personal.barbearia.models.ProfissionalModel;
import com.personal.barbearia.repositories.ProfissionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;

    public ProfissionalService(ProfissionalRepository profissionalRepository) {
        this.profissionalRepository = profissionalRepository;
    }

    public List<ProfissionalModel> listar() {
        return profissionalRepository.findAll();
    }

    public ProfissionalModel pegarUm(UUID id) {
        return profissionalRepository.findById(id)
                .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.PROFISSIONAL_NAO_ENCONTRADO));
    }

    public ProfissionalModel salvar(ProfissionalModel profissionalModel) {
        return profissionalRepository.save(profissionalModel);
    }

    public void deletar(UUID id) {
        profissionalRepository.delete(
                profissionalRepository.findById(id)
                        .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.PROFISSIONAL_NAO_ENCONTRADO)));
    }

}
