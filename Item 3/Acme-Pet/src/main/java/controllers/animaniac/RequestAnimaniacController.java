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

import services.PetService;
import services.RequestService;
import controllers.AbstractController;
import domain.Pet;
import domain.Request;

@Controller
@RequestMapping("/request/animaniac")
public class RequestAnimaniacController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	public RequestService	requestService;

	@Autowired
	public PetService		petService;


	// List ------------------------------------------------------------------		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Request> requests;

		result = new ModelAndView("request/animaniac/list");
		requests = this.requestService.findAllFromPrincipal();

		result.addObject("requests", requests);
		result.addObject("owner", true);
		result.addObject("requestURI", "request/animaniac/list.do");

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
	public ModelAndView save(@Valid final Request request, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(request);
		else
			try {
				this.requestService.save(request);
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
			result = new ModelAndView("request/animaniac/list");
			result.addObject("message", e.getMessage());
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
		Collection<Pet> pets;

		result = new ModelAndView("request/animaniac/create");
		pets = this.petService.findAvalaiblePetsFromAnimaniac();

		result.addObject("request", request);
		result.addObject("message", message);

		return result;
	}
}
