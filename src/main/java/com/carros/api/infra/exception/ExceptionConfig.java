package com.carros.api.infra.exception;

import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({
		EmptyResultDataAccessException.class,
		ObjectNotFoundException.class,
		NoSuchElementException.class
	})
	public ResponseEntity<?> errorNotFound(Exception ex) {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler({
		IllegalArgumentException.class
	})
	public ResponseEntity<?> errorBadRequest(Exception ex) {
		return ResponseEntity.badRequest().build();
	}
	
//	@ExceptionHandler({ 
//		AccessDeniedException.class 
//	})
//	public ResponseEntity<?> accessDenied() {
//		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error("Acesso negado"));
//	}
}

class Error {
	public String error;

	public Error(String error) {
		this.error = error;
	}

}
