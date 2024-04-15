package com.stefanj.simpleticketingapp.exceptions;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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