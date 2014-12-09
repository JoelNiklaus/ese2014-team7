package org.sample.controller.service;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.ForgotPasswordForm;
import org.sample.controller.pojos.LoginForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.model.Address;
import org.sample.model.User;

public interface LoginService {

	/**
	 * Saves Data from SignupForm to DB.
	 * 
	 * @param 	signupForm								the filled out form from the registration
	 * @return 	the SignupForm after DB transaction		
	 * @throws 	InvalidUserException					if first name is "ESE"
	 */
    public SignupForm saveFrom(SignupForm signupForm) throws InvalidUserException;
    
    /**
     * Updates a users profile based on the signupForm submitted.
     * 
     * @param signupForm
     * @return
     * @throws InvalidUserException
     */
    public SignupForm updateProfile(SignupForm signupForm) throws InvalidUserException;
    
    /**
     * Fetches a user from the DB based on his login credentials.
     * 
     * @param loginForm
     * @return
     * @throws InvalidUserException
     */
    public User getUser(LoginForm loginForm) throws InvalidUserException;
    
    /**
     * Fetches user from DB by email.
     * 
     * @param 	forgotPasswordForm			the filled out form containing the email

     * @return	the user matching the email provided
     * @throws 	InvalidUserException		if no user with this email exists.
     */
    public User getUser(ForgotPasswordForm forgotPasswordForm) throws InvalidUserException;
    
    /**
     * Checks if email already exists in DB.
     * 
     * @param 	email email-address to check
     * @return	true if address already exists in DB, false else
     */
    public boolean emailAlreadyExists(String email);

	/**
	 * Return active user
	 * 
	 * @return User
	 */
    public User getLoggedInUser();
    
	/**
	 * Fetches a User by id.
	 * 
	 * @param id
	 * @return
	 */
	public User getUser(Long id) throws InvalidUserException;
	

}
