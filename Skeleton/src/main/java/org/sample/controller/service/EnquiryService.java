package org.sample.controller.service;

import org.sample.controller.pojos.EnquiryForm;
import org.sample.controller.pojos.EnquiryRatingForm;
import org.sample.model.Enquiry;

public interface EnquiryService {

	/**
	 * Completes the given enquiry form by adding timestamp and sender/receiver id and 
	 * saves the enquiry to the DB.
	 * 
	 * @param 	enquiryForm			the enquiry form to be saved to the DB
	 * @return 						the completed form
	 */
    public EnquiryForm submit(EnquiryForm enquiryForm);
    
    /**
	 * Compiles an iterable of all enquiries sent by the logged in user.
	 * 
	 * @return iterable of sent enquiries
	 */
    public Iterable<Enquiry> findSentEnquiries();
    
    /**
	 * Compiles an iterable of all enquiries received for the logged in user that have already been rated.
	 * 
	 * @return iterable of received enquiries
	 */
    public Iterable<Enquiry> findRatedReceivedEnquiries();
    
    /**
	 * Compiles an iterable of all enquiries received for the logged in user that haven't been rated yet.
	 * 
	 * @return iterable of received enquiries
	 */
    public Iterable<Enquiry> findNewReceivedEnquiries();
    
    /**
     * Compiles an iterable of all new (=unrated) enquiries the user hasn't yet seen
     * 
     * @return iterable of unread enquiries
     */
    public Iterable<Enquiry> findUnreadEnquiries();

    /**
     * Submits the rating of an enquiry
     * 
     * @param form					filled out EnquiryRatingForm
     * @return
     */
    public EnquiryRatingForm submitRating(EnquiryRatingForm form);

    /**
     * Removes enquiry
     * 
     * @param enquiry
     * @return
     */
    public Enquiry removeEnquiry(Enquiry enquiry);
    
    /**
     * 
     * @param id
     * @return enquiry
     */
    public Enquiry getEnquiryById(Long id);
    
    /**
     * 
     * @param enquiry
     * @return enquiry
     */
    public Enquiry save(Enquiry enquiry);
}
