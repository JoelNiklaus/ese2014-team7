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
import org.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthentificationController {

	@Autowired
	LoginService loginService;

	/**
	 * Creates a model for registering a new user.
	 * 
	 * @return register model
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView index() {
		assert loginService.getLoggedInUser() == null;
		
		ModelAndView model = new ModelAndView("register");
		model.addObject("signupForm", new SignupForm());
		model.addObject("loggedInUser", loginService.getLoggedInUser());
		return model;
	}

	/**
	 * Submits registration and returns login model
	 * 
	 * @param signupForm
	 *            completed registration form
	 * @param result
	 * @param redirectAttributes
	 * 
	 * @return login model, registration model if signup form invalid
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(@Valid SignupForm signupForm,
			BindingResult result, RedirectAttributes redirectAttributes) {
		assert loginService.getLoggedInUser() == null;
		assert signupForm != null;
		
		ModelAndView model = new ModelAndView("login");

		try {

			if(loginService.emailAlreadyExists(signupForm.getEmail()))
				throw new InvalidUserException("Email already exists");
			
			if (!result.hasErrors()) {
				try {
					loginService.saveFrom(signupForm);

					model.addObject(new LoginForm());
				} catch (InvalidUserException e) {
					model = new ModelAndView("register");
					model.addObject("page_error", e.getMessage());
				}
			} else {
				model = new ModelAndView("register");
			}
			model.addObject("loggedInUser", loginService.getLoggedInUser());
		} catch (InvalidUserException ex) {
			model = new ModelAndView("register");
			signupForm.setEmail("");
			model.addObject("signupForm", signupForm);
			model.addObject("emailExists", "This email address is already in use");
		}

		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "denied";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "logout";
	}

	/**
	 * Displays an E-Mail input field for the user who forgot his password.
	 */
	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public ModelAndView forgot() {
		assert loginService.getLoggedInUser() == null;
		
		ModelAndView model = new ModelAndView("forgot");
		model.addObject("forgotPasswordForm", new ForgotPasswordForm());
		model.addObject("loggedInUser", loginService.getLoggedInUser());
		return model;
	}

	/**
	 * Sends users password to the entered E-Mail address.
	 * 
	 * @param forgotPasswordForm
	 */
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ModelAndView forgot(@Valid ForgotPasswordForm forgotPasswordForm) {
		assert loginService.getLoggedInUser() == null;
		
		ModelAndView model = new ModelAndView("forgot");
		try {
			User user = loginService.getUser(forgotPasswordForm);

			// compose E-Mail
			String email = user.getEmail();
			String password = user.getPassword();
			String firstName = user.getFirstName();
			String lastName = user.getLastName();
			String subject = "Sending Password";
			String message = "Dear " + firstName + " " + lastName + "\n\n"
					+ "You requested your password: " + password
					+ "\n\nYours sincerely,\nteam7";

			try {
				sendMail(email, subject, message);

				model.addObject("success", "Password successfully delivered");
			} catch (EmailException e) {
				model.addObject("error",
						"Password could not be sent: " + e.getMessage());
				e.printStackTrace();
			}

		} catch (InvalidUserException e) {
			model.addObject("error",
					"No User with this E-Mail found: " + e.getMessage());
		}
		return model;
	}

	private void sendMail(String email, String subject, String message)
			throws EmailException {
		Email simpleEmail = new SimpleEmail();
		simpleEmail.setHostName("mailtrap.io");
		simpleEmail.setSmtpPort(465);
		simpleEmail.setAuthenticator(new DefaultAuthenticator(
				"25490b88e52ba93b3", "5f10b93f61b80f"));
		simpleEmail.setSSLOnConnect(false);
		simpleEmail.setFrom("demo@mailtrap.io");
		simpleEmail.setSubject(subject);
		simpleEmail.setMsg(message);
		simpleEmail.addTo(email);
		simpleEmail.send();
	}

}
