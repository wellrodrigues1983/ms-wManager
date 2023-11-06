package com.wmanager.auth.exceptions;

import org.springframework.http.HttpStatus;

public record ExceptionDto(HttpStatus status, String message) {

}
