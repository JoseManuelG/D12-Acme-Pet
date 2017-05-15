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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AttributeValueService;
import services.PetService;
import controllers.AbstractController;
import domain.AttributeValue;
import domain.Pet;
import domain.Photo;

@Controller
@RequestMapping("/pet/animaniac")
public class AnimaniacPetController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	public PetService				petService;

	@Autowired
	public AttributeValueService	attributeValueService;


	// Constructors -----------------------------------------------------------

	public AnimaniacPetController() {
		super();
	}

	// List ------------------------------------------------------------------		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		result = new ModelAndView("pet/list");

		//Esto no creo que le mole demasiado a corchu por lo pronto lo dejo asi
		final Collection<Pet> pets = this.petService.findAll();
		result.addObject("pets", pets);
		return result;
	}

	// Create ------------------------------------------------------------------		

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(final int typeId) {
		ModelAndView result;
		final Pet pet = this.petService.create(typeId);
		result = this.createEditModelAndView(pet);
		return result;
	}

	// Edit ------------------------------------------------------------------		

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(final int petId) {
		ModelAndView result;
		final Pet pet = this.petService.findOne(petId);
		result = this.createEditModelAndView(pet);
		return result;
	}

	// View ------------------------------------------------------------------		

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam final int petId) {
		ModelAndView result;
		Pet res;
		Collection<AttributeValue> attributeValues;
		Collection<Photo> att;

		res = this.petService.findOne(petId);
		att = ;//TODO
		
		attributeValues = this.attributeValueService.findAttributeValuesOfPet(res);

		result = new ModelAndView("pet/view");
		result.addObject("res", res);
		result.addObject("attributeValues", attributeValues);
		result.addObject("requestURI", "pet/animaniac/view.do?petId=" + petId);

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
		result = new ModelAndView("pet/edit");
		result.addObject("pet", pet);
		result.addObject("message", message);

		return result;
	}

}
