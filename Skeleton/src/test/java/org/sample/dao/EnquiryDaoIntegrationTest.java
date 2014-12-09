package org.sample.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.Iterable;

import org.sample.model.Enquiry;
import org.sample.model.dao.EnquiryDao;
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
public class EnquiryDaoIntegrationTest {
	
	@Autowired
	EnquiryDao enquiryDao;

	@Test
	public void testFindAllByReceiverId() {
		
		Long RECEIVER_ID = 1L;
		
		Enquiry enquiry = new Enquiry();
		enquiry.setReceiverId(RECEIVER_ID);
		
		enquiry = enquiryDao.save(enquiry);
		Iterable<Enquiry> enquiryIterable = enquiryDao.findAllByReceiverId(RECEIVER_ID);
		assertEquals(Iterables.get(enquiryIterable, 0).getReceiverId(), RECEIVER_ID);
	}

}
