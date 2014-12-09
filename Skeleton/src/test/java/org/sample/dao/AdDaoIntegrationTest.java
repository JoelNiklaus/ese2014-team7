package org.sample.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.model.Ad;
import org.sample.model.dao.AdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/spring*.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AdDaoIntegrationTest {

	@Autowired
	AdDao adDao;
	
	@Test
	public void testFindByPlacerId() {
    	
		Long LOGGED_IN_USER_ID = 1L;
		
		Ad ad = new Ad();
		ad.setPlacerId(LOGGED_IN_USER_ID);
		
		ad = adDao.save(ad);
		List<Ad> adList = adDao.findByPlacerId(LOGGED_IN_USER_ID);
		assertEquals(adList.get(0).getPlacerId(), LOGGED_IN_USER_ID);
		
	}
	
	@Test 
	public void testFindByRentBetweenAndRoomSizeBetweenAndCityContainingAndAddCostLessThan(){
		
		Long PRICE_MIN = 1L;
		Long PRICE_MAX = 10L;
		Long ROOM_SIZE_MIN = 1L;
		Long ROOM_SIZE_MAX = 10L;
		String CITY = "testCity";
		Long ADD_COST_MAX = 10L;
		
		//adIB --> ad inside of the chosen boundries
		Ad adIB = new Ad();
		
		adIB.setAddCost(10L-1);
		adIB.setRent(5L);
		adIB.setCity("testCity");
		adIB.setRoomSize(5L);
		
		adIB = adDao.save(adIB);
		List<Ad> adIBList = adDao.findByRentBetweenAndRoomSizeBetweenAndCityContainingAndAddCostLessThan(PRICE_MIN, PRICE_MIN, ROOM_SIZE_MIN, ROOM_SIZE_MAX, CITY, ADD_COST_MAX);
		assert(adIBList.get(0).getRent() > PRICE_MIN);
		assert(adIBList.get(0).getRent() < PRICE_MAX);
		assert(adIBList.get(0).getRoomSize() > ROOM_SIZE_MIN);
		assert(adIBList.get(0).getRoomSize() < ROOM_SIZE_MIN);
		assertEquals(adIBList.get(0).getCity(), CITY);
		assert(adIBList.get(0).getAddCost() < ADD_COST_MAX);
		
		//adOB --> ad outside of the chosen boundries
		Ad adOB = new Ad();
		
		adIB.setAddCost(10L+1);
		adIB.setRent(15L);
		adIB.setCity("notTestCity");
		adIB.setRoomSize(15L);
		
		adOB = adDao.save(adOB);
		List<Ad> adOBList = adDao.findByRentBetweenAndRoomSizeBetweenAndCityContainingAndAddCostLessThan(PRICE_MIN, PRICE_MIN, ROOM_SIZE_MIN, ROOM_SIZE_MAX, CITY, ADD_COST_MAX);
		assertFalse(adOBList.get(0).getRent() > PRICE_MIN);
		assertFalse(adOBList.get(0).getRent() < PRICE_MAX);
		assert(adIBList.get(0).getRoomSize() < ROOM_SIZE_MIN);
		assert(adIBList.get(0).getRoomSize() > ROOM_SIZE_MIN);
		assertNotEquals(adIBList.get(0).getCity(), CITY);
		assert(adIBList.get(0).getAddCost() > ADD_COST_MAX);
		
	}
}
