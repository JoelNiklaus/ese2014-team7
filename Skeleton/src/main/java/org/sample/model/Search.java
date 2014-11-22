package org.sample.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Search {
	@Id
	@GeneratedValue
	private Long id;
	
	private Timestamp timestamp;
	
	private Long priceMin;
	private Long priceMax;
	private Long roomSizeMin;
	private Long roomSizeMax;
	
	private String city;
	
	private Long userId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPriceMin() {
		return priceMin;
	}
	public void setPriceMin(Long priceMin) {
		this.priceMin = priceMin;
	}
	public Long getPriceMax() {
		return priceMax;
	}
	public void setPriceMax(Long priceMax) {
		this.priceMax = priceMax;
	}
	public Long getRoomSizeMin() {
		return roomSizeMin;
	}
	public void setRoomSizeMin(Long roomSizeMin) {
		this.roomSizeMin = roomSizeMin;
	}
	public Long getRoomSizeMax() {
		return roomSizeMax;
	}
	public void setRoomSizeMax(Long roomSizeMax) {
		this.roomSizeMax = roomSizeMax;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public Search() {
		
	}

	public Search(Long id, Long priceMin, Long priceMax, Long roomSizeMin,
			Long roomSizeMax, String city) {
		super();
		this.id = id;
		this.priceMin = priceMin;
		this.priceMax = priceMax;
		this.roomSizeMin = roomSizeMin;
		this.roomSizeMax = roomSizeMax;
		this.city = city;
	}
	   
}