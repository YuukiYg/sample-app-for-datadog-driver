package com.yuukiyg.sample.datadogapp.domain;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.yuukiyg.sample.datadogapp.domain.exception.MyBusinessException;
import com.yuukiyg.sample.datadogapp.domain.exception.MySystemException;

import datadog.trace.api.Trace;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoggingServiceImpl implements LoggingService {

	@Override
	@Trace(operationName = "trace.blogic.logging", resourceName = "LoggingServiceImpl.logging")
	public BlogicResult callDatadogApp() {

		// call datadog app.


		return new BlogicResult("", LocalDateTime.now().toString());
	}

	@Override
	public BlogicResult produceGyomuError() {
		log.info("Throwing MyBusinessException from blogic.");
		throw new MyBusinessException();
	}

	@Override
	public BlogicResult produceHoshikiError() {
		log.info("Throwing MySystemException from blogic.");
		throw new MySystemException();
	}
}
