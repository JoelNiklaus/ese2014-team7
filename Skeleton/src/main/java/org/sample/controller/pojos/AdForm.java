package org.sample.controller.pojos;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

public class AdForm {
	
	private Long id;
	private Timestamp timestamp;
	@NotNull
	private String title;
	@NotNull
	private String street;
	private Long houseNr;
	@NotNull
	private String city;
	@NotNull
	private Long zip;
	@NotNull
	private Long rent;
	private Long addCost;
	@NotNull
	private String dateIn;
	private String dateOut;
	@NotNull
	//private String type;
	private Long roomSize;
	private String description;
	private String us;
	private String you;
	
	private String img_one;
	/*
	private String img_two;
	private String img_three;
	private String img_four;
	*/
	
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
	/*
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	*/
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
	public String getImg_one() {
		return img_one;
	}
	public void setImg_one(String img_one) {
		this.img_one = img_one;
	}
	/*
	public String getImg_two() {
		return img_two;
	}
	public void setImg_two(String img_two) {
		this.img_two = img_two;
	}
	public String getImg_three() {
		return img_three;
	}
	public void setImg_three(String img_three) {
		this.img_three = img_three;
	}
	public String getImg_four() {
		return img_four;
	}
	public void setImg_four(String img_four) {
		this.img_four = img_four;
	}
	*/
	
	public Long getPlacerId() {
		return placerId;
	}
	public void setPlacerId(Long placerId) {
		this.placerId = placerId;
	}
	

}
