package org.sample.controller.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.sample.controller.pojos.SearchForm;
import org.sample.model.Ad;
import org.sample.model.Search;
import org.sample.model.User;
import org.sample.model.dao.AdDao;
import org.sample.model.dao.SearchDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired LoginService loginService;
	@Autowired AdDao adDao;
	@Autowired SearchDao searchDao;
	
	// one month
	private static final int SEARCH_EXPIRING_PERIOD = 1000*60*60*24*30;
	
	public Iterable<Ad> computeSearchResults(SearchForm searchForm) {
		Iterable<Ad> searchResults;
		
		Long priceMin = searchForm.getPriceMinAsLong();
		Long priceMax = searchForm.getPriceMaxAsLong();
		Long roomSizeMin = searchForm.getRoomSizeMinAsLong();
		Long roomSizeMax = searchForm.getRoomSizeMaxAsLong();
		Long addCostMax = searchForm.getAddCostMaxAsLong();
		String city = searchForm.getCity();
		Date earliestDateIn = searchForm.getEarliestMoveInDateD();
		Date latestDateIn = searchForm.getLatestMoveInDateD();
		
		if(earliestDateIn==null && latestDateIn==null)
			searchResults = adDao.findByRentBetweenAndRoomSizeBetweenAndCityContainingAndAddCostLessThan(priceMin, priceMax, roomSizeMin, roomSizeMax, city, addCostMax);
		else if(earliestDateIn==null)
			searchResults = adDao.findByRentBetweenAndRoomSizeBetweenAndCityContainingAndAddCostLessThanAndDateInDLessThanEqual(priceMin, priceMax, roomSizeMin, roomSizeMax, city, addCostMax, latestDateIn);
		else if(latestDateIn==null)
			searchResults = adDao.findByRentBetweenAndRoomSizeBetweenAndCityContainingAndAddCostLessThanAndDateInDGreaterThanEqual(priceMin, priceMax, roomSizeMin, roomSizeMax, city, addCostMax, earliestDateIn);
		else
			searchResults = adDao.findByRentBetweenAndRoomSizeBetweenAndCityContainingAndAddCostLessThanAndDateInDBetween(priceMin, priceMax, roomSizeMin, roomSizeMax, city, addCostMax, earliestDateIn, latestDateIn);
		
		return searchResults;
	}
	

	@Transactional
	public Search saveSearch(SearchForm searchForm) {
		Long priceMin = searchForm.getPriceMinAsLong();
		Long priceMax = searchForm.getPriceMaxAsLong();
		Long roomSizeMin = searchForm.getRoomSizeMinAsLong();
		Long roomSizeMax = searchForm.getRoomSizeMaxAsLong();
		String city = searchForm.getCity();
		String earliestDateIn = searchForm.getEarliestMoveInDate();
		String latestDateOut = searchForm.getLatestMoveInDate();
		
		Search search = new Search(new Long(0), priceMin, priceMax, roomSizeMin, roomSizeMax, city, earliestDateIn, latestDateOut);
		search.setUserId(loginService.getLoggedInUser().getId());
		search.setTimestamp(new Timestamp(System.currentTimeMillis()));

		searchDao.save(search);

		return search;
	}

	@Transactional
	public Iterable<Search> findSearches(User user) {
		Iterable<Search> allSearches = searchDao.findAll();
		LinkedList<Search> results = new LinkedList<Search>();

		for(Search s : allSearches)
			if(s.getUserId().equals(loginService.getLoggedInUser().getId()))
				//display only searches which are newer than a constant.
				if(s.getTimestamp().compareTo(new Timestamp(System.currentTimeMillis())) > SEARCH_EXPIRING_PERIOD)
					results.add(s);

		return (Iterable<Search>)results;
	}

	@Transactional
	public boolean alreadySaved(User user, Long searchId) {
		Iterable<Search> searches = findSearches(user);
		
		for(Search search : searches) {
			if(search.getId().equals(searchId))
				return true;
		}
		
		return false;
	}
	
	@Transactional
	public Search removeSearch(Search Search) {
		searchDao.delete(Search);
		
		return Search;
	}

}
