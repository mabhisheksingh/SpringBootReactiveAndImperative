package com.abhishek.exceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class CustomExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
        HttpStatus status = (HttpStatus) ex.getStatusCode();
        String errorMessage = ex.getReason();

        logger.info("Handling ResponseStatusException. Status: {}, Error: {}", status, errorMessage);

        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                errorMessage
        );

        return new ResponseEntity<>(customErrorResponse, status);
    }

    private static class CustomErrorResponse {
        private int status;
        private String error;
        private String message;

        public CustomErrorResponse(int status, String error, String message) {
            this.status = status;
            this.error = error;
            this.message = message;
        }

        // Add getters for status, error, and message
    }
}
