package org.sample.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Ad {
	
	@Id
    @GeneratedValue
    private Long id;
	private Timestamp timestamp;
	private String title;
	private String street;
	private Long houseNr;
	private String city;
	private Long zip;
	private Long rent;
	private Long addCost;
	private String dateIn;
	private String dateOut;
	private String type;
	private Long roomSize;
	private String description;
	private String us;
	private String you;
	private String lat;
	private String lon;

	public Long getId(){
        return id;
    }
    
	public void setId(Long id){
        this.id = id;
    } 
	
    public Timestamp getTimestamp(){
    	return timestamp;
    } 
    
    public void setTimestamp(Timestamp timestamp){
    	this.timestamp = timestamp;
    }
    
    public String getTitle(){
    	return title;
    }
    
    public void setTitle(String title){
    	this.title = title;
    }
    
    public String getCity(){
    	return city;
    }
    
    public void setCity(String city){
    	this.city = city;
    }
    
    public Long getZip(){
    	return zip;
    }
    
    public void setZip(Long zip){
    	this.zip = zip;
    }
    
    public Long getRent(){
    	return rent;
    }
    
    public void setRent(Long rent){
    	this.rent = rent;
    }
    
    public Long getAddCost(){
    	return addCost;
    }
    
    public void setAddCost(Long addCost){
    	this.addCost = addCost;
    }
    
    public String getType(){
    	return type;
    }
    
    public void setType(String type){
    	this.type = type;
    }

	
    public String getStreet() {
		return street;
	}
    

	public void setStreet(String street) {
		this.street = street;
	}

	public Long getHouseNr() {
		return houseNr;
	}

	public void setHouseNr(Long houseNr) {
		this.houseNr = houseNr;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateIn() {
		return dateIn;
	}

	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}

	public String getDateOut() {
		return dateOut;
	}

	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}

	public String getYou() {
		return you;
	}

	public void setYou(String you) {
		this.you = you;
	}

	public String getUs() {
		return us;
	}

	public void setUs(String us) {
		this.us = us;
	}

	public Long getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(Long roomSize) {
		this.roomSize = roomSize;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}
}
