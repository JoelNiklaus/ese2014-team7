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
					Long priceMin = search.getPriceMin();
					Long priceMax = search.getPriceMax();
					Long roomSizeMin = search.getRoomSizeMin();
					Long roomSizeMax = search.getRoomSizeMax();
					String city = search.getCity();
					Iterable<Ad> searchResults;

					//TODO Replace magic Number by config file
					if(city.equals("")){
						if(priceMax == 3000){
							searchResults = adDao.findByRentGreaterThanAndRoomSizeBetween(priceMin-1, roomSizeMin, roomSizeMax);
						} else if(roomSizeMax==300){
							searchResults = adDao.findByRentBetweenAndRoomSizeGreaterThan(priceMin, priceMax, roomSizeMin-1);
						} else if((roomSizeMax == 300) && (priceMax == 3000)){
							searchResults = adDao.findByRentGreaterThanAndRoomSizeGreaterThan(priceMin, roomSizeMin);
						} else {
							searchResults = adDao.findByRentBetweenAndRoomSizeBetween(priceMin, priceMax, roomSizeMin, roomSizeMax);
						}
					} else {
						if(priceMax == 3000){
							searchResults = adDao.findByRentGreaterThanAndRoomSizeBetweenAndCityLike(priceMin, roomSizeMin, roomSizeMax, city);
						} else if(roomSizeMax==300){
							searchResults = adDao.findByRentBetweenAndRoomSizeGreaterThanAndCityLike(priceMin, priceMax, roomSizeMin, city);
						} else if((roomSizeMax == 300) && (priceMax == 3000)){
							searchResults = adDao.findByRentGreaterThanAndRoomSizeGreaterThanAndCityLike(priceMin, roomSizeMin, city);
						} else {
							searchResults = adDao.findByRentBetweenAndRoomSizeBetweenAndCityLike(priceMin, priceMax, roomSizeMin, roomSizeMax, city);
						}
					}

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
