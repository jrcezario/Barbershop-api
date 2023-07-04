package com.personal.barbearia.exceptions;

import com.personal.barbearia.enums.TabelaDeErros;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
public class ErroDeNegocioException extends RuntimeException implements Serializable {

    public final static long seriaVersionUID = 1L;

    private final String mensagem;
    private final HttpStatus httpStatus;

    public ErroDeNegocioException(TabelaDeErros tabelaDeErros) {
        this.mensagem = tabelaDeErros.getMensagem();
        this.httpStatus = tabelaDeErros.getHttpStatus();
    }
}
