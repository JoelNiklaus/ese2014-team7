package org.sample.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.model.Notification;
import org.sample.model.dao.NotificationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Iterables;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/spring*.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class NotificationDaoIntegrationTest {
	@Autowired
	NotificationDao notificationDao;
	
	@Test
	public void testFindAllByUserId() {
		Long USER_ID = 1L;
		
		Notification notification = new Notification();
		notification.setUserId(USER_ID);
		
		notification = notificationDao.save(notification);
		Iterable<Notification> notifictionIterable = notificationDao.findAllByUserId(USER_ID);
		assertEquals(Iterables.get(notifictionIterable, 0).getUserId(), USER_ID);
	}
}
