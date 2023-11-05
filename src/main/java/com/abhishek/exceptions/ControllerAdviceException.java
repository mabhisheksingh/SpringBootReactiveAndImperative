package com.abhishek.exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;


//@ControllerAdvice
public class ControllerAdviceException extends ResponseEntityExceptionHandler {
    private final static  String ERROR_MESSAGE = "error message";
    private final static  String ERROR_CODE = "error code";

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<?> handlePlanetAPIException(HttpClientErrorException.NotFound exception){
        HashMap<String, String> ex = new HashMap<>(2);
        ex.put(ERROR_MESSAGE, exception.getMessage());
        ex.put(ERROR_MESSAGE, exception.getMessage());
        return ResponseEntity.ok(ex);
    }


    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("headers -> "+headers.getOrigin());
        System.out.println("status -> "+status.value());
        System.out.println("ex.getMessage -> "+ex);
        System.out.println("request -> "+request.getContextPath());
        return new ResponseEntity<Object>( "Wrong Media type selected ",HttpStatus.UNSUPPORTED_MEDIA_TYPE );
    }
    
}
