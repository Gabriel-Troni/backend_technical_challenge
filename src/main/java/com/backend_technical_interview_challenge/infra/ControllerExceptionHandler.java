package com.backend_technical_interview_challenge.infra;

import com.backend_technical_interview_challenge.DTO.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.Response;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntry (DataIntegrityViolationException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("Erro: Usuário já cadastrado.", "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity threat403 (HttpClientErrorException.Forbidden exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("Erro: Não autorizado.", "403");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404 (EntityNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralException (Exception exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }
}
