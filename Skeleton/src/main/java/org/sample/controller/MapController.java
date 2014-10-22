package org.sample.controller;


import java.io.IOException;

import org.json.JSONException;

import org.sample.controller.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MapController {

    @Autowired
    LoginService sampleService;


    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public ModelAndView index() {
    	ModelAndView model = new ModelAndView("map");

    	//TODO plot all houses
    	String place="Bern";
    	String plz="3012";
    	String street="Siedlerstrasse";
    	String number="5";
    	
    	try {
			model.addObject("Coordinates", NominatimConnector.getCoordinatesFromAddress(street, number, place, plz));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	model.addObject("shortDescription", street + " " + number + "<br />" + plz + " " + place );
        return model;
    }

}
