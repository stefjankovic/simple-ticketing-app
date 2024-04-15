package com.stefanj.simpleticketingapp.exceptions;


import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Order(Ordered.LOWEST_PRECEDENCE)
@ControllerAdvice
public class GenericRestExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ApiError> handle(Exception exception) {
		String exceptionMessage = exception.getMessage() == null ? "" : exception.getMessage();
		
		ApiError apiError = ApiError.builder()
				.timestamp(LocalDateTime.now())
				.responseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
				.code(ErrorCode.UNKNOWN.getCode())
				.message("Unknown error occurred.")
				.details(Map.of("exceptionMessage", exceptionMessage))
				.build();
		
		return ResponseEntity.
				status(HttpStatus.INTERNAL_SERVER_ERROR).
				body(apiError);
    }
}