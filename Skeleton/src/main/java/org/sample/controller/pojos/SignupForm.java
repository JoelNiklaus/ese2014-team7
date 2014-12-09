package org.sample.controller.pojos;

import javax.persistence.Lob;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
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
    @Email(message="Please enter a valid E-Mail address")
    @NotEmpty(message="Please enter your E-Mail address")
    private String email;
    
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-][a-z0-9!#$%&'*+/=?^_`{|}~-][a-z0-9!#$%&'*+/=?^_`{|}~-][a-z0-9!#$%&'*+/=?^_`{|}~-]*", 
    	    message = "Password must be at least 3 characters long")
    private String password;
    
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-][a-z0-9!#$%&'*+/=?^_`{|}~-][a-z0-9!#$%&'*+/=?^_`{|}~-][a-z0-9!#$%&'*+/=?^_`{|}~-]*", 
    	    message = "Password must be at least 3 characters long")
    private String passwordConfirm;

    @Pattern(regexp = "[a-zA-Z\\s]+", 
    	    message = "Enter your street")
	private String street;
    
	@Lob
	@NotNull
	@Pattern(regexp = "[a-zA-ZäöüÄÖÜ.,;:0-9()\\s]+", message = "Please write something about you")
    private String description;
    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private int houseNr;
    
    @Pattern(regexp = "[a-zA-Z\\s]+", 
    	    message = "Enter your city")
    private String city;
    
    @Min(1000)
    @Max(99999)
    private int zip;

    private String imageId;
    
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

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
}
