package org.sample.controller.pojos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class SignupForm {


    private Long id;
    private String firstName;
    private String lastName;
    
    //TODO: maybe add a password table?


    @NotNull
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", 
    message = "Must be valid email address")
    private String email;
    
    private String password;
    private String passwordConfirm;


	private String street;
    private int houseNr;
    private String city;
    private int zip;

    public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
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
	public boolean hasNull()
	{
		boolean hasNull = (email == "") || (firstName == "") || (lastName == "") || (password == "") 
					   || (street == "")  || (houseNr == 0)  || (city == "")  || (zip == 0);
		System.out.println("has null: " + hasNull);
		System.out.println("city: " + city);
		return hasNull;
	}
}
