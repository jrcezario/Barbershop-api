package com.personal.barbearia.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum TabelaDeErros {

    CLIENTE_NAO_ENCONTRADO("Cliente não encontrado", HttpStatus.NOT_FOUND),
    PROFISSIONAL_NAO_ENCONTRADO("Profissional não encontrado", HttpStatus.NOT_FOUND),
    RESERVA_NAO_ENCONTRADA("Reserva não encontrada", HttpStatus.NOT_FOUND),
    SERVICO_NAO_ENCONTRADO("Serviço não encontrado", HttpStatus.NOT_FOUND),
    ERRO_DE_VALIDACAO_BD("Erro de validação no banco de dados", HttpStatus.BAD_REQUEST);

    private final String mensagem;
    private final HttpStatus httpStatus;

    TabelaDeErros(String mensagem, HttpStatus httpStatus) {
        this.mensagem = mensagem;
        this.httpStatus = httpStatus;
    }
}
