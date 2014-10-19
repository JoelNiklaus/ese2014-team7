package org.sample.controller;

import org.sample.controller.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class MapController {

    @Autowired
    SampleService sampleService;


    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public ModelAndView index() {
    	ModelAndView model = new ModelAndView("map");
    	model.addObject("Address", "Bern, Siedlerstrasse 5");
        return model;
    }


    


}
