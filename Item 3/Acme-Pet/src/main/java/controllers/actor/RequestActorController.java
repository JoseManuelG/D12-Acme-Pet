/*
 * WelcomeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.actor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.RequestService;
import controllers.AbstractController;
import domain.Request;

@Controller
@RequestMapping("/request/actor")
public class RequestActorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	public RequestService	requestService;


	// List ------------------------------------------------------------------		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Request> requests;

		result = new ModelAndView("request/actor/list");
		requests = this.requestService.findAll();

		result.addObject("requests", requests);
		result.addObject("requestURI", "request/actor/list.do");

		return result;
	}
}
