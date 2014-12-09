package org.sample.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.sample.model.Ad;
import org.sample.model.Picture;

@Entity
public class Ad {
	
	@Id
    @GeneratedValue
    private Long id;
	private Long placerId;
	private Timestamp timestamp;
	private String postingDateFormatted;
	private String title;
	private String street;
	private Long houseNr;
	private String city;
	private Long zip;
	private Long rent;
	private Long addCost;
	
	private String dateIn;
	private String dateOut;
	private Date dateInD;
	private Date dateOutD;
	
	private Long roomSize;
	
	@Lob
	private String description;
	
	private Long distanceToPublicTransport;
	private Long distanceToShopping;
	private String us;
	private String you;
	private String lat;
	private String lng;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Enquiry> enquiries = new HashSet<Enquiry>();
	
	public Set<Enquiry> getEnquiries() {
		return enquiries;
	}

	public void setEnquiries(Set<Enquiry> enquiries) {
		this.enquiries = enquiries;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Set<Picture> pictures = new HashSet<Picture>();

	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}

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
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	this.timestamp = timestamp;
    	postingDateFormatted = dateFormat.format(timestamp);
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
    
    /*
    public String getType(){
    	return type;
    }
    
    public void setType(String type){
    	this.type = type;
    }
    */

	
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
		if(roomSize!=null)
			this.roomSize = roomSize;
		else
			this.roomSize = 0L;
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

	public Long getPlacerId() {
		return placerId;
	}

	public void setPlacerId(Long placerId) {
		this.placerId = placerId;
	}

	public String getPostingDateFormatted() {
		return postingDateFormatted;
	}

	public void setPostingDateFormatted(String postingDateFormatted) {
		this.postingDateFormatted = postingDateFormatted;
	}

	public Long getDistanceToPublicTransport() {
		return distanceToPublicTransport;
	}

	public void setDistanceToPublicTransport(Long distanceToPublicTransport) {
		this.distanceToPublicTransport = distanceToPublicTransport;
	}

	public Long getDistanceToShopping() {
		return distanceToShopping;
	}

	public void setDistanceToShopping(Long distanceToShopping) {
		this.distanceToShopping = distanceToShopping;
	}

	public Date getDateOutD() {
		return dateOutD;
	}

	public void setDateOutD(Date dateOutD) {
		this.dateOutD = dateOutD;
	}

	public Date getDateInD() {
		return dateInD;
	}

	public void setDateInD(Date dateInD) {
		this.dateInD = dateInD;
	}
}
