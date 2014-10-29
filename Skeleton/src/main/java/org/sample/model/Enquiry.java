package org.sample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Enquiry {

	@Id
    @GeneratedValue
	private Long enquiryId;

	private Long senderId;
    private Long receiverId;
    private String messageText;
    
    
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
    
	
}
