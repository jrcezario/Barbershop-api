package com.personal.barbearia.services;

import com.personal.barbearia.dtos.ServicoDTO;
import com.personal.barbearia.enums.TabelaDeErros;
import com.personal.barbearia.exceptions.ErroDeNegocioException;
import com.personal.barbearia.mappers.ServicoMapper;
import com.personal.barbearia.models.Servico;
import com.personal.barbearia.repositories.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final ServicoMapper servicoMapper;

    public ServicoService(ServicoRepository servicoRepository, ServicoMapper servicoMapper) {
        this.servicoRepository = servicoRepository;
        this.servicoMapper = servicoMapper;
    }

    public List<ServicoDTO> listar() {
        return servicoRepository.findAll()
                .stream()
                .map(servico -> servicoMapper.toDTO(servico))
                .collect(Collectors.toList());
    }

    public ServicoDTO pegarUm(UUID id) {
        return servicoRepository.findById(id)
                .map(servico -> servicoMapper.toDTO(servico))
                .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.SERVICO_NAO_ENCONTRADO));
    }

    public ServicoDTO salvar(ServicoDTO servicoDTO) {
        Servico servico = servicoMapper.toEntity(servicoDTO);
        return servicoMapper.toDTO(servicoRepository.save(servico));
    }

    public void deletar(UUID id) {
        servicoRepository.delete(
                servicoRepository.findById(id)
                        .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.SERVICO_NAO_ENCONTRADO)));
    }

    public ServicoDTO atualizar(UUID id, ServicoDTO servicoDTO) {
        return servicoRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setNome(servicoDTO.nome());
                    recordFound.setValor(servicoDTO.valor());
                    recordFound.setDescricao(servicoDTO.descricao());
                    return servicoMapper.toDTO(servicoRepository.save(recordFound));
                }).orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.SERVICO_NAO_ENCONTRADO));
    }

}
