package org.sample.controller.pojos;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;



public class VisitAppointmentForm {

	private Long id;
	
	private Long enquiryId;
	@NotNull
	private String startDate;
	@NotNull
	private String endDate;
	

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public Long getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(Long enquiryId) {
		this.enquiryId = enquiryId;
	}

}
