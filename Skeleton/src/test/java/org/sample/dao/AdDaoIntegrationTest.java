package org.sample.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.model.Ad;
import org.sample.model.Address;
import org.sample.model.Picture;
import org.sample.model.User;
import org.sample.model.dao.AdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/spring*.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AdDaoIntegrationTest {

	@Autowired
	AdDao adDao;
	
	final Long PRICE_MIN = 1L;
	final Long PRICE_MAX = 10L;
	final Long ROOM_SIZE_MIN = 1L;
	final Long ROOM_SIZE_MAX = 10L;
	final String CITY = "testCity";
	final Long ADD_COST_MAX = 10L;
	final Date LESS_THAN_EARLIEST_DATE_IN = new Date(50);
	final Date GREATER_THAN_EARLIEST_DATE_IN = new Date(150);
	final Date EARLIEST_DATE_IN = new Date(100);
	final Date LATEST_DATE_IN = new Date(200);
	
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
    public void testPictureReference() {
    	String FILEPATH = "testFilePath"; 
    	
    	Picture picture = new Picture();
    	picture.setFilePath(FILEPATH);
    	Set<Picture> pictures = new HashSet<Picture>(0);
    	pictures.add(picture);
    	Ad ad = new Ad();
    	ad.setPlacerId(1L);
    	ad.setPictures(pictures);
    	adDao.save(ad);
        ad = adDao.findByPlacerId(1L).get(0);
    	
        assertEquals(ad.getPictures().iterator().next().getFilePath(), FILEPATH);
    }
	
	@Test 
	public void testFindByRentBetweenAndRoomSizeBetweenAndCityContainingAndAddCostLessThan(){

		//adIB --> ad inside of the chosen boundries
		Ad adOne = new Ad();
		
		adOne.setAddCost(ADD_COST_MAX-1);
		adOne.setRent(ROOM_SIZE_MAX-1);
		adOne.setCity(CITY);
		adOne.setRoomSize(ROOM_SIZE_MAX-1);

		adDao.save(adOne);
		
		List<Ad> adOneList = adDao.findByRentBetweenAndRoomSizeBetweenAndCityContainingAndAddCostLessThan(PRICE_MIN, PRICE_MAX, ROOM_SIZE_MIN, ROOM_SIZE_MAX, CITY, ADD_COST_MAX);
		assertFalse(adOneList.isEmpty());
		assert(adOneList.get(0).getRent() > PRICE_MIN);
		assert(adOneList.get(0).getRent() < PRICE_MAX);
		assert(adOneList.get(0).getRoomSize() > ROOM_SIZE_MIN);
		assert(adOneList.get(0).getRoomSize() < ROOM_SIZE_MIN);
		assertTrue(adOneList.get(0).getCity() == CITY);
		assert(adOneList.get(0).getAddCost() < ADD_COST_MAX);
		
		//adOB --> ad outside of the chosen boundries
		Ad adTwo = new Ad();
		
		adTwo.setAddCost(ADD_COST_MAX+1);
		adTwo.setRent(PRICE_MAX+1);
		adTwo.setCity("NOT TEST CITY");
		adTwo.setRoomSize(ROOM_SIZE_MAX+1);
		
		adDao.save(adTwo);
		
		List<Ad> adTwoList = adDao.findByRentBetweenAndRoomSizeBetweenAndCityContainingAndAddCostLessThan(PRICE_MIN, PRICE_MAX, ROOM_SIZE_MIN, ROOM_SIZE_MAX, CITY, ADD_COST_MAX);
		assertFalse(adTwoList.contains(adTwo));
		
	}
	
	@Test
	public void testFindByRentBetweenAndRoomSizeBetweenAndCityContainingAndAddCostLessThanAndDateInDGreaterThanEqual(){
		Ad adThree = new Ad();
		
		adThree.setAddCost(ADD_COST_MAX-1);
		adThree.setRent(PRICE_MAX-1);
		adThree.setCity(CITY);
		adThree.setRoomSize(ROOM_SIZE_MAX-1);
		adThree.setDateInD(GREATER_THAN_EARLIEST_DATE_IN);
		
		adDao.save(adThree);
		
		List<Ad> adThreeList = adDao.findByRentBetweenAndRoomSizeBetweenAndCityContainingAndAddCostLessThanAndDateInDGreaterThanEqual(PRICE_MIN, PRICE_MAX, ROOM_SIZE_MIN, ROOM_SIZE_MAX, CITY, ADD_COST_MAX, EARLIEST_DATE_IN);
		assertFalse(adThreeList.isEmpty());
		assert(adThreeList.get(0).getRent() > PRICE_MIN);
		assert(adThreeList.get(0).getRent() < PRICE_MAX);
		assert(adThreeList.get(0).getRoomSize() > ROOM_SIZE_MIN);
		assert(adThreeList.get(0).getRoomSize() < ROOM_SIZE_MIN);
		assertTrue(adThreeList.get(0).getCity() == CITY);
		assert(adThreeList.get(0).getAddCost() < ADD_COST_MAX);
		assertTrue(adThreeList.get(0).getDateInD().compareTo(EARLIEST_DATE_IN) > 0);
		
	}
}
