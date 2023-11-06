package com.wmanager.auth.exceptions;

import java.sql.SQLException;

import org.hibernate.JDBCException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

@ControllerAdvice
public class GlobalExceptionHandler extends Exception {

	private static final long serialVersionUID = 1L;
	
	public static void sendMessage(String msg) {
		System.out.println("=============== " + msg + " ===============");
	}

	@ExceptionHandler(ClassNotFoundException.class)
	public ResponseEntity<ExceptionDto> NotFoundException(ClassNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDto(HttpStatus.NOT_FOUND, e.getMessage()));
	}

	@ExceptionHandler(Forbidden.class)
	public ResponseEntity<ExceptionDto> ForbiddenException(Forbidden e) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionDto(HttpStatus.FORBIDDEN, e.getMessage()));
	}

	@ExceptionHandler(BadRequest.class)
	public ResponseEntity<ExceptionDto> BadRequestException(BadRequest e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage()));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ExceptionDto> IllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage()));
	}

	/*
	 * @ExceptionHandler(DataIntegrityViolationException.class) public
	 * ResponseEntity<ExceptionDto>
	 * DataIntegrityViolationException(DataIntegrityViolationException e) { return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST) .body(new
	 * ExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage())); }
	 */

	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<ExceptionDto> DisabledException(DisabledException e) {
		return ResponseEntity.status(HttpStatus.LOCKED).body(new ExceptionDto(HttpStatus.LOCKED, "Usuario Desabilitado " + e.getMessage()));
	}
	
	@ExceptionHandler(org.springframework.security.authentication.BadCredentialsException.class)
	public ResponseEntity<ExceptionDto> BadCredentialsException(org.springframework.security.authentication.BadCredentialsException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionDto(HttpStatus.UNAUTHORIZED, "Usuario ou senha inválidos " + e.getMessage()));
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<ExceptionDto> EmptyResultDataAccessException(EmptyResultDataAccessException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionDto(HttpStatus.UNAUTHORIZED, "Autenticação Inválida " + e.getMessage()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionDto> dataException(Exception ex){
		if (ex instanceof DataIntegrityViolationException) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionDto(HttpStatus.CONFLICT, "Violação de banco de dados" + ex.getMessage()));
        } else if (ex instanceof ConstraintViolationException) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionDto(HttpStatus.CONFLICT, "Violação de banco de dados" + ex.getMessage()));
        } else if (ex instanceof JDBCException) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionDto(HttpStatus.CONFLICT, "Violação de banco de dados" + ex.getMessage()));
        }else if (ex instanceof SQLException) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionDto(HttpStatus.CONFLICT, "Violação de banco de dados" + ex.getMessage()));
        }else if (ex instanceof DataAccessException) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionDto(HttpStatus.CONFLICT, "Violação de banco de dados" + ex.getMessage()));
        }
		return null;
	}
	

}

/*
 * DataIntegrityViolationException.class ConstraintViolationException.
 * JDBCException.class, SQLException.class DataAccessException.class
 */