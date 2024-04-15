package com.stefanj.simpleticketingapp.exceptions;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException implements ApiException {
    private static final long serialVersionUID = 5709495547407829616L;
    
    private ErrorCode errorCode;
    private Map<String, Object> details;

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }
    
    public NotFoundException(ErrorCode errorCode, Map<String, Object> details) {
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
