package org.sample.controller.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.sample.controller.NominatimConnector;
import org.sample.controller.pojos.AdForm;
import org.sample.model.Coordinates;
import org.sample.model.Picture;
import org.sample.model.Ad;
import org.sample.model.dao.PictureDao;
import org.sample.model.dao.UserDao;
import org.sample.model.dao.AdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdServiceImpl implements AdService {

	@Autowired AdDao adDao;
	@Autowired PictureDao pictureDao;
	@Autowired UserDao userDao;
	@Autowired LoginService loginService;
    
    @Transactional
	public AdForm saveFrom(AdForm adForm) {
	    
    	Ad ad = new Ad();
    	Coordinates coordinates;
		try {
			coordinates = NominatimConnector.getCoordinatesFromAddress(adForm.getStreet(), adForm.getHouseNr().toString(), adForm.getCity(), adForm.getZip().toString());
		    ad.setLon(coordinates.getLon());
		    ad.setLat(coordinates.getLat());
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		//Pictures
		Set<Picture> pictures = new HashSet<Picture>();
		Picture pic1 = new Picture();
		//Picture pic2 = new Picture();
		//Picture pic3 = new Picture();
		//Picture pic4 = new Picture();

		pic1.setFilePath(adForm.getImg_one());
		pic1.setIsMainPic(true);
		pictures.add(pic1);
		
		/*
		pic2.setFilePath(adForm.getImg_two());
		pic2.setIsMainPic(false);
		pictures.add(pic2);
		pic3.setFilePath(adForm.getImg_three());
		pic3.setIsMainPic(false);
		pictures.add(pic3);
		pic4.setFilePath(adForm.getImg_four());
		pic4.setIsMainPic(false);
		pictures.add(pic4); 
		*/

		ad.setPictures(pictures);

		if (pic1 != null)
			pictureDao.save(pic1);
		/*
		if (pic2 != null)
			pictureDao.save(pic2);
		if (pic3 != null)
			pictureDao.save(pic3);
		if (pic4 != null)
			pictureDao.save(pic4);
		*/
		
	    ad.setId(adForm.getId());
	    ad.setPlacerId(loginService.getLoggedInUser().getId()); //TODO: hardcoded id!
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
	    ad.setRoomSize(adForm.getRoomSize());
	    ad.setDescription(adForm.getDescription());
	    ad.setUs(adForm.getUs());
	    ad.setYou(adForm.getYou());

	    ad = adDao.save(ad);
		
	    return  adForm;
	}

	public Iterable<Ad> adCatcher() {
		return adDao.findAll();
	}

	public Ad getAd(Long id) {
		return adDao.findOne(id);
	}

	public String getPicture(Long picId) {
		return pictureDao.findOne(picId).getFilePath();
	}

	public ArrayList<Long> getAdPictureIds(Long adId) {
		Set<Picture> pictures = getAd(adId).getPictures();
		ArrayList<Long> pics = new ArrayList<Long>();

		for (Picture picture : pictures) {
			if (picture.getFilePath() != null) {

				if (picture.getIsMainPic() == false)
					pics.add(picture.getId());

			}

		}
		return pics;
	}

	public long getAdMainPic(Long adId) {
		Set<Picture> pictures = getAd(adId).getPictures();
		long mainPic = 0;
		
		for (Picture picture : pictures) {
			if (picture != null) {

				if (picture.getIsMainPic() == true)
					mainPic = picture.getId();
			}

		}
		return mainPic;
	}

	public ArrayList<Picture> getAdPictures(Long adId) {
		Set<Picture> pictures = getAd(adId).getPictures();
		ArrayList<Picture> pics = new ArrayList<Picture>();
		

		for (Picture picture : pictures) {
			if (picture.getFilePath() != null) {

				if (picture.getIsMainPic() == false)
					pics.add(picture);

			}

		}
		return pics;
	}

}
