package org.sample.controller.pojos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.sample.controller.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class SignupForm {

	@Autowired
	LoginService loginService;

    private Long id;
    
    @Pattern(regexp = "[a-zA-Z\\s]+", 
    	    message = "Enter your first name")
    private String firstName;
    
    @Pattern(regexp = "[a-zA-Z\\s]+", 
    	    message = "Enter your last name")
    private String lastName;

    @NotNull(message="E-Mail already exists")
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", 
    message = "Must be valid email address")
    private String email;
    
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-][a-z0-9!#$%&'*+/=?^_`{|}~-][a-z0-9!#$%&'*+/=?^_`{|}~-][a-z0-9!#$%&'*+/=?^_`{|}~-]*", 
    	    message = "Password must be at leat 3 characters long")
    private String password;
    
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-][a-z0-9!#$%&'*+/=?^_`{|}~-][a-z0-9!#$%&'*+/=?^_`{|}~-][a-z0-9!#$%&'*+/=?^_`{|}~-]*", 
    	    message = "Password must be at leat 3 characters long")
    private String passwordConfirm;

    @Pattern(regexp = "[a-zA-Z\\s]+", 
    	    message = "Enter your street")
	private String street;
    
    private int houseNr;
    
    @Pattern(regexp = "[a-zA-Z]+", 
    	    message = "Enter your city")
    private String city;
    
    private int zip;

    public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		street = street.trim();
		this.street = street; //TODO: test this
	}

	public int getHouseNr() {
		return houseNr;
	}

	public void setHouseNr(int houseNr) {
		this.houseNr = houseNr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		city = city.trim();
		this.city = city;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
    	firstName = firstName.trim();
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
    	lastName = lastName.trim();
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
    	this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setPassword(String password)
    {
    	this.password = password;
    }
    
    public String getPassword()
    {
    	return password;
    }
    
    public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	/**
	 * Checks if one of the mandatory properties (email, password...) is null
	 * 
	 * @return true if at least one mandatory property is null, false else
	 */
	public boolean hasNull() {
		boolean hasNull = (email.equals("")) || (firstName.equals("")) || (lastName.equals("")) || (password.equals("")) 
					   || (street.equals(""))  || (houseNr == 0)  || (city.equals(""))  || (zip == 0);
		return hasNull;
	}
}
