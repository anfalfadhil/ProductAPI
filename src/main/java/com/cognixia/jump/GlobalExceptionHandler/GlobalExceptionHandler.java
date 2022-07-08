package com.cognixia.jump.GlobalExceptionHandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(UserNotFoundException.class)
	 public ResponseEntity<?> userNotFoundException(UserNotFoundException ex,
	WebRequest request){
	ErrorDetails ed = new ErrorDetails(new Date(), ex.getMessage(),
	request.getDescription(false));
	return new ResponseEntity<>(ed, HttpStatus.NOT_FOUND);
	 }
}
