package org.sample.model.dao;

import org.sample.model.Enquiry;
import org.springframework.data.repository.CrudRepository;

public interface EnquiryDao extends CrudRepository<Enquiry,Long> {
	
	Iterable<Enquiry> findAllByReceiverId(Long receiverId);
	
}
