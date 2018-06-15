package br.harlan.sbi.controllers.exceptions;

import br.harlan.sbi.response.Response;
import br.harlan.sbi.response.StandardError;
import br.harlan.sbi.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Response> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        StandardError standardError = new StandardError();
        standardError.setStatus(HttpStatus.NOT_FOUND.value());
        standardError.setMessage(e.getMessage());
        standardError.setTimeStamp(System.currentTimeMillis());
        Response response = new Response();
        response.getErrors().add(standardError);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
