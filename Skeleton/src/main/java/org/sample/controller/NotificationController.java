package org.sample.controller;

import org.sample.controller.service.LoginService;
import org.sample.controller.service.NotificationService;
import org.sample.controller.service.UpdateService;
import org.sample.model.Notification;
import org.sample.model.User;
import org.sample.model.dao.AdDao;
import org.sample.model.dao.NotificationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NotificationController {
	@Autowired
	LoginService loginService;
	@Autowired
	NotificationService notificationService;
	@Autowired
	AdDao adDao;
	@Autowired
	NotificationDao notificationDao;
	@Autowired
	UpdateService updateService;

	/**
	 * Creates a model displaying user's notifications
	 * 
	 * @return notification model
	 */
	@RequestMapping("/notifications")
	public ModelAndView showNotifications() {
		assert loginService.getLoggedInUser() != null;
		
		ModelAndView model = new ModelAndView("notifications");

		User user = loginService.getLoggedInUser();
		updateService.updateNumberOfUnreadItems(model);
		
		model.addObject("notifications", notificationService.findNotifications(user));
		model.addObject("unreadNotifications", notificationService.findUnreadNotifications());
		
		notificationService.markAllNotificationsAsRead(user);
		model.addObject("loggedInUser", user);
		return model;
	}

	/**
	 * Removes notification with given id from DB, returns notification model
	 * 
	 * @param id	id of notification to be deleted
	 * @return		notification model, 404 if id invalid
	 */
	@RequestMapping(value = "/removeNotification", method = RequestMethod.GET)
	public ModelAndView removenotification(@RequestParam String id) {
		assert loginService.getLoggedInUser() != null;
		
		ModelAndView model = showNotifications();

		try {
			long notificationId = Long.parseLong(id);
			Notification notification = notificationDao.findOne(notificationId);

			User user = loginService.getLoggedInUser();
			updateService.updateNumberOfUnreadItems(model);
			notificationService.removeNotification(notification);
			model = showNotifications();
			model.addObject("message", "notification successfully removed.");
			model.addObject("notifications", notificationService.findNotifications(user)); 
		} catch(NumberFormatException ex){
			model = new ModelAndView("404");
		} catch(IllegalArgumentException e) {
			model = new ModelAndView("404");
		}
		return model;
	}
}