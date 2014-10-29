package org.sample.controller.service;

import java.io.IOException;
import java.sql.Timestamp;

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

	@Autowired EnquiryDao enquiryDao; //TODO: @Silas: what does autowired annotation do?

	@Transactional //TODO: @Silas: what does transactional annotation do?
	public EnquiryForm sendEnquiry(EnquiryForm enquiryForm) {
		Enquiry enquiry = new Enquiry();
		
		//TODO: handle IDs in an appropriate way for now, change when login works
		enquiry.setSenderId(0L);
		enquiry.setReceiverId(1L);
		enquiry.setMessageText(enquiryForm.getMessageText());
		
		return null;
	}
    
    

}
