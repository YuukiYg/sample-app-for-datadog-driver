package com.yuukiyg.sample.datadogapp.domain;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.timgroup.statsd.NonBlockingStatsDClientBuilder;
import com.timgroup.statsd.StatsDClient;
import com.yuukiyg.sample.datadogapp.domain.exception.MyBusinessException;
import com.yuukiyg.sample.datadogapp.domain.exception.MySystemException;

import datadog.trace.api.Trace;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoggingServiceImpl implements LoggingService {

	private DatadogAppApi datadogAppApi;

	private StatsDClient statsd;

	public LoggingServiceImpl(DatadogAppApi _datadogAppApi) {
		this.datadogAppApi = _datadogAppApi;
		statsd = new NonBlockingStatsDClientBuilder().prefix("statsd").hostname("localhost").port(8125).build();
	}

	@Override
	@Trace(operationName = "trace.blogic.callDatadogApp", resourceName = "LoggingServiceImpl.callDatadogApp")
	public BlogicResult callDatadogApp() {
		DatadogAppResult result = datadogAppApi.callLogging(100, 1);

		String messageUpperCase = result.getMessage().toUpperCase();
		String timeAtDatadogApp = result.getTime();

		statsd.incrementCounter("custom_metrics.increment.callDatadogApp", new String[]{"sample_tag:t1", "sample_tag2:t2"});

		return new BlogicResult("Message from datadogapp-> " + messageUpperCase, timeAtDatadogApp,
				LocalDateTime.now().toString());
	}

	@Override
	@Trace(operationName = "trace.blogic.produceGyomuError", resourceName = "LoggingServiceImpl.produceGyomuError")
	public BlogicResult produceGyomuError() {
		log.error("Throwing MyBusinessException from blogic.");
		throw new MyBusinessException("intentionally produced exception");
	}

	@Override
	@Trace(operationName = "trace.blogic.produceHoshikiError", resourceName = "LoggingServiceImpl.produceHoshikiError")
	public BlogicResult produceHoshikiError() {
		log.error("Throwing MySystemException from blogic.");
		throw new MySystemException("intentionally produced exception");
	}
}
