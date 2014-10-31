package org.sample.controller;

import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {
	 
	
    @Autowired
    LoginService loginService;
	 


	@RequestMapping("/profile")
	public ModelAndView loadProfilePage()
	{
		ModelAndView model = new ModelAndView("profile");
		model.addObject("profileForm", new SignupForm());
		model.addObject("loggedInUser", loginService.getLoggedInUser());
		return model;
	}

    
}
