package com.seven.nungil.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

	private String timeStamp;
	private HttpStatus httpStatus;
	private String errorMessage;
	private String path;

	public static ErrorResponse createErrorResponse(AbstractApiException exception, String path) {
		return new ErrorResponse(
			LocalDate.now().toString(),
			exception.getHttpStatus(),
			exception.getErrorMessage(),
			path
		);
	}
}