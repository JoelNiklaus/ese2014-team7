package org.sample.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.sample.controller.pojos.VisitAppointmentForm;
import org.sample.controller.service.LoginService;
import org.sample.controller.service.VisitAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class VisitAppointmentController {
	
    @Autowired LoginService loginService;
    @Autowired private VisitAppointmentService visitAppointmentService;
	
	@RequestMapping(value="/createVisitAppointment",method=RequestMethod.GET)
	public ModelAndView test(HttpServletResponse response){
		ModelAndView model = new ModelAndView("createVisitAppointment");
		model.addObject("visitAppointmentForm", new VisitAppointmentForm());
		model.addObject("loggedInUser", loginService.getLoggedInUser());

		return model;
	}
	
    @RequestMapping(value="/createVisitAppointment", method=RequestMethod.POST)
    public @ResponseBody String createVisitAppointment(@Valid VisitAppointmentForm vaForm,
			BindingResult result, RedirectAttributes redirectAttributes){
    	visitAppointmentService.save(vaForm);
    	return "";
    }
}
