package org.sample.controller;


import javax.validation.Valid;

import org.sample.controller.pojos.AdForm;
import org.sample.controller.pojos.ForgotPasswordForm;
import org.sample.controller.pojos.LoginForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.AdService;
import org.sample.controller.service.LoginService;
import org.sample.controller.service.Session;
import org.sample.model.Ad;
import org.sample.model.User;
import org.sample.model.dao.AdDao;
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

	   @RequestMapping("/enquiries")
	   public ModelAndView showBookmarks()
	   {
		   ModelAndView model = new ModelAndView("enquiries");
		   
		   //TODO: Session is going to be obsolete!
			Session session = new Session();
			if(session.getUser() == null)
			{
				model = new ModelAndView("login");
				model.addObject("loginForm", new LoginForm());
		    	model.addObject("forgotPasswordForm", new ForgotPasswordForm());
		    	model.addObject("signupForm", new SignupForm());
			}
		   
		   return model;
	   }
}