package org.sample.controller.service;

import java.util.Iterator;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.LoginForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.model.Address;
import org.sample.model.User;
import org.sample.model.dao.AddressDao;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
public class SampleServiceImpl implements SampleService {

    @Autowired    UserDao userDao;
    @Autowired    AddressDao addDao;
    
    @Transactional
    public SignupForm saveFrom(SignupForm signupForm) throws InvalidUserException{

        String firstName = signupForm.getFirstName();

        if(!StringUtils.isEmpty(firstName) && "ESE".equalsIgnoreCase(firstName)) {
            throw new InvalidUserException("Sorry, ESE is not a valid name");   // throw exception
        }


        
        
        User user = new User();
        user.setFirstName(signupForm.getFirstName());
        user.setEmail(signupForm.getEmail());
        user.setLastName(signupForm.getLastName());
        user.setPassword(signupForm.getPassword());
        
        user = userDao.save(user);   // save object to DB
        
       // Iterable<Address> addresses = addDao.findAll();  // find all 
       //Address anAddress = addDao.findOne((long)3); // find by ID
        
        Address address = new Address();
        address.setId(user.getId());
        address.setStreet(signupForm.getStreet());
        address.setHouseNr(signupForm.getHouseNr());
        address.setCity(signupForm.getCity());
        address.setZip(signupForm.getZip());
        address = addDao.save(address);
     
        
        signupForm.setId(user.getId());

        return signupForm;

    }
    
    @Transactional
    public User getUser(LoginForm form)
    {
    	Iterable<User> users = userDao.findAll();
    	User user = filterResults(users, form.getEmail(), form.getPassword());
    	
    	if(user == null)
    		throw new InvalidUserException("E-Mail or password incorrect");
    	
    	return user;
    }
    
    @Transactional 
    public Address getAddress(long userID)
    {
    	Address add = new Address();
    	add = addDao.findOne(userID);
    	return add;
    }
    
    private User filterResults(Iterable<User> users, String email, String password)
    {
    	User user = null;
    	
    	for(User u: users)
    	{
    		if((u.getEmail().equals(email)) && (u.getPassword().equals(password)))
    			user = u;
    	}
    	return user;
    }
}
