package com.yuukiyg.sample.datadogapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DatadogappApplicationDriver {

	public static void main(String[] args) {
		SpringApplication.run(DatadogappApplicationDriver.class, args);
	}

}
