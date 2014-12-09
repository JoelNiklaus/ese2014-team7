package org.sample.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.sample.controller.exceptions.InvalidAdException;
import org.sample.controller.pojos.EnquiryForm;
import org.sample.controller.pojos.EnquiryRatingForm;
import org.sample.controller.service.EnquiryService;
import org.sample.controller.service.LoginService;
import org.sample.controller.service.UpdateService;
import org.sample.model.Ad;
import org.sample.model.Enquiry;
import org.sample.model.Picture;
import org.sample.model.User;
import org.sample.model.dao.AdDao;
import org.sample.model.dao.EnquiryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EnquiryController {

	@Autowired
	AdDao adDao;

	@Autowired
	EnquiryDao enquiryDao;

	@Autowired
	EnquiryService enquiryService;

	@Autowired
	LoginService loginService;
	
	@Autowired
	UpdateService updateService;

	private String defaultMsg = "Hi guys, \n\n"
			+ "I'm interested in your room. Let's meet and see if I'm going to be your new roomie.\n\n"
			+ "Cheers, ";

	/**
	 * Builds an enquiry creation form, providing a summary of the ad to send an
	 * enquiry for, as well as an input text box displaying a standard message.
	 * 
	 * @param id
	 *            the id of the ad to send an enquiry for
	 * @return enquiry creation for or 404 model, for bad id parameter
	 *         (non-existing, non-numerical)
	 */
	@RequestMapping("/sendEnquiry")
	public ModelAndView createEnquiry(@RequestParam String id) {
		ModelAndView model = new ModelAndView("enquiryMask");

		model.addObject("loggedInUser", loginService.getLoggedInUser());
		updateService.updateNumberOfUnreadItems(model);

		try {
			Ad ad = adDao.findOne(Long.parseLong(id));

			if (ad == null)
				model = new ModelAndView("404");
			else {
					EnquiryForm enquiryForm = new EnquiryForm();
					enquiryForm.setAdId(ad.getId());
					enquiryForm.setMessageText(defaultMsg
							+ loginService.getLoggedInUser().getFirstName());

					enquiryForm.setReceiverId(ad.getPlacerId());
					enquiryForm.setAd(ad);

					model.addObject("ad", ad);
					model.addObject("enquiryForm", enquiryForm);
			}
		} catch (NumberFormatException ex) {
			model = new ModelAndView("404");
		}


		return model;
	}

	/**
	 * Manages processing of submitted enquiries and redirects to enquiries page
	 * 
	 * 
	 * @param enquiryForm
	 *            the filled in form to be processed
	 * @param result
	 *            the binding result
	 * @param redirectAttributes
	 *            the redirected attributes
	 * @return redirect string
	 */
	@RequestMapping(value = "/submitEnquiry", method = RequestMethod.POST)
	public String submitEnquiry(@Valid EnquiryForm enquiryForm,
			BindingResult result, RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("enquiries");
		model.addObject("loggedInUser", loginService.getLoggedInUser());
		updateService.updateNumberOfUnreadItems(model);

		try {
			enquiryService.submit(enquiryForm);
		} catch (InvalidAdException ex) {
			
		}
		 
		return "redirect:enquiries";
	}

	/**
	 * Returns a model presenting sent and received enquiries.
	 * 
	 * @return model presenting sent and received enquiries
	 */
	@RequestMapping("/enquiries")
	public ModelAndView showEnquiries() {
		ModelAndView model = new ModelAndView("enquiries");
		model.addObject("loggedInUser", loginService.getLoggedInUser());
		updateService.updateNumberOfUnreadItems(model);
		
		enquiryViewAddModelAttributes(model);

		return model;
	}

	/**
	 * Displays chosen enquiry in rating model, allowing user to rate enquiry.
	 * 
	 * @param id	id of enquiry to be rated
	 * 
	 * @return		rating model, 404 of enquiry idinvalid
	 */
	@RequestMapping(value = "/rateEnquiry")
	public ModelAndView rateEnquiry(@RequestParam long id) {

		ModelAndView model = new ModelAndView("rateEnquiry");
		model.addObject("loggedInUser", loginService.getLoggedInUser());
		updateService.updateNumberOfUnreadItems(model);

		Enquiry enquiry = enquiryDao.findOne(id);

		if (enquiry != null) {
			enquiry.setAd(adDao.findOne(enquiry.getAdId()));

			List<Integer> numberList = new ArrayList<Integer>();
			for (int i = 1; i <= 10; i++)
				numberList.add(i);

			model.addObject("numberList", numberList);

			model.addObject("enquiry", enquiry);
			EnquiryRatingForm enquiryRatingForm = new EnquiryRatingForm();
			enquiryRatingForm.setEnquiryId(enquiry.getEnquiryId());
			model.addObject("ratingForm", enquiryRatingForm);
		} else {
			model = new ModelAndView("404");
		}

		return model;
	}

	/**
	 * Submits rating for enquiry, returns enquiry display model
	 * 
	 * @param form					completed enquiry rating form
	 * @param result
	 * @param redirectAttributes
	 * 
	 * @return	enquiry display model, 404 if form invalid (or null) or result has errors
	 */
	@RequestMapping(value = "/submitRating", method = RequestMethod.POST)
	public ModelAndView submitRating(@Valid EnquiryRatingForm form,
			BindingResult result, RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView("redirect:/createVisitAppointment?enquiryId="+form.getEnquiryId()+"#prospectTab");
		//model.addObject("loggedInUser", loginService.getLoggedInUser());
		//updateService.updateNumberOfUnreadItems(model);


		if (form != null || !result.hasErrors()) {
			enquiryService.submitRating(form);
			//enquiryViewAddModelAttributes(model);
		}

		return model;
	}


	/**
	 * Removes enquiry with given id from DB.
	 * 
	 * @param id	id of enquiry to be removed
	 * 
	 * @return		enquiry view, 404 if id invalid
	 */
	@RequestMapping(value = "/removeEnquiry", method = RequestMethod.GET)
	public ModelAndView removeEnquiry(@RequestParam String id) {
		ModelAndView model = new ModelAndView("redirect:/enquiries");

		try {
			long enquiryId = Long.parseLong(id);
			Enquiry enquiry = enquiryDao.findOne(enquiryId);
			enquiryService.removeEnquiry(enquiry);
		} catch (NumberFormatException ex) {
			model = new ModelAndView("404");
		} catch (IllegalArgumentException e) {
			model = new ModelAndView("404");
		}
		return model;
	}

	/**
	 * Composes a list of the objects needed in the enquiry view.
	 * 
	 * @param model
	 */
	private void enquiryViewAddModelAttributes(ModelAndView model) {
		Iterable<Enquiry> newReceivedEnquiries = enquiryService.findNewReceivedEnquiries();
		Iterable<Enquiry> ratedReceivedEnquiries = enquiryService.findRatedReceivedEnquiries();
		Iterable<Enquiry> sentEnquiries = enquiryService.findSentEnquiries();
		Iterable<Enquiry> unreadEnquiries = enquiryService.findUnreadEnquiries();

		model.addObject("loggedInUser", loginService.getLoggedInUser());
		model.addObject("newReceivedEnquiries", newReceivedEnquiries);
		model.addObject("ratedReceivedEnquiries", ratedReceivedEnquiries);
		model.addObject("sentEnquiries", sentEnquiries);
		model.addObject("unreadEnquiries", unreadEnquiries);
	}

}
