package br.com.leoat.starwarsapi.controller.advice;

import br.com.leoat.starwarsapi.exception.ResourceNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Controller Advice responsável para gerenciar as mensagens de erro esperadas na API.
 */
@ControllerAdvice
public class GlobalErrorHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundExceptionHandler(ResourceNotFoundException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Object> duplicateKeyExceptionHandler(DuplicateKeyException e, WebRequest request) {
        return handleExceptionInternal(e, "Duplicate key found in the resource.", new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> otherExceptionHandler(Exception e, WebRequest request) {
        return handleExceptionInternal(
                e, "An unexpected error ocurred, please try again later.",
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
