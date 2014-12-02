package org.sample.controller.service;

import org.sample.controller.pojos.VisitAppointmentForm;

public interface VisitAppointmentService {
	
	/**
	 * 
	 * @param vaForm to save
	 * @return vaForm with id
	 */
	public VisitAppointmentForm save(VisitAppointmentForm vaForm);
}
