package com.stefanj.simpleticketingapp.exceptions;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TicketStatusChangeException extends RuntimeException implements ApiException {
	private static final long serialVersionUID = -8538505234734326715L;

	private ErrorCode errorCode;
    private Map<String, Object> details;
    
	public TicketStatusChangeException(ErrorCode errorCode) {
		super(errorCode.getDescription());
		this.errorCode = errorCode;
	}
	
	public TicketStatusChangeException(ErrorCode errorCode, Map<String, Object> details) {
		super(errorCode.getDescription());
		this.errorCode = errorCode;
		this.details = details;
	}
	
	@Override
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	@Override
	public Map<String, Object> getDetails() {
		return details;
	}
}
