package org.sample.controller.pojos;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

public class AdForm {
	
	private Long id;
	private Timestamp timestamp;
	@NotNull
	@Pattern(regexp = "[a-zA-ZäöüÄÖÜ.,;:0-9()\\s]+", message = "Enter a descriptive title\n")
	private String title;
	@NotNull
	@Pattern(regexp = "[a-zA-ZäöüÄÖÜ.,;:0-9()\\s]+", message = "Enter the street of the apartment\n")
	private String street;
	private Long houseNr;
	@NotNull
	@Pattern(regexp = "[a-zA-ZäöüÄÖÜ.,;:0-9()\\s]+", message = "Enter the city of the apartment\n")
	private String city;
	@NotNull
	private Long zip;
	@NotNull
	private Long rent;
	private Long addCost;
	@NotNull
	private String dateIn;
	private String dateOut;
	private Long roomSize;
	@NotNull
	@Pattern(regexp = "[a-zA-ZäöüÄÖÜ.,;:0-9()\\s]+", message = "Enter a description about the room\n")
	private String description;
	private String us;
	private String you;
	
	private String lat;
	private String lng;
	
	private String imageIds;

	
	private Long placerId;
	

	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Long getZip() {
		return zip;
	}
	public void setZip(Long zip) {
		this.zip = zip;
	}
	public Long getRent() {
		return rent;
	}
	public void setRent(Long rent) {
		this.rent = rent;
	}
	public Long getAddCost() {
		return addCost;
	}
	public void setAddCost(Long addCost) {
		this.addCost = addCost;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
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
	public Long getRoomSize() {
		return roomSize;
	}
	public void setRoomSize(Long roomSize) {
		this.roomSize = roomSize;
	}
	public String getUs() {
		return us;
	}
	public void setUs(String us) {
		this.us = us;
	}
	public String getYou() {
		return you;
	}
	public void setYou(String you) {
		this.you = you;
	}

	
	public Long getPlacerId() {
		return placerId;
	}
	public void setPlacerId(Long placerId) {
		this.placerId = placerId;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getImageIds() {
		return imageIds;
	}
	public void setImageIds(String imageIds) {
		this.imageIds = imageIds;
	}
	

}
