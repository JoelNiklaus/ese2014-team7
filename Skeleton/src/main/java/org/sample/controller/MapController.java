package org.sample.controller;



import org.sample.controller.service.LoginService;
import org.sample.model.dao.AdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MapController {

    @Autowired
    LoginService sampleService;
    @Autowired
    AdDao adDao;

    @Autowired
    LoginService loginService;
    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public ModelAndView index() {
    	ModelAndView model = new ModelAndView("map");
    	model.addObject("adList", adDao.findAll());
    	model.addObject("loggedInUser", loginService.getLoggedInUser());

        return model;
    }
}
