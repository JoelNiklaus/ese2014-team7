package org.sample.model.dao;

import java.util.List;

import org.sample.model.Ad;
import org.springframework.data.repository.CrudRepository;


public interface AdDao  extends CrudRepository<Ad,Long>{

	
	List<Ad> findByRentBetweenAndRoomSizeBetweenAndCityContaining(Long priceMin, Long priceMax, Long roomSizeMin, Long roomSizeMax, String city);

	
	List<Ad> findByPlacerId(Long loggedInUserId);
	
}