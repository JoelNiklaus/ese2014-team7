package org.sample.controller;

import org.sample.controller.pojos.EnquiryForm;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.service.BookmarkService;
import org.sample.controller.service.LoginService;
import org.sample.model.Ad;
import org.sample.model.Bookmark;
import org.sample.model.User;
import org.sample.model.dao.AdDao;
import org.sample.model.dao.BookmarkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookmarksController {
	@Autowired
	LoginService loginService;
	@Autowired
	BookmarkService bookmarkService;
	@Autowired
	AdDao adRepository;
	@Autowired
	BookmarkDao bookmarkRepository;

	@RequestMapping("/bookmarks")
	public ModelAndView showBookmarks() {
		ModelAndView model = new ModelAndView("bookmarks");

		User user = loginService.getLoggedInUser();
		model.addObject("bookmarks", bookmarkService.findBookmarks(user));
		model.addObject("loggedInUser", user);
		return model;

	}

	@RequestMapping(value = "/removeBookmark", method = RequestMethod.GET)
	public ModelAndView removeBookmark(@RequestParam String id) {
		ModelAndView model = showBookmarks();

		try {
			long bookmarkId = Long.parseLong(id);
			Bookmark bookmark = bookmarkRepository.findOne(bookmarkId);

			User user = loginService.getLoggedInUser();
			bookmarkService.removeBookmark(bookmark);
			// update bookmark list
			model = showBookmarks();
			model.addObject("message", "bookmark successfully removed.");
			model.addObject("bookmarks", bookmarkService.findBookmarks(user)); 
		} catch(NumberFormatException ex){
			model = new ModelAndView("404");
		} catch(IllegalArgumentException e) {
			model = new ModelAndView("404");
		}
		return model;

	}

	@RequestMapping(value = "/bookmark", method = RequestMethod.GET)
	public ModelAndView bookmark(@RequestParam String id) {
		ModelAndView model = showBookmarks();

		try{
			long adId = Long.parseLong(id);
			Ad ad = adRepository.findOne(adId);

			if(ad == null)
				model = new ModelAndView("404");
			else if (bookmarkService.alreadyBookmarked(loginService.getLoggedInUser(), adId))
				model.addObject("message", "Ad already bookmarked.");
			else {
				bookmarkService.saveBookmark(adId);
				// update bookmark list
				model = showBookmarks();
				model.addObject("message", "Ad successfully bookmarked.");
			}  
		} catch(NumberFormatException ex){
			model = new ModelAndView("404");
		}

		return model;
	}
}