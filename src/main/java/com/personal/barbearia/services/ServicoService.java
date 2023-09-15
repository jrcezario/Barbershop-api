package com.personal.barbearia.services.impl;

import com.personal.barbearia.dtos.ServicoDTO;
import com.personal.barbearia.enums.TabelaDeErros;
import com.personal.barbearia.exceptions.ErroDeNegocioException;
import com.personal.barbearia.mappers.IServicoMapper;
import com.personal.barbearia.models.Servico;
import com.personal.barbearia.repositories.ServicoRepository;
import com.personal.barbearia.services.IServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicoServiceImpl implements IServicoService {

    ServicoRepository servicoRepository;
    IServicoMapper servicoMapper;

    @Override
    public List<ServicoDTO> list() {
        return servicoRepository.findAll()
                .stream()
                .map(servico -> servicoMapper.toServicoDTO(servico))
                .collect(Collectors.toList());
    }

    @Override
    public ServicoDTO getOne(Long id) {
        return servicoRepository.findById(id)
                .map(servico -> servicoMapper.toServicoDTO(servico))
                .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.SERVICO_NAO_ENCONTRADO));
    }

    @Override
    public ServicoDTO create(ServicoDTO servicoDTO) {
        Servico servico = servicoMapper.toServicoEntity(servicoDTO);
        return servicoMapper.toServicoDTO(servicoRepository.save(servico));
    }

    @Override
    public void delete(Long id) {
        servicoRepository.delete(
                servicoRepository.findById(id)
                        .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.SERVICO_NAO_ENCONTRADO)));
    }

    @Override
    public ServicoDTO update(Long id, ServicoDTO servicoDTO) {
        return servicoRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(servicoDTO.nomeServico());
                    recordFound.setValor(servicoDTO.valorServico());
                    recordFound.setDescricao(servicoDTO.descricaoServico());
                    return servicoMapper.toServicoDTO(servicoRepository.save(recordFound));
                }).orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.SERVICO_NAO_ENCONTRADO));
    }

}
