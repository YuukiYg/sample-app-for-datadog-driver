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
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Timed
@RequestMapping("/driver")
public class PocDriverController {
	private LoggingService loggingService;
	private ModelMapper modelMapper;

	public PocDriverController(LoggingService _loggingService, ModelMapper _modelMapper) {
		this.loggingService = _loggingService;
		this.modelMapper = _modelMapper;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "custom-metrics.produce.400")
	@Trace(operationName = "trace.controller.produce.400", resourceName = "PocController.produce400Error")
	public ControllerResult produce400Error() {

		BlogicResult blogicResult = loggingService.produceGyomuError();
		ControllerResult controllerResult = modelMapper.map(blogicResult, ControllerResult.class);
		return controllerResult;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "custom-metrics.produce.500")
	@Trace(operationName = "trace.controller.produce.500", resourceName = "PocController.produce500Error")
	public ControllerResult produce500Error() {
		BlogicResult blogicResult = loggingService.produceHoshikiError();
		ControllerResult controllerResult = modelMapper.map(blogicResult, ControllerResult.class);
		return controllerResult;

	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "custom-metrics.time.driverLogging")
	@Trace(operationName = "trace.controller.driverLogging", resourceName = "PocController.driverLogging")
	public ControllerResult driverLogging() {
		BlogicResult blogicResult = loggingService.callDatadogApp();
		ControllerResult controllerResult = modelMapper.map(blogicResult, ControllerResult.class);
		return controllerResult;
	}

}
