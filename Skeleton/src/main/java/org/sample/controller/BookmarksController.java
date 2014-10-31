package org.sample.controller;

import org.sample.controller.service.LoginService;
import org.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookmarksController {
	@Autowired
	LoginService loginService;
   @RequestMapping("/bookmarks")
   public ModelAndView showBookmarks()
   {
	   ModelAndView model = new ModelAndView("bookmarks");
	   model.addObject("loggedInUser", loginService.getLoggedInUser());
	   return model;
	   
   }
	
}