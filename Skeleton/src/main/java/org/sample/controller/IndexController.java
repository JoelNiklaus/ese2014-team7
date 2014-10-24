package org.sample.controller;

import org.sample.controller.pojos.LoginForm;
import org.sample.controller.pojos.SignupForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
    	ModelAndView model = new ModelAndView("index");
    	model.addObject("signupForm", new SignupForm());
    	model.addObject("loginForm", new LoginForm());
        return model;
    }
        
    @RequestMapping(value = "/security-error", method = RequestMethod.GET)
    public String securityError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("page_error", "You do have have permission to do that!");
        return "redirect:/";
    }

}
