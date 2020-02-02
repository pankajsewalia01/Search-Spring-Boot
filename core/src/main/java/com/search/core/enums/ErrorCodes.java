package com.search.core.enums;


public enum ErrorCodes {
	EXCEPTION("SEARCH_500","Please contact to administartor."),
	INVALID_USER_SEARCH("SEARCH_501","Search input request is invalid."),
	INVALID_USER_SEARCH_PARAM("SEARCH_502","Search input param userId or search token is invalid.");
	
	
	public String getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

	private final String value;
    private final String message;
    
    ErrorCodes(String value, String message) {
        this.value = value;
        this.message = message;
    }
}