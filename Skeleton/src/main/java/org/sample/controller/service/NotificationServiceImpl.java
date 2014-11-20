package org.sample.controller.service;

import java.util.LinkedList;

import org.sample.controller.pojos.SearchForm;
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

	@Transactional
	public void saveNotificationsToAffectedUsers(Ad ad) {
		Notification notification = new Notification();

		Iterable<User> allUsers = userDao.findAll();

		if(userDao.count() > 0)

			//TODO optimize this very inefficient way of fetching the data.
			for(User user : allUsers) {
				//TODO Question: What happens if a user has more than one saved searches
				Search search = searchDao.findOne(user.getId());

				if(search != null) {
					SearchForm searchForm = new SearchForm();
					searchForm.setPriceMin("" + search.getPriceMin());
					searchForm.setPriceMax("" + search.getPriceMax());
					searchForm.setRoomSizeMin("" + search.getRoomSizeMin());
					searchForm.setRoomSizeMax("" + search.getRoomSizeMax());
					searchForm.setCity(search.getCity());
					
					Iterable<Ad> searchResults = searchService.computeSearchResults(searchForm);
					
					for(Ad result : searchResults)
						if(result.equals(ad)) {
							notification.setAdId(ad.getId());
							notification.setUserId(user.getId());
							notificationDao.save(notification);
							break;
						}
				}
			}
	}

	@Transactional
	public Iterable<Notification> findNotifications(User user) {
		Iterable<Notification> allnotifications = notificationDao.findAll();
		LinkedList<Notification> results = new LinkedList<Notification>();

		for(Notification n : allnotifications) {
			if(n.getUserId().equals(loginService.getLoggedInUser().getId())) {
				n.setAd(adDao.findOne(n.getAdId()));
				results.add(n);
			}
		}

		return (Iterable<Notification>)results;
	}

	@Transactional
	public Notification removeNotification(Notification notification) {
		notificationDao.delete(notification);

		return notification;
	}

}
