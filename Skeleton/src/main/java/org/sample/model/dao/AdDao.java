package org.sample.model.dao;

import java.util.List;

import org.sample.model.Ad;
import org.springframework.data.repository.CrudRepository;


public interface AdDao  extends CrudRepository<Ad,Long>{
	List<Ad> findByRentBetweenAndRoomSizeBetween(Long priceMin, Long priceMax, Long roomSizeMin, Long roomSizeMax);
	List<Ad> findByRentGreaterThanAndRoomSizeBetween(Long priceMin, Long roomSizeMin, Long roomSizeMax);
	List<Ad> findByRentBetweenAndRoomSizeGreaterThan(Long priceMin, Long priceMax, Long roomSizeMin);
	List<Ad> findByRentGreaterThanAndRoomSizeGreaterThan(Long priceMin, Long roomSizeMin);
}