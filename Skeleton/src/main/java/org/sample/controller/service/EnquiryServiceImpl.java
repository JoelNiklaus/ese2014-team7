package org.sample.controller.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;

import org.json.JSONException;
import org.sample.controller.NominatimConnector;
import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.EnquiryForm;
import org.sample.controller.pojos.LoginForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.pojos.AdForm;
import org.sample.model.Address;
import org.sample.model.Coordinates;
import org.sample.model.Enquiry;
import org.sample.model.User;
import org.sample.model.Ad;
import org.sample.model.dao.AddressDao;
import org.sample.model.dao.EnquiryDao;
import org.sample.model.dao.UserDao;
import org.sample.model.dao.AdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
public class EnquiryServiceImpl implements EnquiryService {

	private long hardcodedSenderId = 0;
	private long hardcodedReceiverId = 0;
	
	@Autowired EnquiryDao enquiryDao; //TODO: @Silas: what does autowired annotation actually do?

	@Transactional //TODO: @Silas: what does transactional annotation actually do?
	public EnquiryForm submit(EnquiryForm enquiryForm) {
		Enquiry enquiry = new Enquiry();
		
		//TODO: handle IDs in an appropriate way for now, change when login works
		enquiry.setSenderId(hardcodedSenderId);
		Long receiverId = hardcodedReceiverId; //TODO: this should be the ad placer ID
		enquiry.setReceiverId(receiverId);
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
			if(e.getSenderId() == hardcodedSenderId) //TODO: HARDCODED!
				results.add(e);
		}
		
		System.out.println("Results: " + results.size());
		
		return (Iterable<Enquiry>)results;
	}

	public Iterable<Enquiry> findReceivedEnquiries() {
		Iterable<Enquiry> allEnquiries = enquiryDao.findAll();
		LinkedList<Enquiry> results = new LinkedList<Enquiry>();
		
		for(Enquiry e : allEnquiries)
		{
			if(e.getReceiverId() == hardcodedReceiverId) //TODO: HARDCODED!
				results.add(e);
		}
		
		System.out.println("Results: " + results.size());
		
		return (Iterable<Enquiry>)results;
	}
    
    

}
