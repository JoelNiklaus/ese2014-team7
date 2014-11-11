package org.sample.controller.pojos;

import org.sample.model.Ad;
import org.sample.model.Enquiry;

public class EnquiryRatingForm {

	private int rating;
	private Enquiry enquiry;
	private long enquiryId;

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Enquiry getEnquiry() {
		return enquiry;
	}

	public void setEnquiry(Enquiry enquiry) {
		this.enquiry = enquiry;
	}

	public long getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(long enquiryId) {
		this.enquiryId = enquiryId;
	}
	
}
