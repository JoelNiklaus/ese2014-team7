package org.sample.controller;


import javax.validation.Valid;

import org.sample.controller.pojos.AdForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.AdService;
import org.sample.controller.service.LoginService;
import org.sample.model.Ad;
import org.sample.model.User;
import org.sample.model.dao.AdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdController {

    @Autowired
    LoginService loginService;
    @Autowired
    AdService adService;
    @Autowired
    AdDao adRepositry;
    
    @RequestMapping(value = "/createAd", method = RequestMethod.POST)
    public ModelAndView createAd(@Valid AdForm adForm, BindingResult result, RedirectAttributes redirectAttributes){
    	ModelAndView model;
    	if (!result.hasErrors()){
    			adService.saveFrom(adForm);
        		model = new ModelAndView("adCreated");	
    	}else{
    		model = new ModelAndView("createAd");
    	}
    	return model;
    }
    
    @RequestMapping(value = "/createAd", method = RequestMethod.GET)
    public ModelAndView createAd() {
    	ModelAndView model = new ModelAndView("createAd");
		model.addObject("adForm", new AdForm());
        return model;
    }
    //to adjust
    @RequestMapping(value = "/adView", method = RequestMethod.GET)
    public ModelAndView adView() {
    	ModelAndView model = new ModelAndView("adView");
    	model.addObject("adView",adService.adCatcher());
    	return model;

    }

    @RequestMapping("ad")
    public ModelAndView findUser(@RequestParam String id) {
    	ModelAndView model;
	    Ad ad = adRepositry.findOne(new Long(id));
	    
	    if(ad != null){
	    	model = new ModelAndView("ad");
	    	model.addObject("ad", ad);
	    	model.addObject("shortDescription", "<b>"+ad.getTitle()+"</b><br />"+ad.getStreet()+" "+ad.getHouseNr()+"<br />" +ad.getZip()+" "+ad.getCity());
	    } else {
	    	model = new ModelAndView("404");
	    }
	    return model;
    }
    
    @RequestMapping(value = "/bookmark", method = RequestMethod.GET)
    public ModelAndView index(@RequestParam String id) {
    	ModelAndView model = new ModelAndView("profile");
    	
    	//TODO Bookmarking happens here
    	// I need User to save connection between user and ad! 	
    	Ad ad = adRepositry.findOne(new Long(id));
    	User user;
    	
    	model.addObject("profileForm", new SignupForm());
		model.addObject("message", "Ad not yet successfully bookmarked.");
        return model;
    }
}