package org.sample.controller;



import javax.validation.Valid;

import org.sample.controller.pojos.AdForm;
import org.sample.controller.pojos.EnquiryForm;
import org.sample.controller.pojos.ForgotPasswordForm;
import org.sample.controller.pojos.LoginForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.AdService;
import org.sample.controller.service.EnquiryService;
import org.sample.controller.service.LoginService;
import org.sample.controller.service.Session;
import org.sample.model.Ad;
import org.sample.model.Enquiry;
import org.sample.model.User;
import org.sample.model.dao.AdDao;
import org.sample.model.dao.EnquiryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EnquiryController {

    	@Autowired
    	AdDao adRepository;
    	
    	
    	@Autowired
    	EnquiryDao enquiryRepository;
    	
    	@Autowired
        EnquiryService enquiryService;
    	
    	@Autowired
    	LoginService loginService;
    	
    	private String defaultMsg = "Hi guys, \n\n"
    			                  + "I'm interested in your room. Let's meet and see if I'm going to be your new roomie.\n\n"
    			                  + "Cheers!";
	
	   @RequestMapping("/sendEnquiry")
	   public ModelAndView createEnquiry(@RequestParam String id)
	   {
		   ModelAndView model = new ModelAndView("enquiryMask");
		   long adId = 0L;
		   
		   
		   if(loginService.getLoggedInUser() != null){
			   try{
				   adId = Long.parseLong(id);
				   Ad ad = adRepository.findOne(adId);
				   
				   if(ad == null)
					   model = new ModelAndView("404");
				   else 
				   {
					   EnquiryForm enquiryForm = new EnquiryForm();
					   enquiryForm.setMessageText(defaultMsg);
					   
					   long placerID = ad.getPlacerId();
					   enquiryForm.setReceiverId(placerID);
					   
					   model.addObject("ad", ad);
					   model.addObject("enquiryForm", enquiryForm);
				   }
					  
			   }
			   catch(NumberFormatException ex){
				   model = new ModelAndView("404");
			   } 
		   }
		   else
		   {
			   model = new ModelAndView("login");
			   model.addObject("loginForm", new LoginForm());
		   }
		
		   return model;
	   }
	   
	   
	   @RequestMapping(value = "/submitEnquiry", method = RequestMethod.POST) //TODO: experiment
	   public ModelAndView submitEnquiry(@Valid EnquiryForm enquiryForm, BindingResult result, RedirectAttributes redirectAttributes)
	   {
		   ModelAndView model = new ModelAndView("enquiries");		   
		   
		   if(result == null)
			   System.out.println("Result is null!");
			   
		   if(!result.hasErrors())
		   {
			   System.out.println("EQ/submit: no errors in binding result");
			   enquiryService.submit(enquiryForm);
			   Iterable<Enquiry> results = enquiryService.findSentEnquiries();
			   
			   model.addObject("sentEnquiries", results);
		   }   
		   else
		   {
			   System.out.println("EQ/submit: ERRORS binding result");
			   model = new ModelAndView("404");
			   System.out.println(result.getFieldError());
		   }
			   
		   model.addObject("loggedInUser", loginService.getLoggedInUser());
		   return model;
	   }
	   
	
	   @RequestMapping("/enquiries")
	   public ModelAndView showEnquiries()
	   {
		   ModelAndView model = new ModelAndView("enquiries");
		   
		   //TODO: Session is going to be obsolete!
			Session session = new Session();
			if(session.getUser() != null)
			{
				//TODO: add received enquiries
				//TODO: add Ad to Enquiry, in order to access data in enquiries.jsp
				Iterable<Enquiry> receivedEnquiries = enquiryService.findReceivedEnquiries();
				Iterable<Enquiry> sentEnquiries = enquiryService.findSentEnquiries();
				model.addObject("receivedEnquiries", receivedEnquiries);
				model.addObject("sentEnquiries", sentEnquiries);
			}
			else
			{
				model = new ModelAndView("login");
				model.addObject("loginForm", new LoginForm());
		    	model.addObject("forgotPasswordForm", new ForgotPasswordForm());
		    	model.addObject("signupForm", new SignupForm());
			}
	    	try{
	    		model.addObject("loggedInUser", loginService.getLoggedInUser());
	    	} catch (Exception e){
	    	}
		   return model;
	   }
}
