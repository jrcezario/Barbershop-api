package com.personal.barbearia.mappers;

import com.personal.barbearia.dtos.ServicoDTO;
import com.personal.barbearia.models.Servico;
import org.springframework.stereotype.Component;

@Component
public class ServicoMapper {

    public ServicoDTO toDTO(Servico servico) {
        if(servico == null) {
            return null;
        }
        return new ServicoDTO(servico.getId(), servico.getNome(), servico.getValor(), servico.getDescricao());
    }

    public Servico toEntity(ServicoDTO servicoDTO) {
        if(servicoDTO == null) {
            return null;
        }

        Servico servico = new Servico();
        if(servicoDTO.id() != null) {
            servico.setId(servicoDTO.id());
        }
        servico.setNome(servicoDTO.nome());
        servico.setValor(servicoDTO.valor());
        servico.setDescricao(servicoDTO.descricao());
        return servico;
    }
}
