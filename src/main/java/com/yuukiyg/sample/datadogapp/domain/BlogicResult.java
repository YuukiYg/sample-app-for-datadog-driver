package com.yuukiyg.sample.datadogapp.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlogicResult {
	private String message;
	private String timeAtDatadogApp;
	private String timeAtDriver;

	public BlogicResult() {
		message = "default";
		timeAtDatadogApp = "default";
		timeAtDriver = "default";
	}
}
