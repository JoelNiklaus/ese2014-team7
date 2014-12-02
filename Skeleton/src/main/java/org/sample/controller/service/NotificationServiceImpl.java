package org.sample.controller.service;

import java.util.LinkedList;

import org.sample.model.Ad;
import org.sample.model.Notification;
import org.sample.model.Search;
import org.sample.model.User;
import org.sample.model.dao.AdDao;
import org.sample.model.dao.NotificationDao;
import org.sample.model.dao.SearchDao;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired LoginService loginService;
	@Autowired SearchService searchService;
	@Autowired AdDao adDao;
	@Autowired SearchDao searchDao;
	@Autowired UserDao userDao;
	@Autowired NotificationDao notificationDao;

	public void sendNotificationsForMatchingSearches(Ad ad) {
		
		Iterable<Search> allSearches = searchDao.findAll();
		
		for(Search s: allSearches)
			if(match(s, ad))
				sendNotification(s, ad);
		
	}
	
	private boolean match(Search search, Ad ad)
	{
		long price;
		
		if(search == null || ad == null)
			return false;
		
		price = ad.getRent() + ad.getAddCost();
		
		if(ad.getCity().equals(search.getCity())
		   && search.getPriceMin() <= price
		   && price <= search.getPriceMax())
				return true;
		
		return false;
	}

	@Transactional
	private void sendNotification(Search search, Ad ad)
	{
		assert (search != null) && (ad != null);
		
		Notification notification = new Notification();
		notification.setAdId(ad.getId());
		notification.setUserId(search.getUserId());
		notification.setNotificationText("Hey, there's a new ad that might be interesting for you! Click to check it out!");
		notification.setUnread(true);
		notificationDao.save(notification);
	}
	
	@Transactional
	public Iterable<Notification> findNotifications(User user) {
		return fetchRelevantNotifications(false);
	}
	
	public Iterable<Notification> findUnreadNotifications() {
		return fetchRelevantNotifications(true);
	}
	
	
	@Transactional
	private Iterable<Notification> fetchRelevantNotifications(boolean unread)
	{
		Iterable<Notification> allNotifications = notificationDao.findAll();
		LinkedList<Notification> results = new LinkedList<Notification>();

		for(Notification n : allNotifications) {
			if(n.getUserId().equals(loginService.getLoggedInUser().getId()) && (n.isUnread() == unread)) {
				n.setAd(adDao.findOne(n.getAdId()));
				results.add(n);
				
				if(unread)
				{
					System.out.println("Found unread notif");
					n.setUnread(false);
					notificationDao.save(n);
				}
			}
		}

		return (Iterable<Notification>)results;
	}

	
	@Transactional
	public Notification removeNotification(Notification notification) {
		notificationDao.delete(notification);

		return notification;
	}

	public void markAllNotificationsAsRead(User user) {
		Iterable<Notification> allNotifications = findNotifications(user);
		
		for(Notification n : allNotifications)
			n.setUnread(false);
	}

}
