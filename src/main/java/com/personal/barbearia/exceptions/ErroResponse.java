package com.personal.barbearia.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ErroResponse implements Serializable {

    public final static long seriaVersionUID = 1L;

    private String mensagem;
    private HttpStatus httpStatus;
}
