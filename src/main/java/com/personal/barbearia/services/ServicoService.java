package com.personal.barbearia.services;

import com.personal.barbearia.dtos.ServicoDTO;
import com.personal.barbearia.enums.TabelaDeErros;
import com.personal.barbearia.exceptions.ErroDeNegocioException;
import com.personal.barbearia.mappers.ServicoMapper;
import com.personal.barbearia.models.Servico;
import com.personal.barbearia.repositories.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;

    ServicoMapper servicoMapper = Mappers.getMapper(ServicoMapper.class);

    public List<ServicoDTO> list() {
        return servicoRepository.findAll()
                .stream()
                .map(servico -> servicoMapper.toServicoDTO(servico))
                .collect(Collectors.toList());
    }

    public ServicoDTO getOne(Long id) {
        return servicoRepository.findById(id)
                .map(servico -> servicoMapper.toServicoDTO(servico))
                .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.SERVICO_NAO_ENCONTRADO));
    }

    public ServicoDTO create(ServicoDTO servicoDTO) {
        Servico servico = servicoMapper.toServicoEntity(servicoDTO);
        return servicoMapper.toServicoDTO(servicoRepository.save(servico));
    }

    public void delete(Long id) {
        servicoRepository.delete(
                servicoRepository.findById(id)
                        .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.SERVICO_NAO_ENCONTRADO)));
    }

    public ServicoDTO update(Long id, ServicoDTO servicoDTO) {
        return servicoRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNomeServico(servicoDTO.nome());
                    recordFound.setValorServico(servicoDTO.valor());
                    recordFound.setDescricaoServico(servicoDTO.descricao());
                    return servicoMapper.toServicoDTO(servicoRepository.save(recordFound));
                }).orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.SERVICO_NAO_ENCONTRADO));
    }

}
