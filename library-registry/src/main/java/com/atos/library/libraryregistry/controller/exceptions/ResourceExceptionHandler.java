package com.atos.library.libraryregistry.controller.exceptions;

import com.atos.library.libraryregistry.service.exceptions.DataViolationException;
import com.atos.library.libraryregistry.service.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> ObjectNotFound(ObjectNotFoundException ex, HttpServletRequest req){
        StandardError error =
                new StandardError(LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage(),
                        req.getRequestURI());
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataViolationException.class)
    public ResponseEntity<StandardError> DataIntegrityViolation(DataViolationException ex, HttpServletRequest req){
        StandardError error =
                new StandardError(LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage(),
                        req.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
