package com.yuukiyg.sample.datadogapp.app;

public enum MyHttpStatus{
	GYOMU_ERROR(400, "Gyomu Error"),
	HOSHIKI_ERROR(500, "Hoshiki Error");


	private final int value;

	private final String reasonPhrase;


	MyHttpStatus(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * Return the integer value of this status code.
	 */
	public int value() {
		return this.value;
	}

	/**
	 * Return the reason phrase of this status code.
	 */
	public String getReasonPhrase() {
		return this.reasonPhrase;
	}
}
