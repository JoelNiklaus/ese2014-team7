package org.sample.controller.service;

import java.sql.Timestamp;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.ForgotPasswordForm;
import org.sample.controller.pojos.LoginForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.pojos.AdForm;
import org.sample.model.Address;
import org.sample.model.User;
import org.sample.model.Ad;
import org.sample.model.dao.AddressDao;
import org.sample.model.dao.UserDao;
import org.sample.model.dao.AdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
public class LoginServiceImpl implements LoginService {

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

	@Autowired AdDao adDao;
    
    @Transactional
	public AdForm saveFrom(AdForm adForm) {
	    
    	Ad ad = new Ad();
    	
	    ad.setAd_Id(adForm.getAd_id());
	    Timestamp timestamp  = new Timestamp(System.currentTimeMillis());
		ad.setTimestamp(timestamp);
	    ad.setTitle(adForm.getTitle());
	    ad.setStreet(adForm.getStreet());
	    ad.setHouseNr(adForm.getHouseNr());
	    ad.setCity(adForm.getCity());
	    ad.setZip(adForm.getZip());
	    ad.setRent(adForm.getRent());
	    ad.setAddCost(adForm.getAddCost());
	    ad.setDateIn(adForm.getDateIn());
	    ad.setDateOut(adForm.getDateOut());
	    ad.setType(adForm.getType());
	    ad.setRoomSize(adForm.getRoomSize());
	    ad.setDescription(adForm.getDescription());
	    ad.setUs(adForm.getUs());
	    ad.setYou(adForm.getYou());
	    
	    ad = adDao.save(ad);
		
	    return  adForm;
	}
}