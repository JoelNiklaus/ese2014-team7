package org.sample.controller.service;

import org.sample.model.Ad;
import org.sample.model.Notification;
import org.sample.model.User;


public interface NotificationService {

	/**
	 * ??
	 * 
	 * @param ad
	 */
	public void saveNotificationsToAffectedUsers(Ad ad);
	
	/**
	 * Compiles a list of all Notifications relevant to given user.
	 * 
	 * @param user
	 * @return Iterable<Notifications>
	 */
	public Iterable<Notification> findNotifications(User user);
	
	/**
	 * Removes notifications
	 * 
	 * @param notification
	 * @return
	 */
	public Notification removeNotification(Notification notification);
	
}
