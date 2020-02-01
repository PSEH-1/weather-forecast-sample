package com.balaji.example.weatherforecast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.balaji.example.weatherforecast.response.WeatherResponse;
import com.balaji.example.weatherforecast.service.WeatherForecastService;

@RestController
@RequestMapping("/weather")
public class WeatherForecastController {

	@Autowired
	private WeatherForecastService weatherService;

	@GetMapping("/")
	public ResponseEntity<WeatherResponse> getWeatherForecast(@RequestParam String city) {

		WeatherResponse weatherResponse = weatherService.getWeatherReport(city);

		return new ResponseEntity<WeatherResponse>(weatherResponse, HttpStatus.OK);
	}

}
