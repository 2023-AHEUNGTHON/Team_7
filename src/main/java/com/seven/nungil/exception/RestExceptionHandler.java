package com.seven.nungil.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(AbstractApiException.class)
	public ResponseEntity<ErrorResponse> handleGlobalException(AbstractApiException ex, HttpServletRequest request) {
		return ResponseEntity.status(ex.getHttpStatus())
			.body(ErrorResponse.createErrorResponse(ex, request.getRequestURI()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
		HttpServletRequest request) {
		List<String> errors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String errorMessage = error.getDefaultMessage();
			errors.add(errorMessage);
		});
		var errorResponse = ErrorResponse.builder()
			.timeStamp(LocalDateTime.now().toString())
			.httpStatus(HttpStatus.BAD_REQUEST)
			.errorMessage(errors.toString())
			.path(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(errorResponse.build());
	}

}
