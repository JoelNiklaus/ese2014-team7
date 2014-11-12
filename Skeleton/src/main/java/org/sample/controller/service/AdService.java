package org.sample.controller.service;

import java.util.ArrayList;

import org.sample.model.Picture;
import org.sample.controller.pojos.AdForm;
import org.sample.model.Ad;


public interface AdService {

    /**
	 * Saves Data from AdForm to DB.
	 * 
	 * @param 	adForm									the filled out form from the ad creation
	 * @return 	the adForm after DB transaction
	 */
    public AdForm saveFrom(AdForm adForm);
    
    /**
     * Fetches all the ads from the DB
     * 
     * @return all ads
     */
    public Iterable<Ad> adCatcher();
    
	/**
	 * Gets ad by id.
	 * 
	 * @param id
	 * @return Ad
	 */
    public Ad getAd(Long id);
	
	/**
	 * Return the filepath to opening a saved image
	 * 
	 * @param picId
	 * @return filepath
	 */
    public String getPicture(Long picId);
	
	/**
	 * Return all the picture ids to one ad
	 * 
	 * @param adId
	 * @return gets all the picture Ids of one Ad
	 */
    public ArrayList<Long> getAdPictureIds(Long adId);
	
	/**
	 * Returns Id of the main picture of the Ad
	 * 
	 * @param adId
	 * @return mainPic_id
	 */
    public long getAdMainPic(Long adId);
	
	/**
	 * Returns all pictures to one ad
	 * 
	 * @param adId
	 * @return Pictures
	 */
    public ArrayList<Picture> getAdPictures(Long adId);


}
