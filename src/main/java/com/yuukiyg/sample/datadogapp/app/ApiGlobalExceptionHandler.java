package com.yuukiyg.sample.datadogapp.app;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.yuukiyg.sample.datadogapp.domain.exception.MyBusinessException;
import com.yuukiyg.sample.datadogapp.domain.exception.MySystemException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ApiGlobalExceptionHandler {
	@ExceptionHandler(MyBusinessException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleBusinessException(MyBusinessException e) {
		log.info("Catched MyBusinessException at exception handler, and will return it as a Gyomu Error.");
		return makeMessageBody(400, "Gyomu Error.");
	}

	@ExceptionHandler(MySystemException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, Object> handleSystemException(MySystemException e) {
		log.info("Catched MySystemException at exception handler, and will return it as a Hoshiki Error.");
		return makeMessageBody(500, "Hoshiki Error.");
	}

	private Map<String, Object> makeMessageBody(int code, String message) {
		final String statusCodeKey = "statusCode";
		final String messageKey = "message";

		Map<String, Object> responseBody = new HashMap<String, Object>();
		responseBody.put(statusCodeKey, code);
		responseBody.put(messageKey, message);

		return responseBody;
	}
}