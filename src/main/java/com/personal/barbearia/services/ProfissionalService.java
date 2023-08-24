package com.personal.barbearia.services;

import com.personal.barbearia.dtos.ProfissionalDTO;
import com.personal.barbearia.enums.TabelaDeErros;
import com.personal.barbearia.exceptions.ErroDeNegocioException;
import com.personal.barbearia.mappers.ProfissionalMapper;
import com.personal.barbearia.models.Profissional;
import com.personal.barbearia.repositories.ProfissionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;
    private final ProfissionalMapper profissionalMapper;

    public ProfissionalService(ProfissionalRepository profissionalRepository, ProfissionalMapper profissionalMapper) {
        this.profissionalRepository = profissionalRepository;
        this.profissionalMapper = profissionalMapper;
    }

    public List<ProfissionalDTO> list() {
        return profissionalRepository.findAll()
                .stream()
                .map(profissional -> profissionalMapper.toDTO(profissional))
                .collect(Collectors.toList());
    }

    public ProfissionalDTO getOne(UUID id) {
        return profissionalRepository.findById(id)
                .map(profissional -> profissionalMapper.toDTO(profissional))
                .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.PROFISSIONAL_NAO_ENCONTRADO));
    }

    public ProfissionalDTO create(ProfissionalDTO profissionalDTO) {
        Profissional profissional = profissionalMapper.toEntity(profissionalDTO);
        return profissionalMapper.toDTO(profissionalRepository.save(profissional));
    }

    public void delete(UUID id) {
        profissionalRepository.delete(
                profissionalRepository.findById(id)
                        .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.PROFISSIONAL_NAO_ENCONTRADO)));
    }

    public ProfissionalDTO update(UUID id, ProfissionalDTO profissionalDTO) {
        return profissionalRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(profissionalDTO.nome());
                    recordFound.setTelefone(profissionalDTO.telefone());
                    recordFound.setEspecialidade(profissionalDTO.especialidade());
                    return profissionalMapper.toDTO(profissionalRepository.save(recordFound));
                }).orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.PROFISSIONAL_NAO_ENCONTRADO));
    }
}
