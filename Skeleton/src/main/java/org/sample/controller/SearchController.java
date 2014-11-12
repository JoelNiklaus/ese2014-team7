package org.sample.controller;

import javax.validation.Valid;

import org.sample.controller.pojos.LoginForm;
import org.sample.controller.pojos.SearchForm;
import org.sample.controller.service.LoginService;
import org.sample.controller.service.SearchService;
import org.sample.model.Ad;
import org.sample.model.Search;
import org.sample.model.User;
import org.sample.model.dao.AdDao;
import org.sample.model.dao.SearchDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SearchController {

	@Autowired
	AdDao adRepository;
	@Autowired
	LoginService loginService;
	@Autowired
	SearchService searchService;
	@Autowired
	SearchDao searchRepository;

	/**
	 * Assembles a model providing search functionality (specifying search criteria, displaying only ads
	 * matching that criteria).
	 * 
	 * @param searchForm			completed search form
	 * @param result
	 * @param redirectAttributes
	 * @param searchId				id of search-template
	 * 
	 * @return						model with tools to specify search criteria and displaying search results
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)    
	public ModelAndView search(@Valid SearchForm searchForm, BindingResult result, RedirectAttributes redirectAttributes, @RequestParam(value="searchId",required=false) String searchId){


		Long priceMin = searchForm.getPriceMinAsLong();
		Long priceMax = searchForm.getPriceMaxAsLong();
		Long roomSizeMin = searchForm.getRoomSizeMinAsLong();
		Long roomSizeMax = searchForm.getRoomSizeMaxAsLong();
		String city = searchForm.getCity();
		Iterable<Ad> searchResults;

		ModelAndView model = new ModelAndView("search");
		Search searchAttributes;
		if(searchId != null){
			searchAttributes = new Search(new Long(0), new Long(0), new Long(3000), new Long(0),new Long(300), "");
		} else {

			searchAttributes = new Search(new Long(0), priceMin, priceMax, roomSizeMin, roomSizeMax, city);

		}
		model.addObject("loggedInUser", loginService.getLoggedInUser());
		model.addObject("searchAttributes", searchAttributes);



		//TODO Replace magic Number by config file
		if(city.equals("")){
			if(priceMax == 3000){
				searchResults = adRepository.findByRentGreaterThanAndRoomSizeBetween(priceMin-1, roomSizeMin, roomSizeMax);
			} else if(roomSizeMax==300){
				searchResults = adRepository.findByRentBetweenAndRoomSizeGreaterThan(priceMin, priceMax, roomSizeMin-1);
			} else if((roomSizeMax == 300) && (priceMax == 3000)){
				searchResults = adRepository.findByRentGreaterThanAndRoomSizeGreaterThan(priceMin, roomSizeMin);
			} else {
				searchResults = adRepository.findByRentBetweenAndRoomSizeBetween(priceMin, priceMax, roomSizeMin, roomSizeMax);
			}
		} else {
			if(priceMax == 3000){
				searchResults = adRepository.findByRentGreaterThanAndRoomSizeBetweenAndCityLike(priceMin, roomSizeMin, roomSizeMax, city);
			} else if(roomSizeMax==300){
				searchResults = adRepository.findByRentBetweenAndRoomSizeGreaterThanAndCityLike(priceMin, priceMax, roomSizeMin, city);
			} else if((roomSizeMax == 300) && (priceMax == 3000)){
				searchResults = adRepository.findByRentGreaterThanAndRoomSizeGreaterThanAndCityLike(priceMin, roomSizeMin, city);
			} else {
				searchResults = adRepository.findByRentBetweenAndRoomSizeBetweenAndCityLike(priceMin, priceMax, roomSizeMin, roomSizeMax, city);
			}
		}

		if(searchResults != null)
			model.addObject("searchResults", searchResults);

		return model;
	}

	/**
	 * Returns search model for a given search-template.
	 * 
	 * @param searchId		id of search-template to be used
	 * 
	 * @return				search results for given template
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value="searchId",required=false) String searchId) {
		ModelAndView model = new ModelAndView("search");
		model.addObject("searchForm", new SearchForm());
		model.addObject("loggedInUser", loginService.getLoggedInUser());
		Iterable<Ad> searchResults = adRepository.findAll();
		Search searchAttributes;
		
		searchAttributes = new Search(new Long(0), new Long(0), new Long(3000), new Long(0),new Long(300), "");
		try{
			searchAttributes = searchRepository.findOne(Long.parseLong(searchId));
			
		} catch(Exception e) {
			System.out.println(e);
		}
		model.addObject("searchAttributes", searchAttributes);
		if(searchResults != null)
			model.addObject("searchResults", searchResults);
		return model;
	}
	
	/**
	 * Saves current search template to DB.
	 * 
	 * @param searchForm			search form to be saved
	 * @param result
	 * @param redirectAttributes
	 * @param searchId				
	 * @return						search model
	 */
	@RequestMapping(value = "/saveSearch", method = RequestMethod.POST)    
	public ModelAndView saveSearch(@Valid SearchForm searchForm, BindingResult result, RedirectAttributes redirectAttributes, @RequestParam(value="searchId",required=false) String searchId){
		ModelAndView model = search(searchForm, result, redirectAttributes, searchId);
		String message = "";
		
		if(loginService.getLoggedInUser() != null) {
			try {
				searchService.saveSearch(searchForm);
				message = "Search successfully saved";
			} catch (Exception e) {
				e.printStackTrace();
				message = "Search could not be saved";
			}
		}
		else {
			model = new ModelAndView("login");
			model.addObject("loginForm", new LoginForm());
		}
		
		model.addObject("message", message);
		model.addObject("loggedInUser", loginService.getLoggedInUser());

		return model;
	}


	@RequestMapping("/searches")
	public ModelAndView searches() {
		ModelAndView model = new ModelAndView("searches");

		User user = loginService.getLoggedInUser();
		model.addObject("searches", searchService.findSearches(user));
		model.addObject("loggedInUser", user);
		
		return model;
	}

	@RequestMapping(value = "/removeSearch", method = RequestMethod.GET)
	public ModelAndView removeSearch(@RequestParam String id) {
		ModelAndView model = searches();

		try {
			long searchId = Long.parseLong(id);
			Search search = searchRepository.findOne(searchId);

			User user = loginService.getLoggedInUser();
			searchService.removeSearch(search);
			// update search list
			model = searches();
			model.addObject("message", "search successfully removed.");
			model.addObject("searches", searchService.findSearches(user)); 
		} catch(NumberFormatException ex){
			model = new ModelAndView("404");
		} catch(IllegalArgumentException e) {
			model = new ModelAndView("404");
		}
		
		return model;
	}

}
