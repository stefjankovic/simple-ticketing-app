package com.stefanj.simpleticketingapp.model;

public enum TicketCategory {
	SERVICE_REQUEST("ServiceRequest"),
	INCIDENT("Incident"),
	PROBLEM("Problem"),
	CHANGE_REQUEST("ChangeRequest");
	
	private final String code;
	
	TicketCategory(String code) {
        this.code = code;
	}
	
    public String getCode() {
        return code;
    }
	
	public static TicketCategory fromValue(String input) {
		for (TicketCategory category : TicketCategory.values()) {
			if (category.getCode().trim().equalsIgnoreCase(input)) {
				return category;
			}
		}
		return null;
	}
}
