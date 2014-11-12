package org.sample.controller;

import javax.validation.Valid;

import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.LoginService;
import org.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProfileController { 
	
    @Autowired
    LoginService loginService;
	
	@RequestMapping("/profile")
	public ModelAndView loadProfilePage()
	{
		ModelAndView model = new ModelAndView("profile");
		model.addObject("profileForm", new SignupForm());
		
		User loggedInUser = loginService.getLoggedInUser();
		loggedInUser.setAddress(loginService.getAddress(loggedInUser.getId()));
		
		model.addObject("loggedInUser", loggedInUser);
		return model;
	}

    @RequestMapping(value = "/profileChange", method = RequestMethod.POST)
    public ModelAndView profileChange(@Valid SignupForm signupForm, BindingResult result, RedirectAttributes redirectAttributes) {
    	ModelAndView model;
    	loginService.saveFrom(signupForm);
    	model = new ModelAndView("profile");
    	model.addObject("loggedInUser", loginService.getLoggedInUser());
    	return model;
    }
}
