package org.sample.controller.service;

import org.sample.model.Bookmark;
import org.sample.model.User;

public interface BookmarkService {

    /**
     * Bookmark is saved by the id of the ad.
     * 
     * @param adId
     * @return Bookmark			of said Ad.
     */
	public Bookmark saveBookmark(Long adId);
        
    /**
     * Returns all bookmarks saved by given user
     * 
     * @param user
     * @return Iterable<Bookmark>
     */
	public Iterable<Bookmark> findBookmarks(User user);
    
    /**
     * Give true or false if the bookmarked is already saved by given user or not.
     * 
     * @param user
     * @param adId
     * @return boolean			true=already save, false=not saved yet
     */
	public boolean alreadyBookmarked(User user, Long adId);
    
	/**
	 * Removes given bookmark from a user
	 * 
	 * @param bookmark
	 * @return Bookmark			deleted Bookmark
	 */
    public Bookmark removeBookmark(Bookmark bookmark);
}
