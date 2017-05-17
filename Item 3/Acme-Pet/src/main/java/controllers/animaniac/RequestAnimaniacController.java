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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.RequestService;
import controllers.AbstractController;
import domain.Request;

@Controller
@RequestMapping("/request/animaniac")
public class RequestAnimaniacController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	public RequestService	requestService;


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
}
