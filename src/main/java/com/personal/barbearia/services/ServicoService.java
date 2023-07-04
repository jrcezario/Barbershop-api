package com.personal.barbearia.services;

import com.personal.barbearia.enums.TabelaDeErros;
import com.personal.barbearia.exceptions.ErroDeNegocioException;
import com.personal.barbearia.models.ServicoModel;
import com.personal.barbearia.repositories.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public List<ServicoModel> listar() {
        return servicoRepository.findAll();
    }

    public ServicoModel pegarUm(UUID id) {
        return servicoRepository.findById(id)
                .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.SERVICO_NAO_ENCONTRADO));
    }

    public ServicoModel salvar(ServicoModel servicoModel) {
        return servicoRepository.save(servicoModel);
    }

    public void deletar(UUID id) {
        servicoRepository.delete(
                servicoRepository.findById(id)
                        .orElseThrow(() -> new ErroDeNegocioException(TabelaDeErros.SERVICO_NAO_ENCONTRADO)));
    }

}
