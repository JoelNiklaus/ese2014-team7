package org.sample.controller;


import java.security.Principal;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.sample.controller.pojos.AdForm;
import org.sample.controller.service.AdService;
import org.sample.controller.service.LoginService;
import org.sample.controller.service.PictureManager;
import org.sample.controller.service.UpdateService;
import org.sample.model.Ad;
import org.sample.model.dao.AdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    AdDao adDao;
    @Autowired
    ServletContext servletContext;
    @Autowired
	UpdateService updateService;
    
    public final String PICTURE_LOCATION = "/img";
    
    /**
     * Submits created ad and returns a confirmation model.
     * 
     * @param adForm				the completed ad form
     * @param result				
     * @param redirectAttributes
     * @param principal
     * @param files
     * 
     * @return						creation confirmation model, creation model if form invalid
     */
    @RequestMapping(value = "/createAd", method = RequestMethod.POST)
    public ModelAndView createAd(@Valid AdForm adForm, BindingResult result, RedirectAttributes redirectAttributes, Principal principal, @RequestParam("image") MultipartFile[] files){
    	assert loginService.getLoggedInUser() != null;
    	assert adForm != null;
    	
		PictureManager picmgr = new PictureManager();
		String path = servletContext.getRealPath(PICTURE_LOCATION);
		String filename = adForm.getStreet()+String.valueOf(adForm.getHouseNr());
		
		ArrayList<String> pictures = (picmgr.uploadMultipleFile(path, filename, files));
		try {
			if (pictures.get(0)!= null)
				adForm.setImg_one(pictures.get(0));
		} catch (Exception e) {
		}

    	ModelAndView model = new ModelAndView("createAd");
    	if (!result.hasErrors()){
			adService.saveFrom(adForm);
			
			model.addObject("success", "Ad successfully created.");
    	}else{
    		model.addObject("error", "Please fill out all valid information.");
    	}
    	model.addObject("loggedInUser", loginService.getLoggedInUser());
    	
    	updateService.updateNumberOfUnreadItems(model);
    	
    	return model;
    }
    
    /**
     * Builds a model for creating a new ad.
     * 
     * @return create ad model
     */
    @RequestMapping(value = "/createAd", method = RequestMethod.GET)
    public ModelAndView createAd() {
    	assert loginService.getLoggedInUser() != null;
    	
    	ModelAndView model = new ModelAndView("createAd");
		model.addObject("adForm", new AdForm());
		model.addObject("loggedInUser", loginService.getLoggedInUser());
		updateService.updateNumberOfUnreadItems(model);
        return model;
    }
    
    /**
     * Creates a model displaying all ads available in DB.
     * 
     * @return ad view model
     */
    @RequestMapping(value = "/adView", method = RequestMethod.GET)
    public ModelAndView adView() {
    	ModelAndView model = new ModelAndView("adView");
    	model.addObject("adView", adService.adCatcher());
    	model.addObject("loggedInUser", loginService.getLoggedInUser());
    	updateService.updateNumberOfUnreadItems(model);
    	return model;

    }

    /**
     * Creates a model displaying ad with given ad id.
     * 
     * @param id	ad id
     * @return		ad display model, 404 model if adId doesn't exist
     */
    @RequestMapping("ad")
    public ModelAndView displaySingleAd(@RequestParam String id) {
    	
    	ModelAndView model = new ModelAndView("ad");
	    
	    try{
	    	Ad ad = adDao.findOne(new Long(id));
	    	
	    	 if(ad != null){
	 	    	model.addObject("ad", ad);
	 	    	model.addObject("shortDescription", "<b>"+ad.getTitle()+"</b><br />"+ad.getStreet()+" "+ad.getHouseNr()+"<br />" +ad.getZip()+" "+ad.getCity());
	 	    } else {
	 	    	model = new ModelAndView("404");
	 	    }
	    }catch(NumberFormatException ex){
	    	model = new ModelAndView("404");
	    }
	    model.addObject("loggedInUser", loginService.getLoggedInUser());
	    updateService.updateNumberOfUnreadItems(model);
	    return model;
    }
    
    @RequestMapping("myAd")
    public ModelAndView showMyAd() {
    	ModelAndView model = new ModelAndView("myAd");
    
    	try{
    		Long loggedInUserId = loginService.getLoggedInUser().getId();
        	Ad ad = adDao.findOneByPlacerId(loggedInUserId);
        	
        	if(ad != null){
        		model.addObject("ad", ad);
            	Long adId = ad.getId();
            	model.addObject("adId", adId);
        	} else {
        		model = new ModelAndView("404");
        	}
    	} catch(NumberFormatException ex) {
    		model = new ModelAndView("404");
    	}
    	model.addObject("loggedInUser", loginService.getLoggedInUser());
    	updateService.updateNumberOfUnreadItems(model);
    	
    	return model;
    }
    
    @RequestMapping(value = "/deleteAd", method = RequestMethod.GET)
    public String deleteAd(
	    HttpServletRequest request, HttpServletResponse response,
	    HttpSession session, Principal principal, RedirectAttributes redirectAttributes) {
    Long adId = adDao.findOneByPlacerId(loginService.getLoggedInUser().getId()).getId();
	adService.deleteAd(adId);
	
	return "redirect:/";
    }
    
    @RequestMapping(value = "/editAd", method = RequestMethod.GET)
    public ModelAndView editAd() {
	ModelAndView model = new ModelAndView("editAd");
	AdForm adForm = new AdForm();
	Long adId = adDao.findOneByPlacerId(loginService.getLoggedInUser().getId()).getId();
	Ad ad = adService.getAd(adId);
	
	model.addObject("ad", ad);
	//model.addObject("infoMessage", message);
	
	model.addObject("adForm", adForm);
	return model;
    }
    
    @RequestMapping(value = "/submitEditAd", method = RequestMethod.POST)
    public ModelAndView submitEditAd(@Valid AdForm adForm, BindingResult result , Principal principal, @RequestParam(value = "adId", required = true) Long adId) {
	
	ModelAndView model = new ModelAndView("editAd");
	if (!result.hasErrors()){
		adService.editAd(adForm, adId);
		
		model.addObject("success", "Ad successfully created.");
	}else{
		model.addObject("error", "Please fill out all valid information.");
	}
	model.addObject("loggedInUser", loginService.getLoggedInUser());
	
	updateService.updateNumberOfUnreadItems(model);
	
	return model;
    }
}