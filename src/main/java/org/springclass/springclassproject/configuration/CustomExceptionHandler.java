package org.springclass.springclassproject.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springclass.springclassproject.exception.PlateformApiDataValidationException;
import org.springclass.springclassproject.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        //Response header
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //Response body
        final Map<String, String> content = new LinkedHashMap<>();
        content.put("timestamp", String.valueOf(System.currentTimeMillis()));
        content.put("status", String.valueOf(HttpStatus.NOT_FOUND.value()));
        content.put("message", ex.getMessage());
        content.put("path", request.getDescription(false));
        try {
            return new ResponseEntity<>(new ObjectMapper().writeValueAsString(content), headers, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(ex.getMessage(), e);
            return new ResponseEntity<>(new HashMap<>(), headers, HttpStatus.NOT_FOUND);
        }
    }

    @ExceptionHandler({PlateformApiDataValidationException.class})
    protected ResponseEntity<Object> handlePlatformError(PlateformApiDataValidationException ex, WebRequest request) {
        //Response header
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //Response body
        final Map<String, String> content = new LinkedHashMap<>();
        content.put("timestamp", String.valueOf(System.currentTimeMillis()));
        content.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        content.put("message", ex.getMessage());
        content.put("path", request.getDescription(false));
        try {
            return new ResponseEntity<>(new ObjectMapper().writeValueAsString(content), headers, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(ex.getMessage(), e);
            return new ResponseEntity<>(new HashMap<>(), headers, HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleError(Exception ex, WebRequest request) {
        //Response header
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //Response body
        final Map<String, String> content = new LinkedHashMap<>();
        content.put("timestamp", String.valueOf(System.currentTimeMillis()));
        content.put("message", ex.getMessage());
        content.put("class", ex.getClass().getName());
        content.put("path", request.getDescription(false));
        try {
            return new ResponseEntity<>(new ObjectMapper().writeValueAsString(content), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error(ex.getMessage(), e);
            return new ResponseEntity<>(new HashMap<>(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
