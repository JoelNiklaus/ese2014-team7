package org.sample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;


@Entity
public class Bookmark{

	@Id
    @GeneratedValue
	private Long bookmarkId;

	private Long adId;
	private Long userId;
	
	@Transient
	private Ad ad;
	
	public Ad getAd() {
		return ad;
	}
	public void setAd(Ad ad) {
		this.ad = ad;
	}
	public Long getBookmarkId() {
		return bookmarkId;
	}
	public void setBookmarkId(Long bookmarkId) {
		this.bookmarkId = bookmarkId;
	}
	public Long getAdId() {
		return adId;
	}
	public void setAdId(Long adId) {
		this.adId = adId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
   
}
