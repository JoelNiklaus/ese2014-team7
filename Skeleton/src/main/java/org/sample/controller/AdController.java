package org.sample.controller;

import java.io.File;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.sample.controller.pojos.AdForm;
import org.sample.controller.service.AdService;
import org.sample.controller.service.LoginService;
import org.sample.controller.service.UpdateService;
import org.sample.model.Ad;
import org.sample.model.Picture;
import org.sample.model.dao.AdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @Autowired SearchController searchController;
    
    
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
    	

    	ModelAndView model = new ModelAndView("createAd");
    	if (!result.hasErrors()){
			adService.saveFrom(adForm);
			model = searchController.index(null);
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
    
    /**
     * 
     * @return myAds model 
     */
    @RequestMapping("myAds")
    public ModelAndView showMyAd() {
    	ModelAndView model = new ModelAndView("myAds");
    

    	Long loggedInUserId = loginService.getLoggedInUser().getId();
        List<Ad> ads = adDao.findByPlacerId(loggedInUserId);

        model.addObject("ads", ads);
    	model.addObject("loggedInUser", loginService.getLoggedInUser());
    	updateService.updateNumberOfUnreadItems(model);
    	
    	return model;
    }
    
    @RequestMapping(value="/deleteAd")
    public @ResponseBody ModelAndView deleteAd(@RequestParam("id") String id) {
    	ModelAndView model = new ModelAndView("myAds");
    	Ad ad = adDao.findOne(new Long(id));
    	model.addObject("loggedInUser", loginService.getLoggedInUser());
    	adService.deleteAd(ad);
        return model;
    }
    

    
    @RequestMapping(value = "/editAd")
    public @ResponseBody ModelAndView editAd(@RequestParam("id") String id) {
    	ModelAndView model = new ModelAndView("editAd");
    	
    	
    	Ad ad = adService.getAd(new Long(id));
    	AdForm adForm = new AdForm(ad);
    	
    	model.addObject("ad", ad);
    	model.addObject("adForm", adForm);
    	model.addObject("loggedInUser", loginService.getLoggedInUser());
    	
    	return model;
    }
    
    @RequestMapping(value = "/editAd", method = RequestMethod.POST)
    public ModelAndView submitEditAd(@Valid AdForm adForm, BindingResult result , Principal principal, @RequestParam(value = "id", required = true) Long adId) {
	
    	ModelAndView model = new ModelAndView("editAd");
    	if (!result.hasErrors()){
    		adService.editAd(adForm, adId);
		
    		model.addObject("success", "Ad successfully created.");
    	}else{
    		model.addObject("error", "Please fill out all valid information.");
    	}
    	Ad ad = adService.getAd(new Long(adId));
    	model.addObject("ad", ad);
    	model.addObject("adForm", adForm);
    	model.addObject("loggedInUser", loginService.getLoggedInUser());
    	updateService.updateNumberOfUnreadItems(model);
	
    	return model;
    }
}