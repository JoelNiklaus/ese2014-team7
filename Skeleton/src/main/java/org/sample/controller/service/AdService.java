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
    
    public Iterable<Ad> adCatcher();


}
