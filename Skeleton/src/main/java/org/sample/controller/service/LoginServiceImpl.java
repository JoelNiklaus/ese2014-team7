package org.sample.controller.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.ForgotPasswordForm;
import org.sample.controller.pojos.LoginForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.model.Address;
import org.sample.model.Picture;
import org.sample.model.User;
import org.sample.model.dao.AddressDao;
import org.sample.model.dao.PictureDao;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
public class LoginServiceImpl implements LoginService, UserDetailsService {

    @Autowired UserDao userDao;
    @Autowired AddressDao addressDao;
    @Autowired PictureDao pictureDao;
    
    @Transactional
    public SignupForm saveFrom(SignupForm signupForm) throws InvalidUserException {

        Picture picture = null;
        
        try {
            picture = pictureDao.findOne(new Long(signupForm.getImageId()));
        } catch(Exception e) {
        	
        }
    	
        String firstName = signupForm.getFirstName();

        if(!StringUtils.isEmpty(firstName) && "ESE".equalsIgnoreCase(firstName)) {
            throw new InvalidUserException("Sorry, ESE is not a valid name");   // throw exception
        }
        
        User user = new User();
        user.setFirstName(signupForm.getFirstName());
        user.setEmail(signupForm.getEmail());
        user.setLastName(signupForm.getLastName());
        user.setDescription(signupForm.getDescription());
        user.setProfileImage(picture);
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(signupForm.getPassword());
        user.setPassword(hashedPassword);
        
        Address address = new Address();
        address.setStreet(signupForm.getStreet());
        address.setHouseNr(signupForm.getHouseNr());
        address.setCity(signupForm.getCity());
        address.setZip(signupForm.getZip());
        address = addressDao.save(address);
        
        user.setAddress(address);
        user = userDao.save(user);   // save object to DB
        
        signupForm.setId(user.getId());

        return signupForm;
    }
    
    @Transactional
    public SignupForm updateProfile(SignupForm profileForm) throws InvalidUserException {

        String firstName = profileForm.getFirstName();
        
        Picture picture = null;
        
        try {
            picture = pictureDao.findOne(new Long(profileForm.getImageId()));
        } catch(Exception e) {
        	
        }
        
        if(!StringUtils.isEmpty(firstName) && "ESE".equalsIgnoreCase(firstName)) {
            throw new InvalidUserException("Sorry, ESE is not a valid name");   // throw exception
        }
        
        User user = userDao.findByEmail(profileForm.getEmail());
        user.setFirstName(profileForm.getFirstName());
        user.setEmail(profileForm.getEmail());
        user.setLastName(profileForm.getLastName());
        user.setDescription(profileForm.getDescription());
        user.setProfileImage(picture);
        System.err.println(user.getEmail()+ "  " + user.getId());
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(profileForm.getPassword());
        user.setPassword(hashedPassword);
        user = userDao.save(user);   // save object to DB
        
        Address address = user.getAddress();
        address.setStreet(profileForm.getStreet());
        address.setHouseNr(profileForm.getHouseNr());
        address.setCity(profileForm.getCity());
        address.setZip(profileForm.getZip());
        address = addressDao.save(address);
        
        return profileForm;
    }
    
    @Transactional
    public User getUser(ForgotPasswordForm forgotPasswordForm) {
    	Iterable<User> users = userDao.findAll();
    	User user = null;
    	String email = forgotPasswordForm.getEmail();
    	
    	for(User u: users) {
    		if((u.getEmail().equals(email)))
    			user = u;
    	}
    	
    	if(user == null)
    		throw new InvalidUserException("No User with this E-Mail exists.");
    	
    	return user;
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
    
    public boolean emailAlreadyExists(String email)
    {
    	boolean exists = false;
    	
    	Iterable<User> users = userDao.findAll();
    	
    	SEARCH_MATCH:
    	for(User u: users)
    	{
    		if((u.getEmail().equals(email)))
    		{
    			exists = true;
    			break SEARCH_MATCH;
    		}
    			
    	}	
    	
    	return exists;
    }
    
    public User getUser(Long id){
    	User user = userDao.findOne(id);
    	if(user == null)
    		throw new InvalidUserException("No User with this id existing.");
    	return user;
    }

	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		User user = userDao.findByEmail(arg0);
		
		return user;
	}

	public User getLoggedInUser() {
		return userDao.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
	}
}
