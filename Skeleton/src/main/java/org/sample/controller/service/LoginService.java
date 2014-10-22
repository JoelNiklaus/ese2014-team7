package org.sample.controller.service;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.AdForm;
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
	 * Saves Data from AdForm to DB.
	 * 
	 * @param 	adForm									the filled out form from the ad creation
	 * @return 	the adForm after DB transaction
	 */
    public AdForm saveFrom(AdForm adForm);
    
    /**
     * Fetches user from DB by information entered into login form.
     * 
     * @param 	loginForm					the filled out form containing the
     * 										login information
     * @return	the user matching the login information (i.e. email/password combination)
     * @throws 	InvalidUserException		if requested email/password combination doesn't
     * 										exist.
     */
    public User getUser(LoginForm loginForm) throws InvalidUserException;
    
    /**
     * Gets address corresponding to given userID
     * 
     * @param 	userID	ID of the user to search address for
     * @return	user's address
     */
    public Address getAddress(long userID);
    
    /**
     * Checks if email already exists in DB.
     * 
     * @param 	email email-address to check
     * @return	true if address already exists in DB, false else
     */
    public boolean emailAlreadyExists(String email);

}
