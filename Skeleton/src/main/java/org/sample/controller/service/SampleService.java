package org.sample.controller.service;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.LoginForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.model.Address;
import org.sample.model.User;

public interface SampleService {

    public SignupForm saveFrom(SignupForm signupForm) throws InvalidUserException;
    
    public User getUser(LoginForm loginForm) throws InvalidUserException;
    
    public Address getAddress(long userID);
    
    public boolean emailAlreadyExists(String email);

}
