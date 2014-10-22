package org.sample.model;

public class Coordinates {
	private String lon;				
	private String lat;
	
	public Coordinates(String lat, String lon)
	{
		this.lon = lon;
		this.lat = lat;
	}
	
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	
}
