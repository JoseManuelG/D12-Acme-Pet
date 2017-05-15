/*
 * WelcomeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.Animaniac;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.PetService;
import controllers.AbstractController;
import domain.Pet;

@Controller
@RequestMapping("/pet/animaniac")
public class AnimaniacPetController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	public PetService	petService;


	// Constructors -----------------------------------------------------------

	public AnimaniacPetController() {
		super();
	}

	// List ------------------------------------------------------------------		

	@RequestMapping(value = "/list")
	public ModelAndView list() {
		final ModelAndView result;
		result = new ModelAndView("pet/animaniac/list");

		//Esto no creo que le mole demasiado a corchu por lo pronto lo dejo asi
		final Collection<Pet> pets = this.petService.findAll();
		result.addObject("pets", pets);
		return result;
	}

	// Create ------------------------------------------------------------------		

	@RequestMapping(value = "/edit")
	public ModelAndView create() {
		ModelAndView result;
		final Pet pet = this.petService.create();
		result = this.createEditModelAndView(pet);
		return result;
	}

	// Edit ------------------------------------------------------------------		

	@RequestMapping(value = "/edit")
	public ModelAndView edit() {
		ModelAndView result;
		result = this.createEditModelAndView(pet);
		return result;
	}

	//Ancillary methods ----------------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Pet pet) {
		ModelAndView result;

		result = this.createEditModelAndView(pet, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Pet pet, final String message) {
		ModelAndView result;
		result = new ModelAndView("pet/animaniac/edit");
		result.addObject("pet", pet);
		result.addObject("message", message);

		return result;
	}

}
