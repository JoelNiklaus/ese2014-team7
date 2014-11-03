package org.sample.controller.service;

import org.sample.controller.pojos.EnquiryForm;
import org.sample.model.Bookmark;
import org.sample.model.Enquiry;
import org.sample.model.User;

public interface BookmarkService {

    public Bookmark saveBookmark(Long adId);
        
    public Iterable<Bookmark> findBookmarks(User user);
    
    public boolean alreadyBookmarked(User user, Long adId);
    
    public Bookmark removeBookmark(Bookmark bookmark);
}
