package org.sample.controller.service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.LinkedList;

import org.sample.controller.exceptions.InvalidAdException;
import org.sample.controller.pojos.EnquiryForm;
import org.sample.controller.pojos.EnquiryRatingForm;
import org.sample.model.Enquiry;
import org.sample.model.EnquiryComparator;
import org.sample.model.EnquiryComparatorRating;
import org.sample.model.dao.AdDao;
import org.sample.model.dao.EnquiryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnquiryServiceImpl implements EnquiryService {
	
	@Autowired LoginService loginService;
	@Autowired AdDao adDao;
	@Autowired EnquiryDao enquiryDao;


	@Transactional //TODO: throw exception for null Ads to load 404 in Enquiry controller
	public EnquiryForm submit(EnquiryForm enquiryForm) {
		Enquiry enquiry = new Enquiry();
		
		/* fetch ad from DB, according to submitted Ad-ID. Throw exception if no ad found */
		enquiryForm.setAd(adDao.findOne(enquiryForm.getAdId()));
		if(enquiryForm.getAd() == null)
			throw new InvalidAdException("This ad doesn't exist");
			
		/* Update enquiry FORM: set sender to logged in user, receiver to ad placer ID */
		enquiryForm.setTimestamp(new Timestamp(System.currentTimeMillis()));
		enquiryForm.setSenderId(loginService.getLoggedInUser().getId());
		enquiryForm.setReceiverId(enquiryForm.getAd().getPlacerId());
		
		/* Fill in enquiry from completed enquiry form */
		enquiry.setTimestamp(enquiryForm.getTimestamp());
		enquiry.setAdId(enquiryForm.getAdId());
		enquiry.setSenderId(enquiryForm.getSenderId());
		enquiry.setReceiverId(enquiryForm.getReceiverId());
		enquiry.setMessageText(enquiryForm.getMessageText());
		enquiry.setUnread(true);
		
		enquiryDao.save(enquiry);
		
		return enquiryForm;
	}

	
	
	@Transactional
	public Iterable<Enquiry> findSentEnquiries() {
		Iterable<Enquiry> allEnquiries = enquiryDao.findAll();
		LinkedList<Enquiry> results = new LinkedList<Enquiry>();
		
		for(Enquiry e : allEnquiries)
		{
			if(e.getSenderId().equals(loginService.getLoggedInUser().getId()))
			{
				e.setAd(adDao.findOne(e.getAdId()));
				results.add(e);
			}
				
		}
		
		Collections.sort(results, new EnquiryComparator());
		
		return (Iterable<Enquiry>)results;
	}

	
	
	@Transactional
	public Iterable<Enquiry> findNewReceivedEnquiries() {
		Iterable<Enquiry> allEnquiries = findUnratedEnquiriesByUser();
		LinkedList<Enquiry> results = new LinkedList<Enquiry>();
		
		for(Enquiry e : allEnquiries)
		{
			if(!e.isUnread())
			{
				e.setAd(adDao.findOne(e.getAdId()));
				results.add(e);
			}
		}
		
		Collections.sort(results, new EnquiryComparatorRating());
		
		return (Iterable<Enquiry>)results;
	}
	
	@Transactional
	public Iterable<Enquiry> findRatedReceivedEnquiries() {
		Iterable<Enquiry> allEnquiries = enquiryDao.findAll();
		LinkedList<Enquiry> results = new LinkedList<Enquiry>();
		
		for(Enquiry e : allEnquiries)
		{
			if((e.getReceiverId().equals(loginService.getLoggedInUser().getId())) && e.getRating() != 0)
			{
				e.setAd(adDao.findOne(e.getAdId()));
				results.add(e);
			}
		}
		
		Collections.sort(results, new EnquiryComparatorRating());
		
		return (Iterable<Enquiry>)results;
	}



	public EnquiryRatingForm submitRating(EnquiryRatingForm form) {
		
		Enquiry enquiry = enquiryDao.findOne(form.getEnquiryId());
		form.setEnquiry(enquiry);
		
		if(enquiry == null)
				System.out.println("Service: no enquiry added!");
		else {
			enquiry.setRating(form.getRating());
			enquiryDao.save(enquiry);
		}
		
		return form;
	}
    
	@Transactional
	public Enquiry removeEnquiry(Enquiry enquiry) {
		enquiryDao.delete(enquiry);
		
		return enquiry;
	}


	@Transactional
	public Iterable<Enquiry> findUnreadEnquiries() {
		Iterable<Enquiry> allEnquiries = findUnratedEnquiriesByUser();
		LinkedList<Enquiry> results = new LinkedList<Enquiry>();
		
		for(Enquiry e : allEnquiries)
		{
			if(e.isUnread())
			{
				e.setAd(adDao.findOne(e.getAdId()));
				results.add(e);
			}
		}
		
		Collections.sort(results, new EnquiryComparatorRating());
		markEnquiriesAsRead(results);
		
		return (Iterable<Enquiry>)results;
	}
	
	private Iterable<Enquiry> findUnratedEnquiriesByUser()
	{
		Iterable<Enquiry> allEnquiries = enquiryDao.findAll();
		LinkedList<Enquiry> results = new LinkedList<Enquiry>();
		
		for(Enquiry e : allEnquiries)
		{
			if((e.getReceiverId().equals(loginService.getLoggedInUser().getId())) && e.getRating() == 0)
			{
				e.setAd(adDao.findOne(e.getAdId()));
				results.add(e);
			}
		}
		
		Collections.sort(results, new EnquiryComparatorRating());
		
		return (Iterable<Enquiry>)results;
	}
	
	@Transactional
	private void markEnquiriesAsRead(Iterable<Enquiry> enquiries)
	{
		for(Enquiry e : enquiries)
		{
			e.setUnread(false);
			enquiryDao.save(e);
		}	
	}

}
