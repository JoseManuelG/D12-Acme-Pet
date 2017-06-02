/*
 * WelcomeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.TypeService;
import domain.Type;

@Controller
@RequestMapping("/type/animaniac")
public class TypeController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	public TypeService	typeService;


	// Constructors -----------------------------------------------------------

	public TypeController() {
		super();
	}

	// Select ------------------------------------------------------------------		

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public ModelAndView select() {
		ModelAndView result;
		final Collection<Type> types = this.typeService.findAll();
		result = new ModelAndView("type/select");
		result.addObject("types", types);
		result.addObject("requestURI", "type/animaniac/select.do");
		return result;
	}
}
