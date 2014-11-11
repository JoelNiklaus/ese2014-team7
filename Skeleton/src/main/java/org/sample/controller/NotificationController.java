package org.sample.controller;

import org.sample.controller.service.LoginService;
import org.sample.controller.service.NotificationService;
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
	AdDao adRepository;
	@Autowired
	NotificationDao notificationRepository;

	@RequestMapping("/notifications")
	public ModelAndView showNotifications() {
		ModelAndView model = new ModelAndView("notifications");

		User user = loginService.getLoggedInUser();
		model.addObject("notifications", notificationService.findNotifications(user));
		model.addObject("loggedInUser", user);
		return model;
	}

	@RequestMapping(value = "/removeNotification", method = RequestMethod.GET)
	public ModelAndView removenotification(@RequestParam String id) {
		ModelAndView model = showNotifications();

		try {
			long notificationId = Long.parseLong(id);
			Notification notification = notificationRepository.findOne(notificationId);

			User user = loginService.getLoggedInUser();
			notificationService.removeNotification(notification);
			// update notification list
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