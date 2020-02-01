package com.balaji.example.weatherforecast.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.balaji.example.weatherforecast.data.Report;
import com.balaji.example.weatherforecast.data.Weather;
import com.balaji.example.weatherforecast.data.WeatherData;
import com.balaji.example.weatherforecast.response.DayWeather;
import com.balaji.example.weatherforecast.response.WeatherResponse;

@Service
public class WeatherForecastService {

	@Autowired
	private RestTemplate restTemplate;

	public WeatherResponse getWeatherReport(String city) {

		ResponseEntity<WeatherData> forEntity = restTemplate
				.getForEntity("https://api.openweathermap.org/data/2.5/forecast?q=" + city
						+ "&mode=json&appid=d2929e9483efc82c82c32ee7e02d563e&units=metric", WeatherData.class);

		List<Report> reports = forEntity.getBody().getReports();

		WeatherResponse weatherResponse = new WeatherResponse();
		List<DayWeather> dayWeather = new ArrayList<DayWeather>();

		HashMap<Date, DayWeather> hashMap = new HashMap<Date, DayWeather>();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (Report report : reports) {

			String dt_txt = report.getDt_txt();

			List<Weather> weather = report.getWeather();
			for (Weather weath : weather) {

				if ("Rain".equals(weath.getMain())) {
					weatherResponse.setMessage("Carry Umberlla");
				}

			}

			if (report.getMain().getTemp() >= 40) {
				weatherResponse.setMessage("Use Sunscreen Lotion");
			}

			Date parse = null;
			try {
				parse = simpleDateFormat.parse(dt_txt);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			DayWeather dayWeather2 = hashMap.get(parse);
			if (null == dayWeather2) {
				DayWeather dayWeatherTemp = new DayWeather();
				dayWeatherTemp.setDate(parse);
				dayWeatherTemp.setMinTemp(report.getMain().getTemp_min());
				dayWeatherTemp.setMaxTemp(report.getMain().getTemp_max());
				hashMap.put(parse, dayWeatherTemp);

			} else {

				dayWeather2.setMinTemp(
						report.getMain().getTemp_min() < dayWeather2.getMinTemp() ? report.getMain().getTemp_min()
								: dayWeather2.getMinTemp());
				dayWeather2.setMaxTemp(
						report.getMain().getTemp_max() > dayWeather2.getMaxTemp() ? report.getMain().getTemp_max()
								: dayWeather2.getMaxTemp());

			}

		}

		Set<Entry<Date, DayWeather>> entrySet = hashMap.entrySet();
		for (Entry<Date, DayWeather> entry : entrySet) {

			dayWeather.add(entry.getValue());
		}

		weatherResponse.setDayWeather(dayWeather);

		return weatherResponse;
	}

}
