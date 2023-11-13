package com.abhishek.exceptions;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Order(-4)
//@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class ControllerAdviceException  extends  ResponseEntityExceptionHandler {
    private final static String ERROR_MESSAGE = "error message";
    private final static String ERROR_CODE = "error code";
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<?> objectNotFoundException(ObjectNotFoundException objectNotFoundException) {
        System.out.println("HIT");
        Map<String, String> error = new HashMap<>();
        error.put(ERROR_MESSAGE, objectNotFoundException.getMessage());
        error.put(ERROR_CODE, HttpStatus.NOT_FOUND.toString());
        return ResponseEntity.ok(error);
    }

	@Override
	protected Mono<ResponseEntity<Object>> handleMethodNotAllowedException(MethodNotAllowedException ex,
			HttpHeaders headers, HttpStatusCode status, ServerWebExchange exchange) {
		System.out.println("handleMethodNotAllowedException headers -> " + headers.getOrigin());
		System.out.println("status -> " + status.value());
		System.out.println("ex -> " + ex.getSupportedMethods());
		System.out.println("request -> " + exchange.getResponse());
		return Mono.just(new ResponseEntity<Object>("Wrong Media type selected ", HttpStatus.METHOD_NOT_ALLOWED));
	}

    @Override
    protected Mono<ResponseEntity<Object>> handleResponseStatusException(
            ResponseStatusException ex, HttpHeaders headers, HttpStatusCode status,
            ServerWebExchange exchange) {
        System.out.println("handleResponseStatusException headers -> "+headers.getOrigin());
        System.out.println("status -> "+status.value());
        System.out.println("ex -> "+ex.getMessage());
        System.out.println("request -> "+exchange.getResponse());
        if (status == HttpStatus.NOT_FOUND) {
            // Handle the "Not Found" error here
            // You can customize the response or log the error
            return Mono.just(new ResponseEntity<Object>("Resource not found", HttpStatus.NOT_FOUND));
        }
        // Handle other types of errors here
        return super.handleExceptionInternal(ex, null, headers, status, exchange);
        //return Mono.just(new ResponseEntity<Object>( "Request path not exist ",HttpStatus.NOT_FOUND ));
    }
}
