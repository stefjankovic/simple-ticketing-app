package com.stefanj.simpleticketingapp.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ApiError {
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timestamp;
	private HttpStatus responseStatus;
	private String code;
	private String message;
	private Map<String, Object> details;
}
