package br.harlan.sbi.resources.handlers;

import br.harlan.sbi.response.FieldMessage;
import br.harlan.sbi.response.Response;
import br.harlan.sbi.response.StandardError;
import br.harlan.sbi.response.ValidationError;
import br.harlan.sbi.services.exceptions.DataIntegrityException;
import br.harlan.sbi.services.exceptions.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceExceptionHandler.class);

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Response> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        LOGGER.error("ObjectNotFoundException: {}", e.getMessage());
        StandardError standardError = new StandardError();
        standardError.setStatus(HttpStatus.NOT_FOUND.value());
        standardError.setMessage(e.getMessage());
        if (e.getCause() != null)
            standardError.setCause(e.getCause().toString());
        standardError.setTimeStamp(System.currentTimeMillis());
        Response response = new Response();
        response.getErrors().add(standardError);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<Response> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
        LOGGER.error("DataIntegrityException: {}", e.getMessage());
        StandardError standardError = new StandardError();
        standardError.setStatus(HttpStatus.BAD_REQUEST.value());
        standardError.setMessage(e.getMessage());
        if (e.getCause() != null)
            standardError.setCause(e.getCause().toString());
        standardError.setTimeStamp(System.currentTimeMillis());
        Response response = new Response();
        response.getErrors().add(standardError);
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> notValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        LOGGER.error("MethodArgumentNotValidException: {}", e.getMessage());
        String message = "An error occurred in the validation of the data";
        ValidationError validationError = new ValidationError(
                HttpStatus.BAD_REQUEST.value(), message, System.currentTimeMillis(), null);
        if (e.getCause() != null)
            validationError.setCause(e.getCause().toString());
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            validationError.getFieldMessages().add(new FieldMessage(fieldError.getField(), fieldError.getDefaultMessage()));
        });
        Response response = new Response();
        response.getErrors().add(validationError);
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
    }
}
