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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AnimaniacService;
import services.PetService;
import services.RequestService;
import controllers.AbstractController;
import domain.Animaniac;
import domain.Pet;
import domain.Request;
import forms.RateForm;

@Controller
@RequestMapping("/request/animaniac")
public class RequestAnimaniacController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private AnimaniacService	animaniacService;

	@Autowired
	private RequestService		requestService;

	@Autowired
	private PetService			petService;


	// List ------------------------------------------------------------------		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		result = this.createListModelAndView();

		return result;
	}

	// Edit ------------------------------------------------------------------		

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Request request;

		request = this.requestService.create();

		Assert.notNull(request);

		result = this.createEditModelAndView(request);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Request request, final BindingResult binding) {
		ModelAndView result;
		Request requestResult;

		requestResult = this.requestService.reconstruct(request, binding);
		if (binding.hasErrors())
			result = this.createEditModelAndView(request);
		else
			try {
				this.requestService.save(requestResult);
				result = new ModelAndView("redirect:list.do");
			} catch (final IllegalArgumentException e) {
				result = this.createEditModelAndView(request, e.getMessage());
			}

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int requestId) {
		ModelAndView result;

		try {
			this.requestService.delete(requestId);
			result = new ModelAndView("redirect:list.do");
		} catch (final IllegalArgumentException e) {
			result = this.createListModelAndView(e.getMessage());
		}

		return result;
	}

	@RequestMapping(value = "/rate", method = RequestMethod.GET)
	public ModelAndView rate(@RequestParam final int requestId) {
		ModelAndView result;
		RateForm rateForm;

		try {
			this.requestService.checkRateable(requestId);
			rateForm = new RateForm(requestId);
			result = this.createRateModelAndView(rateForm);
		} catch (final IllegalArgumentException e) {
			result = this.createListModelAndView(e.getMessage());
		}

		return result;
	}

	@RequestMapping(value = "/rate", method = RequestMethod.POST, params = "save")
	public ModelAndView saveRate(@Valid final RateForm rateForm, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createRateModelAndView(rateForm);
		else
			try {
				this.requestService.rate(rateForm);
				result = new ModelAndView("redirect:list.do");
			} catch (final IllegalArgumentException e) {
				result = this.createRateModelAndView(rateForm, e.getMessage());
			}

		return result;
	}

	//Ancillary methods ----------------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Request request) {
		ModelAndView result;

		result = this.createEditModelAndView(request, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Request request, final String message) {
		ModelAndView result;
		Collection<Pet> availablePets;

		try {
			availablePets = this.petService.findAvalaiblePetsFromPrincipal();

			result = new ModelAndView("request/animaniac/create");
			result.addObject("availablePets", availablePets);
			result.addObject("request", request);
			result.addObject("message", message);

		} catch (final IllegalArgumentException e) {
			result = this.createListModelAndView(e.getMessage());
		}

		return result;
	}

	protected ModelAndView createListModelAndView() {
		ModelAndView result;

		result = this.createListModelAndView(null);

		return result;
	}

	protected ModelAndView createListModelAndView(final String message) {
		ModelAndView result;
		Collection<Request> requests;
		Animaniac principal;

		result = new ModelAndView("request/animaniac/list");
		requests = this.requestService.findAllFromPrincipal();
		principal = this.animaniacService.findAnimaniacByPrincipal();

		result.addObject("requests", requests);
		result.addObject("principal", principal);
		result.addObject("message", message);
		result.addObject("requestURI", "request/animaniac/list.do");

		return result;
	}

	protected ModelAndView createRateModelAndView(final RateForm rateForm) {
		ModelAndView result;

		result = this.createRateModelAndView(rateForm, null);

		return result;
	}

	protected ModelAndView createRateModelAndView(final RateForm rateForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("request/animaniac/rate");
		result.addObject("rateForm", rateForm);
		result.addObject("message", message);

		return result;
	}
}
