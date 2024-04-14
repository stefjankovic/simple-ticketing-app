package com.stefanj.simpleticketingapp.model;

public enum SLAPriority {
	LOW("Low"), 
	MEDIUM("Medium"), 
	URGENT("Urgent");

	private final String code;
	
	SLAPriority(String code) {
        this.code = code;
	}
	
    public String getCode() {
        return code;
    }
	
	public static SLAPriority fromValue(String input) {
		for (SLAPriority priority : SLAPriority.values()) {
			if (priority.getCode().trim().equalsIgnoreCase(input)) {
				return priority;
			}
		}
		return null;
	}
}
