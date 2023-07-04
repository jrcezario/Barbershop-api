package com.personal.barbearia.exceptions;

import com.personal.barbearia.enums.TabelaDeErros;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    // Validação de dados no body da requisição
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return handleExceptionInternal(exception, errors, headers, status, request);
    }

    // Tratamento de problemas relacionados ao bancos de dados
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        if (ex.getCause() instanceof ConstraintViolationException) {

            TabelaDeErros tabelaDeErros = TabelaDeErros.ERRO_DE_VALIDACAO_BD;

            ErroResponse response = new ErroResponse();
            response.setHttpStatus(tabelaDeErros.getHttpStatus());
            response.setMensagem(tabelaDeErros.getMensagem());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
    }

    // Tratamento de erros na resposta do servidor
    @ExceptionHandler(ErroDeNegocioException.class)
    public ResponseEntity<Object> handleErroDeNegocioException(ErroDeNegocioException ex) {

        ErroResponse response = new ErroResponse();
        response.setMensagem(ex.getMensagem());
        response.setHttpStatus(ex.getHttpStatus());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
