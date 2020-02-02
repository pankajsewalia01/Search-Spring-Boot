package com.search.core.entity;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.search.core.enums.ErrorCodes;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Error implements Serializable{

	private static final long serialVersionUID = 374983788L;
	
	private String code;
	private String type;
	private String message;
	
	public Error(){}
	
	public Error(String code, String message,String type) {
		this.code = code;
		this.message = message;
		this.type = type;
	}
	
	public Error(ErrorCodes errorCodes,String type) {
		this.code = errorCodes.getValue();
		this.message = errorCodes.getMessage();
		this.type = type;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Error [code=" + code + ", type=" + type + ", message=" + message + "]";
	}

}