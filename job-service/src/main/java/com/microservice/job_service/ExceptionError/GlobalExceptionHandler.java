package com.microservice.job_service.ExceptionError;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        // Retourner uniquement le message d'erreur
        return new ResponseEntity<>(ex.getReason(), ex.getStatusCode());
    }

    // Cette classe Java est Creer pour l'ajout dun Job , faudrait au prealable k le Compagnyid existe
}