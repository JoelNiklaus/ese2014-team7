package org.sample.controller.service;

import org.sample.controller.enums.VisitAppointmentState;
import org.sample.controller.pojos.VisitAppointmentForm;
import org.sample.model.VisitAppointment;


public interface VisitAppointmentService {
	
	/**
	 * 
	 * @param vaForm to save
	 * @return vaForm with id
	 */
	public VisitAppointmentForm save(VisitAppointmentForm vaForm);
	
	/**
	 * 
	 * @param visitAppointment
	 */
	public VisitAppointment getVisitAppointment(Long id);
	
	/**
	 * 
	 * @param id
	 */
	public void remove(Long id);
	
	/**
	 * 
	 * @param id
	 * @param state
	 */
	public void updateState(Long id, VisitAppointmentState state);
}
