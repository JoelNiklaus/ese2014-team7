package org.sample.controller;

import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.sample.controller.enums.VisitAppointmentState;
import org.sample.controller.pojos.EnquiryRatingForm;
import org.sample.controller.pojos.VisitAppointmentForm;
import org.sample.controller.service.EnquiryService;
import org.sample.controller.service.LoginService;
import org.sample.controller.service.VisitAppointmentService;
import org.sample.model.Enquiry;
import org.sample.model.User;
import org.sample.model.VisitAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class VisitAppointmentController {
	
    @Autowired LoginService loginService;
    @Autowired EnquiryService enquiryService;
    @Autowired VisitAppointmentService visitAppointmentService;
	
    /**
     * The page for the adcrator to manage an enquiry
     * 
     * @param response
     * @param enquiryId
     * @return createVisitAppointment or 404 when the enquiry was not found
     */
	@RequestMapping(value="/createVisitAppointment",method=RequestMethod.GET)
	public ModelAndView createVisitAppointment(HttpServletResponse response, @RequestParam("enquiryId") String enquiryId){
		ModelAndView model = new ModelAndView("createVisitAppointment");
		VisitAppointmentForm form = new VisitAppointmentForm();
		
		try{
			form.setEnquiryId(new Long(enquiryId));
			Enquiry enquiry = enquiryService.getEnquiryById(new Long(enquiryId));
			if(enquiry==null)
				model = new ModelAndView("404");
			
			User sender = loginService.getUser(enquiry.getSenderId());
			
			EnquiryRatingForm ratingForm = new EnquiryRatingForm();
			ratingForm.setEnquiryId(new Long(enquiryId));
			ratingForm.setRating(enquiry.getRating());
			ratingForm.setEnquiryRatingComment(enquiry.getEnquiryRatingComment());
			
			model.addObject("sender", sender);
			model.addObject("ratingForm", ratingForm);
			model.addObject("visitAppointmentForm", form);
			model.addObject("enquiry", enquiry);
			model.addObject("loggedInUser", loginService.getLoggedInUser());

		} catch (Exception e){
			model = new ModelAndView("404");
		}
		
		return model;
	}
	
	/**
	 * The page for the ad prospect to manage the enquiry
	 * 
	 * @param response
	 * @param enquiryId
	 * @return enquiry invitation page or 404 when the enquiry does not exist
	 */
	@RequestMapping(value="/manageInvitationRequests",method=RequestMethod.GET)
	public ModelAndView manageInvitationRequests(HttpServletResponse response, @RequestParam("enquiryId") String enquiryId){
		ModelAndView model = new ModelAndView("manageInvitationRequests");
		
		try{
			Enquiry enquiry = enquiryService.getEnquiryById(new Long(enquiryId));
			
			User receiver = loginService.getUser(enquiry.getReceiverId());
			
			model.addObject("receiver", receiver);
			model.addObject("enquiry", enquiry);
			model.addObject("loggedInUser", loginService.getLoggedInUser());
			if(enquiry==null)
				model = new ModelAndView("404");
		} catch (Exception e){
			model = new ModelAndView("404");
		}
		
		return model;
	}
	
	/**
	 * Ads a Visit Appointment to the database
	 * 
	 * Precondition the form is completed
	 * Postcondition the visitAppointment is stored
	 * 
	 * @param vaForm
	 * @param result
	 * @param redirectAttributes
	 * @return redirect back
	 */
    @RequestMapping(value="/createVisitAppointment", method=RequestMethod.POST)
    public @ResponseBody ModelAndView createVisitAppointment(@Valid VisitAppointmentForm vaForm,
			BindingResult result, RedirectAttributes redirectAttributes){
    	visitAppointmentService.save(vaForm);
    	return new ModelAndView("redirect:/createVisitAppointment?enquiryId="+vaForm.getEnquiryId());
    }
    
    /**
     * Removes an Appointment
     * 
     * Precondition the appointment exists in the database
     * Postcondition the appointment is removed from the database
     * 
     * @param id of the visitAppointment
     * @param enquiryId
     * @return redirect back
     */
    @RequestMapping(value="/removeVisitAppointment", method=RequestMethod.GET)
    public @ResponseBody ModelAndView removeVisitAppointment(@RequestParam("id") String id, @RequestParam("enquiryId") String enquiryId){
        	
    	Enquiry enquiry = enquiryService.getEnquiryById(new Long(enquiryId));
    	LinkedList<VisitAppointment> visitAppointments = new LinkedList<VisitAppointment>();    
    	visitAppointments.addAll(enquiry.getVisitAppointments());
    	
    	VisitAppointment tmpVisitApppointment = visitAppointmentService.getVisitAppointment(new Long(id));
    	
    	for (VisitAppointment visitAppointment : visitAppointments) {
    		
			if(tmpVisitApppointment.getId().equals(visitAppointment.getId())){
				visitAppointments.remove(visitAppointment);
			}
		}
    	enquiry.setVisitAppointments(visitAppointments);
    	
    	enquiryService.save(enquiry);
    	
    	return new ModelAndView("redirect:/createVisitAppointment?enquiryId="+enquiryId+"#sentInvitationTab");
    }
    
    /**
     * Sets the state of an Visit Appointment
     * 
     * Precondition the visitAppointment state is NEW
     * Postcondition the visitAppointment state is set to ACCEPTED
     * 
     * @param id of the visitAppointment
     * @param enquiryId
     * @return redirect back
     */
    @RequestMapping(value="/setVisitAppointmentStateAccepted", method=RequestMethod.GET)
    public @ResponseBody ModelAndView setVisitAppointmentStateAccepted(@RequestParam("id") String id, @RequestParam("enquiryId") String enquiryId){
        Enquiry enquiry = enquiryService.getEnquiryById(new Long(enquiryId));
        enquiry.setUnread(true);
        enquiryService.save(enquiry);
    	visitAppointmentService.updateState(new Long(id), VisitAppointmentState.ACCEPTED);
    	
    	return new ModelAndView("redirect:/manageInvitationRequests?enquiryId="+enquiryId);
    }
    
    /**
     * Sets the state of an Visit Appointment
     * 
     * Precondition the visitAppointment state is NEW
     * Postcondition the visitAppointment state is set to REJECTED
     * 
     * @param id of the visitAppointment
     * @param enquiryId
     * @return
     */
    @RequestMapping(value="/setVisitAppointmentStateRejected", method=RequestMethod.GET)
    public @ResponseBody ModelAndView setVisitAppointmentStateRejected(@RequestParam("id") String id, @RequestParam("enquiryId") String enquiryId){
        Enquiry enquiry = enquiryService.getEnquiryById(new Long(enquiryId));
        enquiry.setUnread(true);
        enquiryService.save(enquiry);
    	visitAppointmentService.updateState(new Long(id), VisitAppointmentState.REJECTED);
    	
    	return new ModelAndView("redirect:/manageInvitationRequests?enquiryId="+enquiryId);
    }
}
