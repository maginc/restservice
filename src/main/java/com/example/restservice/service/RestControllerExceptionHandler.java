package com.example.restservice.service;

import com.example.restservice.persistance.model.RequestLogEntry;
import com.example.restservice.persistance.repositories.RequestLogEntryRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
        This class sole purpose is to save error log in database,
        and return exception message
 */

@ControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    final RequestLogEntryRepository requestLogEntryRepository;

    public RestControllerExceptionHandler(RequestLogEntryRepository requestLogEntryRepository) {
        this.requestLogEntryRepository = requestLogEntryRepository;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        String[] strings = request.getDescription(true).split(";");
        int id = Integer.parseInt(strings[0].substring(strings[0].lastIndexOf("/") + 1));

        requestLogEntryRepository.save(new RequestLogEntry(id, false));

        return new ResponseEntity<>(ex.getLocalizedMessage(), status);
    }

}
