package com.balaji.example.weatherforecast.response;

import java.util.List;

public class WeatherResponse {

	private List<DayWeather> dayWeather;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<DayWeather> getDayWeather() {
		return dayWeather;
	}

	public void setDayWeather(List<DayWeather> dayWeather) {
		this.dayWeather = dayWeather;
	}
}
