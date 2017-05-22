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

import services.PetService;
import domain.Pet;

@Controller
@RequestMapping("/pet")
public class PetController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	public PetService	petService;


	// Constructors -----------------------------------------------------------

	public PetController() {
		super();
	}

	// Select ------------------------------------------------------------------		

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		final Collection<Pet> pets = this.petService.findAll();
		result = new ModelAndView("pet/listAll");
		result.addObject("pets", pets);
		result.addObject("requestURI", "pet/listAll.do");
		return result;
	}
}
