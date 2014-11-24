package org.sample.model.dao;

import org.sample.model.Notification;
import org.springframework.data.repository.CrudRepository;

public interface NotificationDao extends CrudRepository<Notification,Long> {

	Iterable<Notification> findAllByUserId(Long userId);
}
