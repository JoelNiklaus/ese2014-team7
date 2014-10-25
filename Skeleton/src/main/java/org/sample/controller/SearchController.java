package org.sample.controller;

import javax.validation.Valid;

import org.sample.controller.pojos.SearchForm;
import org.sample.model.Ad;
import org.sample.model.dao.AdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SearchController {

    @Autowired
    AdDao adRepositry;
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView createAd(@Valid SearchForm searchForm, BindingResult result, RedirectAttributes redirectAttributes){
    	
    	Long priceMin = searchForm.getPriceMinAsLong();
    	Long priceMax = searchForm.getPriceMaxAsLong();
    	Long roomSizeMin = searchForm.getRoomSizeMinAsLong();
    	Long roomSizeMax = searchForm.getRoomSizeMaxAsLong();
    	String city = searchForm.getCity();
    	Iterable<Ad> searchResults;
    	
    	//TODO Replace magic Number by config file
    	if(city.equals("")){
	    	if(priceMax == 3000){
	    		searchResults = adRepositry.findByRentGreaterThanAndRoomSizeBetween(priceMin-1, roomSizeMin, roomSizeMax);
	    	} else if(roomSizeMax==300){
	    		searchResults = adRepositry.findByRentBetweenAndRoomSizeGreaterThan(priceMin, priceMax, roomSizeMin-1);
	    	} else if((roomSizeMax == 300) && (priceMax == 3000)){
	    		searchResults = adRepositry.findByRentGreaterThanAndRoomSizeGreaterThan(priceMin, roomSizeMin);
	    	} else {
	    		searchResults = adRepositry.findByRentBetweenAndRoomSizeBetween(priceMin, priceMax, roomSizeMin, roomSizeMax);
	    	}
    	} else {
	    	if(priceMax == 3000){
	    		searchResults = adRepositry.findByRentGreaterThanAndRoomSizeBetweenAndCityLike(priceMin, roomSizeMin, roomSizeMax, city);
	    	} else if(roomSizeMax==300){
	    		searchResults = adRepositry.findByRentBetweenAndRoomSizeGreaterThanAndCityLike(priceMin, priceMax, roomSizeMin, city);
	    	} else if((roomSizeMax == 300) && (priceMax == 3000)){
	    		searchResults = adRepositry.findByRentGreaterThanAndRoomSizeGreaterThanAndCityLike(priceMin, roomSizeMin, city);
	    	} else {
	    		searchResults = adRepositry.findByRentBetweenAndRoomSizeBetweenAndCityLike(priceMin, priceMax, roomSizeMin, roomSizeMax, city);
	    	}
    	}
    	ModelAndView model = new ModelAndView("search");

    	if(searchResults != null)
    		model.addObject("searchResults", searchResults);
    	
    	return model;
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView index() {
    	ModelAndView model = new ModelAndView("search");
		model.addObject("searchForm", new SearchForm());
		
		Iterable<Ad> searchResults = adRepositry.findAll();
    	if(searchResults != null)
    		model.addObject("searchResults", searchResults);
        return model;
    }


}
