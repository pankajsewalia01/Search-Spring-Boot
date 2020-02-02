package com.search.core.enums;

public enum ErrorLogs {
	SERIALIZATION_ERROR("IOException in serialization using ObjectMapper."),
	DESERIALIZATION_ERROR("IOException in deserialization using ObjectMapper."),
	REDIS_ERROR("Exception occur in fetching from Redis"),
	TRIE_INSERTION_ERROR("Exception occur in while inserting into user search trie.");
	
	private String message;
	
	public String getMessage() {
		return this.message;
	}
	
	private ErrorLogs(String message){
		this.message=message;
	}
}