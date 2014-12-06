package org.sample.service;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.sample.controller.pojos.AdForm;
import org.sample.controller.service.AdService;
import org.sample.model.Ad;
import org.sample.model.dao.AdDao;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdServiceTest {

		private AdDao adDao;
		@Autowired
		private AdService adService;
	
		@Before
		public void doSetup(){
			adDao = mock(AdDao.class);
		}
		
		@Test
		public void testSaveFrom(){
			AdForm adForm = new AdForm();
			
			adForm.setPlacerId(1L);
		    Timestamp timestamp  = new Timestamp(System.currentTimeMillis());
			adForm.setTimestamp(timestamp);
			adForm.setTitle("testTitle");
			adForm.setStreet("testStreet");
			adForm.setHouseNr(1L);
			adForm.setCity("testCity");
			adForm.setZip(1L);
			adForm.setRent(1L);
			adForm.setAddCost(1L);
			adForm.setDateIn("testDateIn");
			adForm.setDateOut("testDateOut");
			adForm.setRoomSize(1L);
			adForm.setDescription("testDescription");
			adForm.setDistanceToPublicTransport(1L);
			adForm.setDistanceToShopping(1L);
			adForm.setUs("testUs");
			adForm.setYou("testYou");
			adForm.setLat("testLat");
			adForm.setLng("testLng");
			
			when(adDao.save(any(Ad.class))).thenAnswer(new Answer<Ad>(){
				public Ad answer(InvocationOnMock invocation) throws Throwable{
					Ad ad = (Ad) invocation.getArguments()[0];
					ad.setId(1L);
					return ad;
				}
			});

			assertNull(adForm.getId());
			
			adForm = adService.saveFrom(adForm);
		
			assertNotNull(adForm.getId());
			assertTrue(adForm.getId() > 0);			
		}
}
