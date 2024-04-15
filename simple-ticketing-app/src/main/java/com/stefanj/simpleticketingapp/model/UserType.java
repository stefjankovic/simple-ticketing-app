package com.stefanj.simpleticketingapp.model;

public enum UserType {
	ADMIN("Admin"),
	SUPPORT("SupportStaff"),
	CUSTOMER("Customer"),
	AUDITOR("Auditor");
	
	private final String code;
	
	UserType(String code) {
        this.code = code;
	}
	
    public String getCode() {
        return code;
    }
	
	public static UserType fromValue(String input) {
		for (UserType type : UserType.values()) {
			if (type.getCode().trim().equalsIgnoreCase(input)) {
				return type;
			}
		}
		return null;
	}
}
