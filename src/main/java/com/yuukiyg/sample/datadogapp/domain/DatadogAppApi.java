package com.yuukiyg.sample.datadogapp.domain;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "datadogApi", url="${url.datadogapp}")
public interface DatadogAppApi {

	@RequestMapping(method=RequestMethod.GET, value="logging", produces = "application/json")
    DatadogAppResult callLogging(@RequestParam("total-logs")int totalLogs, @RequestParam("each-size")int eachSize);

}
