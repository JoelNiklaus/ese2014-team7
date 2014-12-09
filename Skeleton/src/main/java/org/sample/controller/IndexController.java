package org.sample.controller;

import org.sample.controller.service.LoginService;
import org.sample.controller.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {
	
	@Autowired LoginService loginService;
	@Autowired UpdateService updateService;
    
	/**
	 * Shows a 404 error page.
	 * 
	 * @return
	 */
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public ModelAndView notFound() {
    	ModelAndView model = new ModelAndView("404");
    	model.addObject("loggedInUser", loginService.getLoggedInUser());
    	updateService.updateNumberOfUnreadItems(model);
        return model;
    }
    
    /**
     * 
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/security-error", method = RequestMethod.GET)
    public String securityError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("page_error", "You do have have permission to do that!");

        return "redirect:/";
    }
}
