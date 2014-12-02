package org.sample.controller;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.LoginForm;
import org.sample.controller.service.BookmarkService;
import org.sample.controller.service.LoginService;
import org.sample.controller.service.UpdateService;
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
	AdDao adDao;
	@Autowired
	BookmarkDao bookmarkDao;
	@Autowired
	UpdateService updateService;

	/**
	 * Assembles a model displaying user's bookmarked ads.
	 * 
	 * @return bookmarks model
	 */
	@RequestMapping("/bookmarks")
	public ModelAndView showBookmarks() {
		ModelAndView model = new ModelAndView("bookmarks");

		User user = loginService.getLoggedInUser();
		model.addObject("bookmarks", bookmarkService.findBookmarks(user));
		model.addObject("loggedInUser", user);
		updateService.updateNumberOfUnreadItems(model);
		return model;
	}

	/**
	 * Removes bookmark with given id from users bookmarks list and returns
	 * bookmars model.
	 * 
	 * @param id	id of bookmark to be removed
	 * 
	 * @return		bookmark model, 404 if bookmark id invalid
	 */
	@RequestMapping(value = "/removeBookmark", method = RequestMethod.GET)
	public ModelAndView removeBookmark(@RequestParam String id) {
		ModelAndView model = showBookmarks();

		try {
			long bookmarkId = Long.parseLong(id);
			Bookmark bookmark = bookmarkDao.findOne(bookmarkId);

			User user = loginService.getLoggedInUser();
			updateService.updateNumberOfUnreadItems(model);
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

	/**
	 * Adds ad with given id to user's bookmark list and returns a bookmarks model
	 * 
	 * @param id	id of ad to be bookmarked
	 * 
	 * @return		bookmark model, 404 if ad id invalid
	 */
	@RequestMapping(value = "/bookmark", method = RequestMethod.GET)
	public ModelAndView bookmark(@RequestParam String id) {
		ModelAndView model;
		
		if (loginService.getLoggedInUser() != null) {
			model = showBookmarks();
			model.addObject("loggedInUser", loginService.getLoggedInUser());
			updateService.updateNumberOfUnreadItems(model);
			try{
				long adId = Long.parseLong(id);
				Ad ad = adDao.findOne(adId);
	
				if(loginService.getLoggedInUser() == null)
					throw new InvalidUserException("Not logged in");
				
				if(ad == null)
					model = new ModelAndView("404");
				else if (bookmarkService.alreadyBookmarked(loginService.getLoggedInUser(), adId))
					model.addObject("message", "Ad already bookmarked.");
				else {
					bookmarkService.saveBookmark(adId);
					model = showBookmarks();
					model.addObject("message", "Ad successfully bookmarked.");
				}  
			} catch(NumberFormatException ex){
				model = new ModelAndView("404");
			} catch(InvalidUserException ex){
				model = new ModelAndView("login");
				model.addObject("loginForm", new LoginForm());
			}
		} else {
			model = new ModelAndView("login");
			model.addObject("loginForm", new LoginForm());
		}

		return model;
	}
}