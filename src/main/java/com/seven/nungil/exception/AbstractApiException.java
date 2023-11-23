package com.seven.nungil.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class AbstractApiException extends RuntimeException {

	private final HttpStatus httpStatus;
	private final String errorMessage;

	public AbstractApiException(ErrorCode errorCode, String errorMessage) {
		this.httpStatus = errorCode.getHttpStatus();
		this.errorMessage = errorMessage;
	}
}