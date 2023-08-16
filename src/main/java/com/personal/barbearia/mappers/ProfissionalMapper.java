package com.personal.barbearia.mappers;

import com.personal.barbearia.dtos.ProfissionalDTO;
import com.personal.barbearia.models.Profissional;
import org.springframework.stereotype.Component;

@Component
public class ProfissionalMapper {

    public ProfissionalDTO toDTO(Profissional profissional) {
        if(profissional == null) {
            return null;
        }
        return new ProfissionalDTO(profissional.getId(), profissional.getNome(), profissional.getTelefone(), profissional.getEspecialidade());
    }

    public Profissional toEntity(ProfissionalDTO profissionalDTO) {
        if(profissionalDTO == null) {
            return null;
        }

        Profissional profissional = new Profissional();
        if(profissionalDTO.id() != null) {
            profissional.setId(profissionalDTO.id());
        }
        profissional.setNome(profissionalDTO.nome());
        profissional.setTelefone(profissionalDTO.telefone());
        profissional.setEspecialidade(profissionalDTO.especialidade());
        return profissional;
    }
}
