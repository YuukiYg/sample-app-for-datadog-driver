package com.yuukiyg.sample.datadogapp.domain;

import lombok.Data;

/**
 *
 * Feignでレスポンスを受けるクラス
 * @author YuukiYg
 *
 */
@Data
public class DatadogAppResult {

	private String message;
	private String time;

}
