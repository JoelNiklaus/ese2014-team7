package org.sample.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.sample.controller.pojos.VisitAppointmentForm;
import org.sample.controller.service.EnquiryService;
import org.sample.controller.service.LoginService;
import org.sample.controller.service.VisitAppointmentService;
import org.sample.model.Enquiry;
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
	
	@RequestMapping(value="/createVisitAppointment",method=RequestMethod.GET)
	public ModelAndView test(HttpServletResponse response, @RequestParam("enquiryId") String enquiryId){
		ModelAndView model = new ModelAndView("createVisitAppointment");
		VisitAppointmentForm form = new VisitAppointmentForm();
		
		try{
			form.setEnquiryId(new Long(enquiryId));
			Enquiry enquiry = enquiryService.getEnyuiryById(new Long(enquiryId));
			
			model.addObject("visitAppointmentForm", form);
			model.addObject("enquiry", enquiry);
			model.addObject("loggedInUser", loginService.getLoggedInUser());
			if(enquiry==null)
				model = new ModelAndView("404");
		} catch (Exception e){
			model = new ModelAndView("404");
		}
		
		return model;
	}
	
    @RequestMapping(value="/createVisitAppointment", method=RequestMethod.POST)
    public @ResponseBody ModelAndView createVisitAppointment(@Valid VisitAppointmentForm vaForm,
			BindingResult result, RedirectAttributes redirectAttributes){
    	visitAppointmentService.save(vaForm);
    	return new ModelAndView("redirect:/enquiries");
    }
}
