package org.sample.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.sample.controller.pojos.VisitAppointmentForm;
import org.sample.controller.service.VisitAppointmentService;
import org.sample.controller.service.VisitAppointmentServiceImpl;
import org.sample.model.User;
import org.sample.model.VisitAppointment;
import org.sample.model.dao.VisitAppointmentDao;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VisitAppointmentServiceTest {
	
	@Autowired
	private VisitAppointmentDao visitAppointmentDao;
	@Autowired
	private VisitAppointmentService visitAppointmentService;
	
	@Before
	public void doSetup(){
		visitAppointmentDao = mock(VisitAppointmentDao.class);
		visitAppointmentService  = mock(VisitAppointmentService.class);
	}

	@Test
	public void testSave() {
		VisitAppointmentForm visitAppointmentForm = new VisitAppointmentForm();
		visitAppointmentForm.setEnquiryId(1L);
		
		when(visitAppointmentDao.save(any(VisitAppointment.class)))
        .thenAnswer(new Answer<VisitAppointment>() {
            public VisitAppointment answer(InvocationOnMock invocation) throws Throwable {
            	VisitAppointment visitAppointment = (VisitAppointment) invocation.getArguments()[0];
            	visitAppointment.setId(1L);
                return visitAppointment;
            }
        });
		
		assertNull(visitAppointmentForm.getId());
		
		visitAppointmentForm = visitAppointmentService.save(visitAppointmentForm);
		
		assertNotNull(visitAppointmentForm.getId());
		assertTrue(visitAppointmentForm.getId() > 0);
	}
}
