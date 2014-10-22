package org.sample.controller;

import javax.validation.Valid;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.AdForm;
import org.sample.controller.pojos.LoginForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.SampleService;
import org.sample.model.Address;
import org.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

    @Autowired
    SampleService sampleService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
    	ModelAndView model = new ModelAndView("index");
    	model.addObject("signupForm", new SignupForm());
    	model.addObject("loginForm", new LoginForm());
        return model;
    }

    @RequestMapping(value = "/createAd", method = RequestMethod.POST)
    public ModelAndView createAd(@Valid AdForm adForm, BindingResult result, RedirectAttributes redirectAttributes){
    	ModelAndView model;
    	if (!result.hasErrors()){
    			sampleService.saveFrom(adForm);
        		model = new ModelAndView("adCreated");	
    	}else{
    		model = new ModelAndView("createAd");
    	}
    	return model;
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@Valid SignupForm signupForm, BindingResult result, RedirectAttributes redirectAttributes) {
    	ModelAndView model;    	
    	if (signupIsOkay(result, signupForm)) {
            try {
            	//TODO: for password mismatch: can we put other, valid info as default when returning to register page, 
            	//so user doesn't have to start all over?
            	
            	sampleService.saveFrom(signupForm);
            	
            	LoginForm loginForm = registerToLogin(signupForm);
            	User user = sampleService.getUser(loginForm);
        		Address add = sampleService.getAddress(user.getId());
            	model = new ModelAndView("profile");
            	model.addObject("user", user);
        		model.addObject("address", add);
            } catch (InvalidUserException e) {
            	model = new ModelAndView("index");
            	model.addObject("page_error", e.getMessage());
            }
        } else {
        	model = new ModelAndView("index");
        	model.addObject("signupForm", new SignupForm());
        	model.addObject("loginForm", new LoginForm());
        }   	
    	return model;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@Valid LoginForm loginForm, BindingResult result, RedirectAttributes redirectAttributes) {
    	ModelAndView model;
    	try
    	{
    		User user = sampleService.getUser(loginForm);
    		Address add = sampleService.getAddress(user.getId());
    		
    		model = new ModelAndView("profile");   		
    		model.addObject("user", user);
    		model.addObject("address", add);
    	}
    	catch(InvalidUserException ex)
    	{
    		model = new ModelAndView("index");
        	model.addObject("signupForm", new SignupForm());
        	model.addObject("loginForm", new LoginForm());
    	}
    	return model;
    }
    
    private LoginForm registerToLogin(SignupForm signupForm)
    {
    	LoginForm login = new LoginForm();
    	login.setEmail(signupForm.getEmail());
    	login.setPassword(signupForm.getPassword());
    	return login;
    }
    
    
    @RequestMapping(value = "/security-error", method = RequestMethod.GET)
    public String securityError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("page_error", "You do have have permission to do that!");
        return "redirect:/";
    }
    
    private boolean signupIsOkay(BindingResult result, SignupForm signupForm)
    {
    	boolean okay = !result.hasErrors() && signupForm.getPassword().equals(signupForm.getPasswordConfirm())
    				&& !sampleService.emailAlreadyExists(signupForm.getEmail()) && !signupForm.hasNull();
    	System.out.println("okay: " + okay);
    	return okay;
    }

}


