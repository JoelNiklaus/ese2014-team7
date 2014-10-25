package org.sample.controller.pojos;

public class SearchForm {
	private String priceMin;
	private String priceMax;
	private String roomSizeMin;
	private String roomSizeMax;
	

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
}
