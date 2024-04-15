package com.stefanj.simpleticketingapp.exceptions;

import java.util.Map;

public interface ApiException {
	ErrorCode getErrorCode();
	Map<String, Object> getDetails();
}
