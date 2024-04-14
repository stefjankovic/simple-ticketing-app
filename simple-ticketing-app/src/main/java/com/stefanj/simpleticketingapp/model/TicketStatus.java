package com.stefanj.simpleticketingapp.model;

public enum TicketStatus {
	OPEN("Open"),
	IN_PROGRESS("InProgress"),
	ON_HOLD("OnHold"),
	RESOLVED("Resolved"),
	CLOSED("Closed");
	
	private final String code;
	
	TicketStatus(String code) {
        this.code = code;
	}
	
    public String getCode() {
        return code;
    }
	
	public static TicketStatus fromValue(String input) {
		for (TicketStatus status : TicketStatus.values()) {
			if (status.getCode().trim().equalsIgnoreCase(input)) {
				return status;
			}
		}
		return null;
	}
}
