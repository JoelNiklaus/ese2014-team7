package org.sample.controller.service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.sample.controller.pojos.AdForm;
import org.sample.model.Ad;
import org.sample.model.Picture;
import org.sample.model.dao.PictureDao;
import org.sample.model.dao.UserDao;
import org.sample.model.dao.AdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdServiceImpl implements AdService {

	@Autowired AdDao adDao;
	@Autowired UserDao userDao;
	@Autowired LoginService loginService;
    @Autowired NotificationService notificationService;
    @Autowired PictureDao pictureDao;
    
    @Transactional
	public AdForm saveFrom(AdForm adForm) {
	    
    	Ad ad = new Ad();
    	DateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.GERMAN);
    	
    	Set<Picture> pictures = new HashSet<Picture>(0);
    	
    	List<String> pictureIdList = Arrays.asList(adForm.getImageIds().replaceAll(" ", "").split(","));
    	for (String id : pictureIdList) {
    		if(id!=null);
    			if(!id.equals(""))
    				pictures.add(pictureDao.findOne(new Long(id)));
		}
    	
    	ad.setPictures(pictures);
    	
	    ad.setId(adForm.getId());
	    ad.setPlacerId(loginService.getLoggedInUser().getId());
	    Timestamp timestamp  = new Timestamp(System.currentTimeMillis());
		ad.setTimestamp(timestamp);
	    ad.setTitle(adForm.getTitle());
	    ad.setStreet(adForm.getStreet());
	    ad.setHouseNr(adForm.getHouseNr());
	    ad.setCity(adForm.getCity());
	    ad.setZip(adForm.getZip());
	    ad.setRent(adForm.getRent());
	    ad.setAddCost(adForm.getAddCost());
	    
	    ad.setDateIn(adForm.getDateIn());
	    ad.setDateOut(adForm.getDateOut());
		ad.setDateInD(parseDate(adForm.getDateIn()));
		ad.setDateOutD(parseDate(adForm.getDateOut()));
	    
	    ad.setRoomSize(adForm.getRoomSize());
	    ad.setDescription(adForm.getDescription());
	    ad.setDistanceToPublicTransport(adForm.getDistanceToPublicTransport());
	    ad.setDistanceToShopping(adForm.getDistanceToShopping());
	    ad.setUs(adForm.getUs());
	    ad.setYou(adForm.getYou());
	    ad.setLat(adForm.getLat());
	    ad.setLng(adForm.getLng());
	    ad = adDao.save(ad);
	    
		// Ad successfully saved to DB
		// can now send notifications to users
		notificationService.sendNotificationsForMatchingSearches(ad);
		
	    return  adForm;
	}

	public Iterable<Ad> adCatcher() {
		return adDao.findAll();
	}

	public Ad getAd(Long id) {
		return adDao.findOne(id);
	}

	public void deleteAd(Ad ad) {

		PictureService pictureService = new PictureServiceImpl();
		for (Picture picture : ad.getPictures()) {
			if(picture != null)
				pictureService.deletePicture(picture);
		}
		adDao.delete(ad.getId());
	}

	public void editAd(AdForm adForm, Long adId) {
		
		Ad ad = adDao.findOne(adId);
		
    	Set<Picture> pictures = new HashSet<Picture>(0);
    	
    	List<String> pictureIdList = Arrays.asList(adForm.getImageIds().replaceAll(" ", "").split(","));
    	for (String id : pictureIdList) {
    		if(id!=null);
    			if(!id.equals(""))
    				pictures.add(pictureDao.findOne(new Long(id)));
		}
    	
    	ad.setPictures(pictures);
		
	    Timestamp timestamp  = new Timestamp(System.currentTimeMillis());
		ad.setTimestamp(timestamp);
	    ad.setTitle(adForm.getTitle());
	    ad.setStreet(adForm.getStreet());
	    ad.setHouseNr(adForm.getHouseNr());
	    ad.setCity(adForm.getCity());
	    ad.setZip(adForm.getZip());
	    ad.setRent(adForm.getRent());
	    ad.setAddCost(adForm.getAddCost());
	    
	    ad.setDateIn(adForm.getDateIn());
	    ad.setDateOut(adForm.getDateOut());
	    ad.setDateInD(parseDate(adForm.getDateIn()));
		ad.setDateOutD(parseDate(adForm.getDateOut()));
	    
	    ad.setRoomSize(adForm.getRoomSize());
	    ad.setDescription(adForm.getDescription());
	    ad.setDistanceToPublicTransport(adForm.getDistanceToPublicTransport());
	    ad.setDistanceToShopping(adForm.getDistanceToShopping());
	    ad.setUs(adForm.getUs());
	    ad.setYou(adForm.getYou());
	    ad.setLat(adForm.getLat());
	    ad.setLng(adForm.getLng());
	    
	    ad = adDao.save(ad);
	    
	    // Ad successfully saved to DB
	 	// can now send notifications to users
	    notificationService.sendNotificationsForMatchingSearches(ad);
		
	}
	
	/**
	 * Parses Date from String.
	 * @param dateString  	format: dd.mm.yyyy
	 * @return
	 */
	private Date parseDate(String dateString)
	{
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
	
		int month, day, year;
		
		try
		{		
			day = Integer.parseInt(dateString.substring(0, 2));
			month = Integer.parseInt(dateString.substring(3, 5))-1;
			year = Integer.parseInt(dateString.substring(6, 10));
			
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, day);
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			
			date = cal.getTime();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return date;
	}

}
