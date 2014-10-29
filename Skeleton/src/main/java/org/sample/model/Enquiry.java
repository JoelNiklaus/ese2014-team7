package org.sample.model;

import javax.persistence.Entity;

@Entity
public class Enquiry {

    private Long senderId;
    private Long receiverId;
    private String messageText;
    
    
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
