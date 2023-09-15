package com.personal.barbearia.services.impl;

import com.personal.barbearia.dtos.ProfissionalDTO;
import com.personal.barbearia.enums.TabelaDeErros;
import com.personal.barbearia.exceptions.ErroDeNegocioException;
import com.personal.barbearia.mappers.IProfissionalMapper;
import com.personal.barbearia.models.Profissional;
import com.personal.barbearia.repositories.ProfissionalRepository;
import com.personal.barbearia.services.IProfissionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfissionalServiceImpl implements IProfissionalService {

    ProfissionalRepository profissionalRepository;
    IProfissionalMapper profissionalMapper;

    @Override
    public List<ProfissionalDTO> list() {
        return profissionalRepository.findAll()
                .stream()
                .map(profissional -> profissionalMapper.toProfissionalDTO(profissional))
                .collect(Collectors.toList());
    }

    @Override
    public ProfissionalDTO getOne(Long id) {
        return profissionalRepository.findById(id)
                .map(profissional -> profissionalMapper.toProfissionalDTO(profissional))
                .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.PROFISSIONAL_NAO_ENCONTRADO));
    }

    @Override
    public ProfissionalDTO create(ProfissionalDTO profissionalDTO) {
        Profissional profissional = profissionalMapper.toProfissionalEntity(profissionalDTO);
        return profissionalMapper.toProfissionalDTO(profissionalRepository.save(profissional));
    }

    @Override
    public void delete(Long id) {
        profissionalRepository.delete(
                profissionalRepository.findById(id)
                        .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.PROFISSIONAL_NAO_ENCONTRADO)));
    }

    @Override
    public ProfissionalDTO update(Long id, ProfissionalDTO profissionalDTO) {
        return profissionalRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(profissionalDTO.nomeProfissional());
                    recordFound.setTelefone(profissionalDTO.telefoneProfissional());
                    return profissionalMapper.toProfissionalDTO(profissionalRepository.save(recordFound));
                }).orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.PROFISSIONAL_NAO_ENCONTRADO));
    }
}
