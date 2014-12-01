package org.sample.controller.service;

import org.sample.model.Ad;
import org.sample.model.Notification;
import org.sample.model.User;


public interface NotificationService {

	/**
	 * Saves the notifications to all users who have saved a search matching the newly created ad.
	 * 
	 * @param ad
	 */
	public void sendNotificationsForMatchingSearches(Ad ad);
	
	/**
	 * Compiles a list of all (already read) Notifications relevant to given user.
	 * 
	 * @param user
	 * @return Iterable<Notifications>
	 */
	public Iterable<Notification> findNotifications(User user);
	
	/**
	 * Removes a notification
	 * 
	 * @param notification
	 * @return
	 */
	public Notification removeNotification(Notification notification);

	/**
	 * Sets all Notifications of a user to read state.
	 * @param user
	 */
	public void markAllNotificationsAsRead(User user);

	/**
	 * Compiles a list of all unread notifications and sets the items in that list to read.
	 * 
	 * @return
	 */
	public Iterable<Notification> findUnreadNotifications();
	
}
