package org.sample.controller;

import javax.validation.Valid;

import org.sample.controller.pojos.SearchForm;
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
    	ModelAndView model = new ModelAndView("search");
    	
    	return model;
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView index() {
    	ModelAndView model = new ModelAndView("search");
		model.addObject("searchForm", new SearchForm());
        return model;
    }


}
