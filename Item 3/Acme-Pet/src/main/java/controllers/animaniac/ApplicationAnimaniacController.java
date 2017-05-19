/*
 * WelcomeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.animaniac;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import controllers.AbstractController;
import domain.Application;

@Controller
@RequestMapping("/application/animaniac")
public class ApplicationAnimaniacController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ApplicationService	applicationService;


	// List ------------------------------------------------------------------		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final Integer requestId) {
		ModelAndView result;
		Collection<Application> applications;
		Boolean requestOwner;

		result = new ModelAndView("application/animaniac/list");

		if (requestId == null) {
			applications = this.applicationService.findAllFromPrincipal();
			requestOwner = false;
		} else {
			applications = this.applicationService.findFromRequest(requestId);
			requestOwner = this.applicationService.checkRequestOwner(requestId);
		}

		result.addObject("applications", applications);
		result.addObject("requestOwner", requestOwner);
		result.addObject("requestURI", "application/animaniac/list.do");

		return result;
	}

	// Create ------------------------------------------------------------------		

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(final int requestId) {
		ModelAndView result;
		Application application;

		application = this.applicationService.create(requestId);

		Assert.notNull(application);

		result = this.createEditModelAndView(application);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Application application, final BindingResult binding) {
		ModelAndView result;
		Application applicationResult;

		applicationResult = this.applicationService.reconstruct(application, binding);
		if (binding.hasErrors())
			result = this.createEditModelAndView(application);
		else
			try {
				this.applicationService.save(applicationResult);
				result = new ModelAndView("redirect:list.do");
			} catch (final IllegalArgumentException e) {
				result = this.createEditModelAndView(application, e.getMessage());
			}

		return result;
	}

	// Accept / Deny ------------------------------------------------------------------		

	@RequestMapping(value = "/accept", method = RequestMethod.GET)
	public ModelAndView accept(@RequestParam final int applicationId) {
		ModelAndView result;
		Application application;

		application = this.applicationService.findOne(applicationId);

		this.applicationService.accept(applicationId);

		result = new ModelAndView("redirect:list.do?requestId=" + application.getRequest().getId());

		return result;
	}

	@RequestMapping(value = "/deny", method = RequestMethod.GET)
	public ModelAndView deny(@RequestParam final int applicationId) {
		ModelAndView result;
		Application application;

		application = this.applicationService.findOne(applicationId);

		this.applicationService.deny(applicationId);

		result = new ModelAndView("redirect:list.do?requestId=" + application.getRequest().getId());

		return result;
	}

	//Ancillary methods ----------------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Application application) {
		ModelAndView result;

		result = this.createEditModelAndView(application, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Application application, final String message) {
		ModelAndView result;

		result = new ModelAndView("application/animaniac/create");
		result.addObject("application", application);
		result.addObject("message", message);

		return result;
	}

}
