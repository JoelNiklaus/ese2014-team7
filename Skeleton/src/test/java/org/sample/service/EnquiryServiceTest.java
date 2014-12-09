package org.sample.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.sample.controller.pojos.EnquiryForm;
import org.sample.controller.service.EnquiryService;
import org.sample.controller.service.EnquiryServiceImpl;
import org.sample.controller.service.LoginService;
import org.sample.controller.service.LoginServiceImpl;
import org.sample.model.Ad;
import org.sample.model.dao.AdDao;
import org.sample.model.dao.EnquiryDao;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EnquiryServiceTest {

	@Autowired 
	LoginService loginService;
	@Autowired 
	AdDao adDao;
	@Autowired 
	EnquiryDao enquiryDao;
	
	@Autowired 
	EnquiryForm enquiryForm;
	@Autowired
	EnquiryServiceImpl enquiryServiceImpl;
	
	private EnquiryService enquiryService;
	
	
	@Before
	public void doSetup(){
		Ad ad = mock(Ad.class);
		EnquiryService enquiryService = mock(EnquiryService.class);
		enquiryServiceImpl = new EnquiryServiceImpl();
	}
	
	@Test
	public void testSubmit() {
		
		EnquiryForm enquiryForm = new EnquiryForm();
		
		assertNull(enquiryForm.getSenderId());
		
		enquiryForm = enquiryServiceImpl.submit(enquiryForm);
		
		assertNotNull(enquiryForm.getSenderId());
		assertTrue(enquiryForm.getSenderId() > 0);
		
	}
}
