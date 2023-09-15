package com.personal.barbearia.services;

import com.personal.barbearia.dtos.ProfissionalDTO;
import com.personal.barbearia.enums.TabelaDeErros;
import com.personal.barbearia.exceptions.ErroDeNegocioException;
import com.personal.barbearia.mappers.ProfissionalMapper;
import com.personal.barbearia.models.Profissional;
import com.personal.barbearia.repositories.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;

    ProfissionalMapper profissionalMapper = Mappers.getMapper(ProfissionalMapper.class);

    public List<ProfissionalDTO> list() {
        return profissionalRepository.findAll()
                .stream()
                .map(profissional -> profissionalMapper.toProfissionalDTO(profissional))
                .collect(Collectors.toList());
    }

    public ProfissionalDTO getOne(Long id) {
        return profissionalRepository.findById(id)
                .map(profissional -> profissionalMapper.toProfissionalDTO(profissional))
                .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.PROFISSIONAL_NAO_ENCONTRADO));
    }

    public ProfissionalDTO create(ProfissionalDTO profissionalDTO) {
        Profissional profissional = profissionalMapper.toProfissionalEntity(profissionalDTO);
        return profissionalMapper.toProfissionalDTO(profissionalRepository.save(profissional));
    }

    public void delete(Long id) {
        profissionalRepository.delete(
                profissionalRepository.findById(id)
                        .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.PROFISSIONAL_NAO_ENCONTRADO)));
    }

    public ProfissionalDTO update(Long id, ProfissionalDTO profissionalDTO) {
        return profissionalRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNomeProfissional(profissionalDTO.nome());
                    recordFound.setTelefoneProfissional(profissionalDTO.telefone());
                    return profissionalMapper.toProfissionalDTO(profissionalRepository.save(recordFound));
                }).orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.PROFISSIONAL_NAO_ENCONTRADO));
    }
}
