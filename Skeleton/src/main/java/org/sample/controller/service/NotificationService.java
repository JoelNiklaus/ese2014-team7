package org.sample.controller.service;

import org.sample.model.Ad;
import org.sample.model.Notification;
import org.sample.model.User;


public interface NotificationService {

	public void saveNotificationsToAffectedUsers(Ad ad);
	
	public Iterable<Notification> findNotifications(User user);
	
	public Notification removeNotification(Notification notification);
	
}
