package org.sample.controller;

import javax.validation.Valid;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.ForgotPasswordForm;
import org.sample.controller.pojos.LoginForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.LoginService;
import org.sample.controller.service.Session;
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
public class AuthentificationController {

    @Autowired
    LoginService sampleService;
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@Valid SignupForm signupForm, BindingResult result, RedirectAttributes redirectAttributes) {
    	ModelAndView model;    	
    	if (signupIsOkay(result, signupForm)) {
            try {
            	//TODO: for password mismatch: can we put other, valid info as default when returning to register page, 
            	//so user doesn't have to start all over?
            	
            	sampleService.saveFrom(signupForm);
            	
            	LoginForm loginForm = registerFormToLoginForm(signupForm);
            	User user = sampleService.getUser(loginForm);
        		Address add = sampleService.getAddress(user.getId());
        		Session session = new Session();
        		session.setUser(user);
        		
            	model = new ModelAndView("profile");
            	model.addObject("session", session);
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
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
    	ModelAndView model = new ModelAndView("login");
    	model.addObject("loginForm", new LoginForm());
    	model.addObject("forgotPasswordForm", new ForgotPasswordForm());
    	model.addObject("signupForm", new SignupForm());
    	return model;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@Valid LoginForm loginForm, BindingResult result, RedirectAttributes redirectAttributes) {
    	ModelAndView model;
    	try {
    		User user = sampleService.getUser(loginForm);
    		Address add = sampleService.getAddress(user.getId());
    		Session session = new Session();
    		session.setUser(user);
    		
    		model = new ModelAndView("profile");   	
    		model.addObject("session", session);
    		model.addObject("address", add);
    	} catch(InvalidUserException ex) {
    		model = new ModelAndView("index");
        	model.addObject("signupForm", new SignupForm());
        	model.addObject("loginForm", new LoginForm());
    	}
    	return model;
    }
    
    private LoginForm registerFormToLoginForm(SignupForm signupForm) {
    	LoginForm login = new LoginForm();
    	login.setEmail(signupForm.getEmail());
    	login.setPassword(signupForm.getPassword());
    	return login;
    }
    
    //TODO Bug: Displays not existent form Forgot Password...
    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public ModelAndView forgot(@Valid ForgotPasswordForm forgotPasswordForm) {
    	ModelAndView model = new ModelAndView("forgot");
    	try {
    		User user = sampleService.getUser(forgotPasswordForm);
    		
    		String email = user.getEmail();
    		String password = user.getPassword();
    		String firstName = user.getFirstName();
    		String lastName = user.getLastName();
    		String subject = "Sending Password";
    		String message = "Dear " + firstName + " " + lastName + "\n\n"
    				+ "You requested your password: " + password + "\n\nYours sincerely,\nteam7";
    		    		
    		try {
    			sendMail(email, subject, message);
    			
    			model.addObject("success", "Password successfully delivered");
    		} catch (EmailException e) {
    			model.addObject("error", "Password could not be sent: " + e.getMessage());
    			e.printStackTrace();
    		}
    		
    	} catch(InvalidUserException e) {
    		model.addObject("error", "No User with this E-Mail found: " + e.getMessage());
    	}
    	    	
    	return model;
    }
    
    private void sendMail(String email, String subject, String message) throws EmailException {
    	Email simpleEmail = new SimpleEmail();
    	simpleEmail.setHostName("mailtrap.io");
    	simpleEmail.setSmtpPort(465);
    	simpleEmail.setAuthenticator(new DefaultAuthenticator("25490b88e52ba93b3", "5f10b93f61b80f"));
    	simpleEmail.setSSLOnConnect(false);
    	simpleEmail.setFrom("demo@mailtrap.io");
    	simpleEmail.setSubject(subject);
    	simpleEmail.setMsg(message);
    	simpleEmail.addTo(email);
    	simpleEmail.send();
    }
        
    private boolean signupIsOkay(BindingResult result, SignupForm signupForm)
    {
    	boolean okay = !result.hasErrors() && signupForm.getPassword().equals(signupForm.getPasswordConfirm())
    				&& !sampleService.emailAlreadyExists(signupForm.getEmail()) && !signupForm.hasNull();
    	System.out.println("okay: " + okay);
    	return okay;
    }

}
