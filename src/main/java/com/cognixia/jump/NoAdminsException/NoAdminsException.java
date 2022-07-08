package com.cognixia.jump.NoAdminsException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;





@ControllerAdvice
public class NoAdminsException extends Throwable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String message = "No Admins in the database";
	
	@ExceptionHandler(NoAdminsException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String getMessage () {
		return this.message;
	}
}
