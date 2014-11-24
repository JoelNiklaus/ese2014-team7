package org.sample.controller;

import javax.validation.Valid;

import org.sample.controller.pojos.LoginForm;
import org.sample.controller.pojos.SearchForm;
import org.sample.controller.service.EnquiryService;
import org.sample.controller.service.LoginService;
import org.sample.controller.service.SearchService;
import org.sample.controller.service.UpdateService;
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
	LoginService loginService;
	@Autowired
	SearchService searchService;
	@Autowired
	SearchDao searchDao;
	@Autowired
	AdDao adDao;
	@Autowired
	UpdateService updateService;
	
	/**
	 * Returns search model for a given search-template. Is also the home page!
	 * 
	 * @param searchId		id of search-template to be used
	 * 
	 * @return				search results for given template
	 */
	@RequestMapping(value = {"", "/", "/search"} , method = RequestMethod.GET)
	public ModelAndView index(@RequestParam(value="searchId", required=false) String searchId) {
		ModelAndView model = new ModelAndView("search");
		model.addObject("searchForm", new SearchForm());
		model.addObject("loggedInUser", loginService.getLoggedInUser());
		updateService.updateNumberOfUnreadItems(model);
		Iterable<Ad> searchResults = adDao.findAll();
		Search searchAttributes;
		
		searchAttributes = new Search(new Long(0), new Long(0), new Long(3000), new Long(0),new Long(300), "");
		try{
			searchAttributes = searchDao.findOne(Long.parseLong(searchId));
			
		} catch(Exception e) {
			System.out.println(e);
		}
		model.addObject("searchAttributes", searchAttributes);
		if(searchResults != null)
			model.addObject("searchResults", searchResults);
		
		
		
		return model;
	}

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

		ModelAndView model = new ModelAndView("search");
		Search searchAttributes;
		
		if(searchId != null){
			searchAttributes = new Search(new Long(0), new Long(0), new Long(3000), new Long(0),new Long(300), "");
		} else {
			Long priceMin = searchForm.getPriceMinAsLong();
			Long priceMax = searchForm.getPriceMaxAsLong();
			Long roomSizeMin = searchForm.getRoomSizeMinAsLong();
			Long roomSizeMax = searchForm.getRoomSizeMaxAsLong();
			String city = searchForm.getCity();
			searchAttributes = new Search(new Long(0), priceMin, priceMax, roomSizeMin, roomSizeMax, city);
		}
		
		model.addObject("loggedInUser", loginService.getLoggedInUser());
		updateService.updateNumberOfUnreadItems(model);
		model.addObject("searchAttributes", searchAttributes);

		Iterable<Ad> searchResults = searchService.computeSearchResults(searchForm);

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
		assert loginService.getLoggedInUser() != null;
		
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
		updateService.updateNumberOfUnreadItems(model);

		return model;
	}


	@RequestMapping("/searches")
	public ModelAndView searches() {
		assert loginService.getLoggedInUser() != null;
		
		ModelAndView model = new ModelAndView("searches");

		User user = loginService.getLoggedInUser();
		model.addObject("searches", searchService.findSearches(user));
		model.addObject("loggedInUser", user);
		updateService.updateNumberOfUnreadItems(model);
		
		return model;
	}

	@RequestMapping(value = "/removeSearch", method = RequestMethod.GET)
	public ModelAndView removeSearch(@RequestParam String id) {
		assert loginService.getLoggedInUser() != null;
		assert id != null;
		
		ModelAndView model = searches();

		try {
			long searchId = Long.parseLong(id);
			Search search = searchDao.findOne(searchId);

			User user = loginService.getLoggedInUser();
			updateService.updateNumberOfUnreadItems(model);
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
