package org.sample.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.sample.controller.exceptions.InvalidAdException;
import org.sample.controller.pojos.EnquiryForm;
import org.sample.controller.pojos.EnquiryRatingForm;
import org.sample.controller.pojos.LoginForm;
import org.sample.controller.service.EnquiryService;
import org.sample.controller.service.LoginService;
import org.sample.model.Ad;
import org.sample.model.Enquiry;
import org.sample.model.dao.AdDao;
import org.sample.model.dao.EnquiryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    			                  + "Cheers, ";
	
    	/**
    	 * Builds an enquiry creation form, providing a summary of the ad to send an enquiry for,
    	 * as well as an input text box displaying a standard message.
    	 * 
    	 * @param id	the id of the ad to send an enquiry for
    	 * @return		enquiry creation for or 404 model, for bad id parameter
    	 * 				(non-existing, non-numerical)
    	 */
	   @RequestMapping("/sendEnquiry")
	   public ModelAndView createEnquiry(@RequestParam String id)
	   {
		   ModelAndView model = new ModelAndView("enquiryMask");
		   
		   if(loginService.getLoggedInUser() != null)
		   {
			   model.addObject("loggedInUser", loginService.getLoggedInUser());

			   try{
				   Ad ad = adRepository.findOne(Long.parseLong(id));
					   
				   if(ad == null)
					   model = new ModelAndView("404");
				   else 
				   {
					   EnquiryForm enquiryForm = new EnquiryForm();
					   enquiryForm.setAdId(ad.getId());
					   enquiryForm.setMessageText(defaultMsg + loginService.getLoggedInUser().getFirstName());
						   
					   enquiryForm.setReceiverId(ad.getPlacerId());
					   enquiryForm.setAd(ad);		
						   
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
	   
	   
	   /**
	    * Manages processing of submitted enquiries and returns enquiries page model, in case of success.
	    * 
	    * @param enquiryForm			the filled in form to be processed
	    * @param result					the binding result
	    * @param redirectAttributes		the redirected attributes
	    * @return						enquiries model or 404 model, in case of errors in binding results
	    */
	   @RequestMapping(value = "/submitEnquiry", method = RequestMethod.POST) 
	   public ModelAndView submitEnquiry(@Valid EnquiryForm enquiryForm, BindingResult result, RedirectAttributes redirectAttributes)
	   {
		   ModelAndView model = new ModelAndView("enquiries");
		   model.addObject("loggedInUser", loginService.getLoggedInUser());
		   
		   try
		   {
			   if(!result.hasErrors())
			   {
				   enquiryService.submit(enquiryForm);
				   Iterable<Enquiry> results = enquiryService.findSentEnquiries();
				   
				   model.addObject("sentEnquiries", results);
			   }   
			   else
			   {
				   model = new ModelAndView("404");
			   }	  
		   }
		   catch(InvalidAdException ex)
		   {
			   model = new ModelAndView("404");
		   }

		   return model;
	   }
	   
	
	   /**
	    * Returns a model presenting sent and received enquiries.
	    * 
	    * @return	model presenting sent and received enquiries
	    */
	   @RequestMapping("/enquiries")
	   public ModelAndView showEnquiries()
	   {
		   ModelAndView model = new ModelAndView("enquiries");
		   model.addObject("loggedInUser", loginService.getLoggedInUser());
		  
		   enquiryViewAddModelAttributes(model);
	
		   return model;
	   }
	   
	   
	   @RequestMapping(value="/rateEnquiry")
	   public ModelAndView rateEnquiry(@RequestParam long id)
	   {
		   //TODO: security
		   
		   ModelAndView model = new ModelAndView("rateEnquiry");
		   model.addObject("loggedInUser", loginService.getLoggedInUser());
		   
		   Enquiry enquiry = enquiryRepository.findOne(id);
		   enquiry.setAd(adRepository.findOne(enquiry.getAdId()));
		   
		   List<Integer> numberList = new ArrayList<Integer>();
		   for(int i=1; i<=10; i++)
			   numberList.add(i);
		   
		   model.addObject("numberList", numberList);
		   
		   model.addObject("enquiry", enquiry);
		   EnquiryRatingForm enquiryRatingForm = new EnquiryRatingForm();
		   enquiryRatingForm.setEnquiryId(enquiry.getEnquiryId());
		   model.addObject("ratingForm", enquiryRatingForm);
		   
		   return model;
	   }
	   
	   @RequestMapping(value="/submitRating", method = RequestMethod.POST)
	   public ModelAndView submitRating(@Valid EnquiryRatingForm form, BindingResult result, RedirectAttributes redirectAttributes)
	   {   
		   ModelAndView model = new ModelAndView("enquiries");
		   model.addObject("loggedInUser", loginService.getLoggedInUser());
		   
		   if(form!=null || !result.hasErrors())
		   {
			   if(form==null)
				   System.out.println("form null!");
			   enquiryService.submitRating(form);

			   enquiryViewAddModelAttributes(model);
		   }
		   else
		   {
			   model = new ModelAndView("404");
			   model.addObject("loggedInUser", loginService.getLoggedInUser());
		   }

		   return model;
	   }
	   
	   
	   private void enquiryViewAddModelAttributes(ModelAndView model)
	   {
		   Iterable<Enquiry> newReceivedEnquiries = enquiryService.findNewReceivedEnquiries();
		   Iterable<Enquiry> ratedReceivedEnquiries = enquiryService.findRatedReceivedEnquiries();
		   Iterable<Enquiry> sentEnquiries = enquiryService.findSentEnquiries();
		   
		   addEmptyMessages(model, newReceivedEnquiries, ratedReceivedEnquiries, sentEnquiries);
				
		   model.addObject("loggedInUser", loginService.getLoggedInUser());
		   model.addObject("newReceivedEnquiries", newReceivedEnquiries);
		   model.addObject("ratedReceivedEnquiries", ratedReceivedEnquiries);
		   model.addObject("sentEnquiries", sentEnquiries);
	   }
	   
	   
	   private void addEmptyMessages(ModelAndView model, Iterable<Enquiry> newReceivedEnquiries, Iterable<Enquiry> ratedReceivedEnquiries, Iterable<Enquiry> sentEnquiries)
	   {
		   if(!newReceivedEnquiries.iterator().hasNext())
			   model.addObject("newEmpty", "No new enquiries");
		   
		   if(!ratedReceivedEnquiries.iterator().hasNext())
			   model.addObject("ratedEmpty", "No rated enquiries");
		   
		   if(!sentEnquiries.iterator().hasNext())
			   model.addObject("sentEmpty", "No sent enquiries");
	   }
}
