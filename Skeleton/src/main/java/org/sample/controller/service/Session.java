package org.sample.controller.service;

import javax.persistence.Entity;

import org.sample.model.User;

@Entity
public class Session {

	private static User user;
	
	public  User getUser()
	{
		return user;
	}
	
	@SuppressWarnings("static-access")
	public  void setUser(User user)
	{
		this.user = user;
	}
	
	public  void resetUser()
	{
		user = null;
	}
	
}
