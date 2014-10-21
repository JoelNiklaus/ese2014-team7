package org.sample.controller;


import org.json.JSONObject;
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

    	//TODO plot all houses
    	String ort="Bern";
    	String plz="3012";
    	String street="siedlerstrasse";
    	String nr="5";
    	
    	String url = "http://nominatim.openstreetmap.org/search?q="+ort+",+"+street+"+"+nr+"&format=json&polygon=0&addressdetails=1";
    	
    	try{
    		JSONObject request = JSON.readJsonFromUrl(url);
    		model.addObject("lat", request.getString("lat"));
    		model.addObject("lon", request.getString("lon"));
    		model.addObject("shortDescription",street+" "+nr+",<br/> "+plz+" "+ort);
    	} catch (Exception e){
    		System.out.println(e.toString());
    	}

        return model;
    }


    


}
