package org.sample.controller.service;

import java.util.LinkedList;
import org.sample.controller.pojos.EnquiryForm;
import org.sample.model.Enquiry;
import org.sample.model.dao.EnquiryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnquiryServiceImpl implements EnquiryService {
	
	@Autowired EnquiryDao enquiryDao;
	@Autowired LoginService loginService;

	@Transactional 
	public EnquiryForm submit(EnquiryForm enquiryForm) {
		Enquiry enquiry = new Enquiry();
		
		enquiry.setSenderId(loginService.getLoggedInUser().getId());
		enquiry.setReceiverId(enquiryForm.getReceiverId());
		enquiry.setMessageText(enquiryForm.getMessageText());
		
		enquiryDao.save(enquiry);
		
		return enquiryForm;
	}

	@Transactional
	public Iterable<Enquiry> findSentEnquiries() {
		Iterable<Enquiry> allEnquiries = enquiryDao.findAll();
		LinkedList<Enquiry> results = new LinkedList<Enquiry>();
		
		for(Enquiry e : allEnquiries)
		{
			if(e.getSenderId() == loginService.getLoggedInUser().getId())
				results.add(e);
		}
		
		return (Iterable<Enquiry>)results;
	}

	@Transactional
	public Iterable<Enquiry> findReceivedEnquiries() {
		Iterable<Enquiry> allEnquiries = enquiryDao.findAll();
		LinkedList<Enquiry> results = new LinkedList<Enquiry>();
		
		for(Enquiry e : allEnquiries)
		{
			if(e.getReceiverId() == loginService.getLoggedInUser().getId())
				results.add(e);
		}
		
		return (Iterable<Enquiry>)results;
	}
    
    

}
