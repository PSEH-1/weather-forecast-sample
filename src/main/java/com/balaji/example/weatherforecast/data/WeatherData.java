package com.balaji.example.weatherforecast.data;

import java.util.List;

import lombok.Data;

@Data
public class WeatherData {

	private String cod;

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public int getMessage() {
		return message;
	}

	public void setMessage(int message) {
		this.message = message;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public List<Report> getList() {
		return list;
	}

	public void setList(List<Report> list) {
		this.list = list;
	}

	private int message;

	private int cnt;
	private List<Report> list;

	private City city;

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Report> getReports() {
		return list;
	}

	public void setReports(List<Report> reports) {
		this.list = reports;
	}
}
