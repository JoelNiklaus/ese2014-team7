package org.sample.controller.service;

import org.sample.model.Enquiry;
import org.sample.model.Notification;
import org.sample.model.User;
import org.sample.model.dao.EnquiryDao;
import org.sample.model.dao.NotificationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;


@Service
public class UpdateServiceImpl implements UpdateService {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	EnquiryDao enquiryDao;
	
	@Autowired
	NotificationDao notificationDao;
	
	public void updateNumberOfUnreadItems(ModelAndView model) {
		User user = loginService.getLoggedInUser();
		
		if(user != null)
		{
			Iterable<Enquiry> allEnquiries = enquiryDao.findAllByReceiverId(user.getId());
			long numOfUnreadE = 0;
				
			for(Enquiry e : allEnquiries)
				if(e.isUnread())
					numOfUnreadE++;
				
			model.addObject("numUnreadEnquiries", numOfUnreadE);
			
			Iterable<Notification> allNotifications = notificationDao.findAllByUserId(user.getId());
			long numOfUnreadN = 0;
				
			for(Notification e : allNotifications)
				if(e.isUnread())
					numOfUnreadN++;
				
			model.addObject("numUnreadNotifications", numOfUnreadN);
			
		}
		else
		{
			model.addObject("numUnreadEnquiries", 0);
			model.addObject("numUnreadNotifications", 0);
		}
		
	}
}
