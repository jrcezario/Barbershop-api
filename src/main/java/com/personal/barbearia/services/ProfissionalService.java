package com.personal.barbearia.services;

import com.personal.barbearia.dtos.ProfissionalDto;
import com.personal.barbearia.exceptions.RecordNotFoundException;
import com.personal.barbearia.mappers.ProfissionalMapper;
import com.personal.barbearia.models.Profissional;
import com.personal.barbearia.repositories.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;
    private final ProfissionalMapper profissionalMapper;

    public List<ProfissionalDto> list() {
        return profissionalRepository.findAll()
                .stream()
                .map(profissional -> ProfissionalMapper.INSTANCE.toProfissionalDto(profissional))
                .collect(Collectors.toList());
    }

    public ProfissionalDto getOne(Long id) {
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
        return ProfissionalMapper.INSTANCE.toProfissionalDto(profissional);
    }

    public ProfissionalDto create(ProfissionalDto profissionalDTO) {
        Profissional profissional = profissionalMapper.toProfissionalEntity(profissionalDTO);
        return ProfissionalMapper.INSTANCE.toProfissionalDto(profissionalRepository.save(profissional));
    }

    public void delete(Long id) {
        profissionalRepository.delete(
                profissionalRepository.findById(id)
                        .orElseThrow(() -> new RecordNotFoundException(id)));
    }

    public ProfissionalDto update(Long id, ProfissionalDto profissionalDTO) {
        return profissionalRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(profissionalDTO.nome());
                    recordFound.setTelefone(profissionalDTO.telefone());
                    return ProfissionalMapper.INSTANCE.toProfissionalDto(profissionalRepository.save(recordFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }
}
