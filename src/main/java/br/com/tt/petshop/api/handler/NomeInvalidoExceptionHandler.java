package br.com.tt.petshop.api.handler;

import br.com.tt.petshop.api.exception.NomeInvalidoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class NomeInvalidoExceptionHandler {

    @ExceptionHandler(NomeInvalidoException.class)
    public ResponseEntity<ApiError> tratar(NomeInvalidoException e){
        ApiError apiError = new ApiError("nome_invalido",e.getMessage());
        return ResponseEntity
                .unprocessableEntity()
                .body(apiError);
    }
}
