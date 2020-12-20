package com.yuukiyg.sample.datadogapp.domain;

public interface LoggingService {
	public BlogicResult callDatadogApp();
	public BlogicResult produceGyomuError();
	public BlogicResult produceHoshikiError();
}
