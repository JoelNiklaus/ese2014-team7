package org.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookmarksController {

   @RequestMapping("/bookmarks")
   public ModelAndView showBookmarks()
   {
	   ModelAndView model = new ModelAndView("bookmarks");


	   
	   return model;
   }
	
}