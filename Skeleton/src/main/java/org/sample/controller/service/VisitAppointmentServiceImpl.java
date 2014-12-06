package org.sample.controller.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.sample.controller.enums.VisitAppointmentState;
import org.sample.controller.pojos.VisitAppointmentForm;
import org.sample.model.VisitAppointment;
import org.sample.model.dao.VisitAppointmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VisitAppointmentServiceImpl implements VisitAppointmentService{

	@Autowired VisitAppointmentDao vaDao;
	
	@Transactional
	public VisitAppointmentForm save(VisitAppointmentForm vaForm) {
		
		Date endDate;
		Date startDate;
		try {
			endDate = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(vaForm.getEndDate());
			System.out.println(endDate.toString());
			startDate= new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(vaForm.getStartDate());
			VisitAppointment visitAppointment = new VisitAppointment();
			visitAppointment.setEquiryId(vaForm.getEnquiryId());
			visitAppointment.setEndDate(endDate);
			visitAppointment.setComment(vaForm.getComment());
			visitAppointment.setEquiryId(vaForm.getEnquiryId());
			visitAppointment.setStartDate(startDate);
			visitAppointment.setState(VisitAppointmentState.NEW);
			visitAppointment = vaDao.save(visitAppointment);
			vaForm.setId(visitAppointment.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vaForm;
	}

}
