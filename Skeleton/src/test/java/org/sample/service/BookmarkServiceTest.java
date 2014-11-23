 package org.sample.service;


import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.sample.controller.service.BookmarkService;
import org.sample.controller.service.BookmarkServiceImpl;
import org.sample.model.Bookmark;
import org.sample.model.dao.BookmarkDao;

public class BookmarkServiceTest {

    private BookmarkDao bookmarkDao;
    private BookmarkService bookmarkService;

    @Before
    public void doSetup() {
        bookmarkDao = mock(BookmarkDao.class);
        bookmarkService = new BookmarkServiceImpl();
    }

    @Test
    public void testSaveBookmark() {
    	//TODO set loggedin User
    	
    	when(bookmarkDao.save(any(Bookmark.class)))
    	.thenAnswer(new Answer<Bookmark>() {
    		public Bookmark answer(InvocationOnMock invocation) throws Throwable {
    			Bookmark bookmark = (Bookmark) invocation.getArguments()[0];
    			bookmark.setBookmarkId(1L);
    			return bookmark;
    		}
    	});
        
    	Long adId = 1L;
    	bookmarkService.saveBookmark(adId);
    	
    	assertEquals(new Long(1), bookmarkDao.findOne(1L).getBookmarkId());
        

    }
}