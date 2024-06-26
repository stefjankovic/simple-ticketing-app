package com.stefanj.simpleticketingapp.exceptions;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.stefanj.simpleticketingapp.dtos.ApiError;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiError> handle(NotFoundException exception) {	
		return makeResponseEntity(exception, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseEntity<ApiError> handle(BadCredentialsException exception) {
		String exceptionMessage = exception.getMessage() == null ? "" : exception.getMessage();
        
        ApiError apiError = ApiError.builder()
        		.timestamp(LocalDateTime.now())
        		.responseStatus(HttpStatus.UNAUTHORIZED)
        		.code(ErrorCode.BAD_USER_CREDENTIALS_PROVIDED.getCode())
        		.message(ErrorCode.BAD_USER_CREDENTIALS_PROVIDED.getDescription())
        		.details(Map.of("exceptionMessage", exceptionMessage))
        		.build();
        		
        return ResponseEntity
        		.status(HttpStatus.UNAUTHORIZED)
        		.body(apiError);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        Map<String, Object> fieldErrors = exception.getBindingResult().getFieldErrors().stream()
        		.collect(Collectors.toMap(error -> error.getField(), FieldError::getDefaultMessage));
        
        ApiError apiError = ApiError.builder()
        		.timestamp(LocalDateTime.now())
        		.responseStatus(HttpStatus.BAD_REQUEST)
        		.code(ErrorCode.VALIDATION_FAILED.getCode())
        		.message("Api constraint violation.")
        		.details(fieldErrors)
        		.build();
        		
        return ResponseEntity
        		.status(HttpStatus.BAD_REQUEST)
        		.body(apiError);
    }
	
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ApiError> handleMethodAccessDenied(AccessDeniedException exception) {
		String exceptionMessage = exception.getMessage() == null ? "" : exception.getMessage();
        
        ApiError apiError = ApiError.builder()
        		.timestamp(LocalDateTime.now())
        		.responseStatus(HttpStatus.FORBIDDEN)
        		.code(ErrorCode.API_ENDPOINT_ACCESS_DENIED.getCode())
        		.message(ErrorCode.API_ENDPOINT_ACCESS_DENIED.getDescription())
        		.details(Map.of("exceptionMessage", exceptionMessage))
        		.build();
        		
        return ResponseEntity
        		.status(HttpStatus.FORBIDDEN)
        		.body(apiError);
    }
	
	private ResponseEntity<ApiError> makeResponseEntity(ApiException exception, HttpStatus httpStatus) {
		ApiError apiError = ApiError.builder()
				.timestamp(LocalDateTime.now())
				.responseStatus(httpStatus)
				.code(exception.getErrorCode().getCode())
				.message(exception.getErrorCode().getDescription())
				.details(exception.getDetails())
				.build();
		
		return ResponseEntity
		        .status(httpStatus)
		        .body(apiError);
	}
}