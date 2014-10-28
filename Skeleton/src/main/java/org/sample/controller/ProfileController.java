package org.sample.controller;

import org.sample.controller.pojos.ForgotPasswordForm;
import org.sample.controller.pojos.LoginForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.LoginService;
import org.sample.controller.service.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {

	@RequestMapping("/profile")
	public ModelAndView loadProfilePage()
	{
		ModelAndView model = new ModelAndView("profile");
		
		Session session = new Session();
		model.addObject("session", new Session());
		model.addObject("profileForm", new SignupForm());
		if(session.getUser() == null)
		{
			model = new ModelAndView("login");
			model.addObject("loginForm", new LoginForm());
	    	model.addObject("forgotPasswordForm", new ForgotPasswordForm());
	    	model.addObject("signupForm", new SignupForm());
		}
			
		
		return model;
	}
	
    @Autowired
    LoginService loginService;
    
}
