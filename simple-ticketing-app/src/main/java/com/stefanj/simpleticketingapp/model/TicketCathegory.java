package com.stefanj.simpleticketingapp.model;

public enum TicketCathegory {
	SERVICE_REQUEST("ServiceRequest"),
	INCIDENT("Incident"),
	PROBLEM("Problem"),
	CHANGE_REQUEST("ChangeRequest");
	
	private final String code;
	
	TicketCathegory(String code) {
        this.code = code;
	}
	
    public String getCode() {
        return code;
    }
	
	public static TicketCathegory fromValue(String input) {
		for (TicketCathegory cathegory : TicketCathegory.values()) {
			if (cathegory.getCode().trim().equalsIgnoreCase(input)) {
				return cathegory;
			}
		}
		return null;
	}
}
