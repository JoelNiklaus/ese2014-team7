package org.sample.controller.pojos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.sample.model.DateConverter;

public class SearchForm {
	
	private String priceMin;
	private String priceMax;
	private String roomSizeMin;
	private String roomSizeMax;
	private String city;
	private String addCostMax;
	private String earliestMoveInDate;
	private String latestMoveInDate;
	private Date earliestMoveInDateD;
	private Date latestMoveInDateD;

	public String getRoomSizeMin() {
		return roomSizeMin;
	}
	public void setRoomSizeMin(String roomSizeMin) {
		this.roomSizeMin = roomSizeMin;
	}
	public String getRoomSizeMax() {
		return roomSizeMax;
	}
	public void setRoomSizeMax(String roomSizeMax) {
		this.roomSizeMax = roomSizeMax;
	}
	public String getPriceMin() {
		return priceMin;
	}
	public void setPriceMin(String priceMin) {
		this.priceMin = priceMin;
	}
	public String getPriceMax() {
		return priceMax;
	}
	public void setPriceMax(String priceMax) {
		this.priceMax = priceMax;
	}
	
	public Long getPriceMinAsLong(){
		return Long.parseLong(priceMin);
	}
	public Long getPriceMaxAsLong(){
		return Long.parseLong(priceMax);
	}
	public Long getRoomSizeMinAsLong(){
		return Long.parseLong(roomSizeMin);
	}
	public Long getRoomSizeMaxAsLong(){
		return Long.parseLong(roomSizeMax);
	}
	public Long getAddCostMaxAsLong(){
		try {
			return Long.parseLong(addCostMax);
		} catch(Exception e){
			
		}
		return 1000L;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddCostMax() {
		return addCostMax;
	}
	public void setAddCostMax(String addCostMax) {
		this.addCostMax = addCostMax;
	}
	
	
	
	public String getEarliestMoveInDate() {
		return earliestMoveInDate;
	}
	public void setEarliestMoveInDate(String earliestMoveInDate) {
		this.earliestMoveInDate = earliestMoveInDate;
		setEarliestMoveInDateD(null);
	}
	public String getLatestMoveInDate() {
		return latestMoveInDate;
	}
	public void setLatestMoveInDate(String latestMoveInDate) {
		this.latestMoveInDate = latestMoveInDate;
		setLatestMoveInDateD(null);
	}
	
	
	
	public Date getEarliestMoveInDateD() {
		return earliestMoveInDateD;
	}
	public void setEarliestMoveInDateD(Date earliestMoveInDateD) {
		this.earliestMoveInDateD = DateConverter.parseDate(earliestMoveInDate);
	}
	public Date getLatestMoveInDateD() {
		return latestMoveInDateD;
	}
	public void setLatestMoveInDateD(Date latestMoveInDateD) {
		this.latestMoveInDateD = DateConverter.parseDate(latestMoveInDate);
	}
	
}
