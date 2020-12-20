package com.yuukiyg.sample.datadogapp.app;

import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuukiyg.sample.datadogapp.domain.BlogicResult;
import com.yuukiyg.sample.datadogapp.domain.LoggingService;

import datadog.trace.api.Trace;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Timed
@RequestMapping("/driver")
public class PocDriverController {
	private LoggingService loggingService;
	private ModelMapper modelMapper;
	private final Counter counterGyomuError;
	private final Counter counterHoshikiError;


	public PocDriverController(LoggingService _loggingService, ModelMapper _modelMapper, MeterRegistry registry) {
		this.loggingService = _loggingService;
		this.modelMapper = _modelMapper;
		this.counterGyomuError = registry.counter("custom_metrics.error.gyomu");
		this.counterHoshikiError = registry.counter("custom_metrics.error.hoshiki");
	}

	@RequestMapping(value = "/error400", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "custom_metrics.time.produce400")
	@Trace(operationName = "trace.controller.produce.400", resourceName = "PocController.produce400Error")
	public ControllerResult produce400Error() {
		this.counterGyomuError.increment();

		BlogicResult blogicResult = loggingService.produceGyomuError();
		ControllerResult controllerResult = modelMapper.map(blogicResult, ControllerResult.class);
		return controllerResult;
	}

	@RequestMapping(value = "/error500", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "custom_metrics.time.produce500")
	@Trace(operationName = "trace.controller.produce.500", resourceName = "PocController.produce500Error")
	public ControllerResult produce500Error() {
		this.counterHoshikiError.increment();

		BlogicResult blogicResult = loggingService.produceHoshikiError();
		ControllerResult controllerResult = modelMapper.map(blogicResult, ControllerResult.class);
		return controllerResult;

	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "custom_metrics.time.driverLogging")
	@Trace(operationName = "trace.controller.driverLogging", resourceName = "PocController.driverLogging")
	public ControllerResult driverLogging() {
		BlogicResult blogicResult = loggingService.callDatadogApp();
		ControllerResult controllerResult = modelMapper.map(blogicResult, ControllerResult.class);

		log.info("driverLogging endpoint called.");
		return controllerResult;
	}

}
