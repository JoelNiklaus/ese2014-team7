package org.sample.controller.service;


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
	

	public void deleteAd(Ad ad);

	public void editAd(AdForm adForm, Long adId);


}
