package com.abhishek.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
// import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.*;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Locale;
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

    
    // @ExceptionHandler(ResponseStatusException.class)
    // public Mono<ResponseEntity<Object>> handleResponseStatusException(ResponseStatusException ex, ServerWebExchange exchange) {
    //     HttpStatus status = (HttpStatus) ex.getStatusCode();
    //     return handleExceptionInternal(ex, null, new HttpHeaders(), status, exchange);
    // }


    protected Mono<ResponseEntity<Object>> handleWebExchangeBindException(
            WebExchangeBindException ex, HttpHeaders headers, HttpStatusCode status,
            ServerWebExchange exchange) {

        return handleExceptionInternal(ex, null, headers, status, exchange);
    }

    /**
     * Customize the handling of {@link ServerWebInputException}.
     * <p>This method delegates to {@link #handleExceptionInternal}.
     * @param ex the exception to handle
     * @param headers the headers to use for the response
     * @param status the status code to use for the response
     * @param exchange the current request and response
     * @return a {@code Mono} with the {@code ResponseEntity} for the response
     */
    protected Mono<ResponseEntity<Object>> handleServerWebInputException(
            ServerWebInputException ex, HttpHeaders headers, HttpStatusCode status,
            ServerWebExchange exchange) {

        return handleExceptionInternal(ex, null, headers, status, exchange);
    }

    /**
     * Customize the handling of any {@link ResponseStatusException}.
     * <p>This method delegates to {@link #handleExceptionInternal}.
     * @param ex the exception to handle
     * @param headers the headers to use for the response
     * @param status the status code to use for the response
     * @param exchange the current request and response
     * @return a {@code Mono} with the {@code ResponseEntity} for the response
     */
    protected Mono<ResponseEntity<Object>> handleResponseStatusException(
            ResponseStatusException ex, HttpHeaders headers, HttpStatusCode status,
            ServerWebExchange exchange) {

        return handleExceptionInternal(ex, null, headers, status, exchange);
    }

    /**
     * Customize the handling of {@link ServerErrorException}.
     * <p>This method delegates to {@link #handleExceptionInternal}.
     * @param ex the exception to handle
     * @param headers the headers to use for the response
     * @param status the status code to use for the response
     * @param exchange the current request and response
     * @return a {@code Mono} with the {@code ResponseEntity} for the response
     */
    protected Mono<ResponseEntity<Object>> handleServerErrorException(
            ServerErrorException ex, HttpHeaders headers, HttpStatusCode status,
            ServerWebExchange exchange) {

        return handleExceptionInternal(ex, null, headers, status, exchange);
    }

    /**
     * Customize the handling of any {@link ErrorResponseException}.
     * <p>This method delegates to {@link #handleExceptionInternal}.
     * @param ex the exception to handle
     * @param headers the headers to use for the response
     * @param status the status code to use for the response
     * @param exchange the current request and response
     * @return a {@code Mono} with the {@code ResponseEntity} for the response
     */
    protected Mono<ResponseEntity<Object>> handleErrorResponseException(
            ErrorResponseException ex, HttpHeaders headers, HttpStatusCode status,
            ServerWebExchange exchange) {

        return handleExceptionInternal(ex, null, headers, status, exchange);
    }

    /**
     * Convenience method to create a {@link ProblemDetail} for any exception
     * that doesn't implement {@link ErrorResponse}, also performing a
     * {@link MessageSource} lookup for the "detail" field.
     * @param ex the exception being handled
     * @param status the status to associate with the exception
     * @param defaultDetail default value for the "detail" field
     * @param detailMessageCode the code to use to look up the "detail" field
     * through a {@code MessageSource}, falling back on
     * {@link ErrorResponse#getDefaultDetailMessageCode(Class, String)}
     * @param detailMessageArguments the arguments to go with the detailMessageCode
     * @return the created {@code ProblemDetail} instance
     */
    protected ProblemDetail createProblemDetail(
            Exception ex, HttpStatusCode status, String defaultDetail, @Nullable String detailMessageCode,
            @Nullable Object[] detailMessageArguments, ServerWebExchange exchange) {

        ErrorResponse.Builder builder = ErrorResponse.builder(ex, status, defaultDetail);
        if (detailMessageCode != null) {
            builder.detailMessageCode(detailMessageCode);
        }
        if (detailMessageArguments != null) {
            builder.detailMessageArguments(detailMessageArguments);
        }
        return builder.build().updateAndGetBody(this.messageSource, getLocale(exchange));
    }

    private static Locale getLocale(ServerWebExchange exchange) {
        Locale locale = exchange.getLocaleContext().getLocale();
        return (locale != null ? locale : Locale.getDefault());
    }

    /**
     * Internal handler method that all others in this class delegate to, for
     * common handling, and for the creation of a {@link ResponseEntity}.
     * <p>The default implementation does the following:
     * <ul>
     * <li>return {@code null} if response is already committed
     * <li>set the {@code "jakarta.servlet.error.exception"} request attribute
     * if the response status is 500 (INTERNAL_SERVER_ERROR).
     * <li>extract the {@link ErrorResponse#getBody() body} from
     * {@link ErrorResponse} exceptions, if the {@code body} is {@code null}.
     * </ul>
     * @param ex the exception to handle
     * @param body the body to use for the response
     * @param headers the headers to use for the response
     * @param status the status code to use for the response
     * @param exchange the current request and response
     * @return a {@code Mono} with the {@code ResponseEntity} for the response
     */
    protected Mono<ResponseEntity<Object>> handleExceptionInternal(
            Exception ex, @Nullable Object body, @Nullable HttpHeaders headers, HttpStatusCode status,
            ServerWebExchange exchange) {

        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }

        if (body == null && ex instanceof ErrorResponse errorResponse) {
            body = errorResponse.updateAndGetBody(this.messageSource, getLocale(exchange));
        }

        return createResponseEntity(body, headers, status, exchange);
    }



}
