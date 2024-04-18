package com.stefanj.simpleticketingapp.exceptions;

public enum ErrorCode {
	UNKNOWN("ER000","Unknown error."),
	VALIDATION_FAILED("ER001", "Validation failed."), 
	RESOURCE_NOT_FOUND("ER002", "Resource for provided criteria is not found in repository."),
	API_ENDPOINT_ACCESS_DENIED("ER003", "User does not have privilege to use this api endpoint."),
	BAD_USER_CREDENTIALS_PROVIDED("ER004","Entered user credentials are not valid."),
	ILLEGAL_TICKET_STATUS_CHANGE("ER005","Ticket cannot change to provided status.");
	
	private String code;
	private String description;
	
	private ErrorCode(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
}
