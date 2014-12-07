package org.sample.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;



@Entity
public class Enquiry{

	@Id
    @GeneratedValue
	private Long enquiryId;
	
	private Long adId;
	private Timestamp timestamp;
	private Long senderId;
    private Long receiverId;
    private String messageText;

    @OneToOne(fetch = FetchType.EAGER)
    private User sender;
    
	private int rating;
    private boolean unread;
    private String enquiryRatingComment;
    
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<VisitAppointment> visitAppointments;
    
    @Transient
    private Ad ad;
    
    public List<VisitAppointment> getVisitAppointments() {
		return visitAppointments;
	}

	public void setVisitAppointments(List<VisitAppointment> visitAppointments) {
		this.visitAppointments = visitAppointments;
	}
	
    public Enquiry(){
    }
    
    public Long getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(Long enquiryId) {
		this.enquiryId = enquiryId;
	}
    
    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long id) {
        this.senderId = id;
    }

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public boolean isUnread() {
		return unread;
	}

	public void setUnread(boolean unread) {
		this.unread = unread;
	}

	public String getEnquiryRatingComment() {
		return enquiryRatingComment;
	}

	public void setEnquiryRatingComment(String enquiryRatingComment) {
		this.enquiryRatingComment = enquiryRatingComment;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

}
