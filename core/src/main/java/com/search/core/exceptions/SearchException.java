package com.search.core.exceptions;

import com.search.core.enums.ErrorCodes;

public class SearchException extends RuntimeException{

	private static final long serialVersionUID = -5708137942413686640L;
	
	
	private ErrorCodes code;

	public SearchException(String message) {        
        super(message);
    }
    
    public SearchException(ErrorCodes code) {        
    	super(code.getMessage());
        this.code = code;
    }
    
    public SearchException(ErrorCodes code, String message) {        
    	super(message);
        this.code = code;
    }
    
    public SearchException(ErrorCodes code, Throwable t) {        
    	super(code.getMessage(),t);
        this.code = code;
    }
    
    public SearchException(String message, Throwable t) {        
    	super(message, t);
    }

	public ErrorCodes getCode() {
		return code;
	}

	public void setCode(ErrorCodes code) {
		this.code = code;
	}
	
}
