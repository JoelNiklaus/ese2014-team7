package org.sample.controller;


import java.io.IOException;

import org.json.JSONException;
import org.sample.controller.service.LoginService;
import org.sample.model.Ad;
import org.sample.model.User;
import org.sample.model.dao.AdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MapController {

    @Autowired
    LoginService sampleService;
    @Autowired
    AdDao adRepository;

    @Autowired
    LoginService loginService;
    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public ModelAndView index() {
    	ModelAndView model = new ModelAndView("map");
    	model.addObject("adList", adRepository.findAll());
    	model.addObject("loggedInUser", loginService.getLoggedInUser());

        return model;
    }

//    @ModelAttribute("adList")
//    public Iterable<Ad> populateAdList(){
//	    Iterable<Ad> adList = ;
//	    return adList;
//    }
}
