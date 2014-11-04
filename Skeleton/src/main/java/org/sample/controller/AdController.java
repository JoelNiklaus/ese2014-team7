package org.sample.controller;


import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.sample.controller.PictureManager;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdController {

    @Autowired
    LoginService loginService;
    @Autowired
    AdService adService;
    @Autowired
    AdDao adRepository;
    @Autowired
    ServletContext servletContext;
    
    public final String PICTURE_LOCATION = "/img";
    
    @RequestMapping(value = "/createAd", method = RequestMethod.POST)
    public ModelAndView createAd(@Valid AdForm adForm, BindingResult result, RedirectAttributes redirectAttributes, Principal principal, @RequestParam("image") MultipartFile[] files){
    	
		PictureManager picmgr = new PictureManager();
		String path = servletContext.getRealPath(PICTURE_LOCATION);
		String filename = adForm.getStreet()+String.valueOf(adForm.getHouseNr());
		
		ArrayList<String> pictures = (picmgr.uploadMultipleFile(path, filename, files));
		try {
			if (pictures.get(0)!= null)
				adForm.setImg_one(pictures.get(0));
		} catch (Exception e) {
		}
		/*
		try {
			if (pictures.get(1)!= null)
				adForm.setImg_two(pictures.get(1));
		} catch (Exception e) {
		}
		try {
			if (pictures.get(2) != null)
				adForm.setImg_three(pictures.get(2));
		} catch (Exception e) {
		}
		try {
			if (pictures.get(3) != null)
				adForm.setImg_four(pictures.get(3));
		} catch (Exception e) {
		}
		*/

    	ModelAndView model;
    	if (!result.hasErrors()){
    			adService.saveFrom(adForm);
        		model = new ModelAndView("adCreated");	
    	}else{
    		model = new ModelAndView("createAd");
    	}
    	model.addObject("loggedInUser", loginService.getLoggedInUser());
    	return model;
    }
    
    @RequestMapping(value = "/createAd", method = RequestMethod.GET)
    public ModelAndView createAd() {
    	ModelAndView model = new ModelAndView("createAd");
		model.addObject("adForm", new AdForm());
		model.addObject("loggedInUser", loginService.getLoggedInUser());
        return model;
    }
    
    
    @RequestMapping(value = "/adView", method = RequestMethod.GET)
    public ModelAndView adView() {
    	ModelAndView model = new ModelAndView("adView");
    	model.addObject("adView",adService.adCatcher());
    	model.addObject("loggedInUser", loginService.getLoggedInUser());
    	return model;

    }

    @RequestMapping("ad")
    public ModelAndView findUser(@RequestParam String id) {
    	
    	ModelAndView model = new ModelAndView("ad");
    	//Long adId = Long.parseLong(id);
	    
	    try{
	    	Ad ad = adRepository.findOne(new Long(id));
	    	
	    	 if(ad != null){
	 	    	model.addObject("ad", ad);
	 	    	model.addObject("shortDescription", "<b>"+ad.getTitle()+"</b><br />"+ad.getStreet()+" "+ad.getHouseNr()+"<br />" +ad.getZip()+" "+ad.getCity());
	 	    	//model.addObject("newAdProfile", adService.getAd(adId));
	 			//model.addObject("pictureIds", adService.getAdPictureIds(adId));
	 			//model.addObject("mainPic", adService.getAdMainPic(adId));
	 	    } else {
	 	    	model = new ModelAndView("404");
	 	    }
	    }catch(NumberFormatException ex){
	    	model = new ModelAndView("404");
	    }
	    model.addObject("loggedInUser", loginService.getLoggedInUser());
	    return model;
    }
}