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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AnimaniacService;
import services.AttributeService;
import services.AttributeValueService;
import services.PetService;
import services.PhotoService;
import services.RequestService;
import services.TypeService;
import controllers.AbstractController;
import domain.AttributeValue;
import domain.Pet;
import domain.Photo;
import forms.PetForm;

@Controller
@RequestMapping("/pet/animaniac")
public class AnimaniacPetController extends AbstractController { //TODO el nombre es al reves

	// Services ---------------------------------------------------------------

	@Autowired
	public PetService				petService;

	@Autowired
	private PhotoService			photoService;

	@Autowired
	private TypeService				typeService;

	@Autowired
	public AttributeValueService	attributeValueService;

	@Autowired
	public AttributeService			attributeService;

	@Autowired
	public AnimaniacService			animaniacService;

	@Autowired
	public RequestService			requestService;


	// Constructors -----------------------------------------------------------

	public AnimaniacPetController() {
		super();
	}

	// List ------------------------------------------------------------------		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int animaniacId) {
		final ModelAndView result;
		Boolean owner = false;
		result = new ModelAndView("pet/animaniac/list");

		final Collection<Pet> pets = this.petService.findPetsByAnimaniac(animaniacId);
		owner = this.animaniacService.findOne(animaniacId).equals(this.animaniacService.findAnimaniacByPrincipal());
		result.addObject("pets", pets);
		result.addObject("owner", owner);
		result.addObject("requestURI", "pet/animaniac/list.do?animaniacId=" + animaniacId);

		return result;
	}

	// Create ------------------------------------------------------------------		

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(final int typeId) {
		ModelAndView result;
		final PetForm petForm = new PetForm();
		final Collection<AttributeValue> attributeValues = this.attributeService.attributeValuesFromType(typeId, this.petService.create(typeId));
		petForm.getAttributeValues().addAll(attributeValues);
		petForm.fillAttributes(attributeValues);
		petForm.setType(this.typeService.findOne(typeId));
		result = this.createEditModelAndView(petForm);
		return result;
	}
	// Edit ------------------------------------------------------------------		

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(final int petId) {
		ModelAndView result;
		final Pet pet = this.petService.findOne(petId);
		final PetForm petForm = new PetForm(pet);
		final Collection<AttributeValue> attributeValues = this.attributeValueService.findAttributeValuesOfPet(pet);
		petForm.getAttributeValues().addAll(attributeValues);
		petForm.fillAttributes(attributeValues);
		petForm.getPhotos().addAll(this.photoService.findPhotosOfPet(pet));
		result = this.createEditModelAndView(petForm);
		return result;
	}

	// Save -------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView write(final PetForm petForm, final BindingResult bindingResult) {
		ModelAndView result;
		Pet pet;
		String error;
		if (petForm.getId() == -1)
			pet = this.petService.reconstruct(petForm, bindingResult);
		else
			pet = this.petService.reconstruct2(petForm, petForm.getId(), bindingResult);

		if (bindingResult.hasErrors()) {
			error = null;
			if (bindingResult.hasFieldErrors("url"))
				error = "pet.url.error";
			result = this.createEditModelAndView(petForm, error);
		} else
			try {
				if (petForm.getId() != 0)
					this.requestService.checkRequests(petForm.getId());
				final Pet savedPet = this.petService.save(pet, petForm.getAttributeValues(), petForm.getPhotos());
				result = this.view(savedPet.getId());
			} catch (final IllegalArgumentException e) {
				result = this.createEditModelAndView(petForm, e.getMessage());
			}

		return result;
	}

	// Delete ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final PetForm petForm) {
		ModelAndView result;

		try {
			this.petService.delete(this.petService.findOne(petForm.getId()));
			result = this.list(this.animaniacService.findAnimaniacByPrincipal().getId());

		} catch (final IllegalArgumentException e) {
			result = this.createEditModelAndView(petForm, e.getMessage());
		}

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "addPhoto")
	public ModelAndView addAttachment(final PetForm petForm) {
		ModelAndView result;

		petForm.addPhotoSpace();

		result = this.createEditModelAndView(petForm);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "removePhoto")
	public ModelAndView removeAttachment(final PetForm petForm) {
		ModelAndView result;

		petForm.removePhotoSpace();

		result = this.createEditModelAndView(petForm);

		return result;
	}

	// View ------------------------------------------------------------------		

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam final int petId) {
		ModelAndView result;
		Pet res;
		Collection<AttributeValue> attributeValues;
		final Collection<Photo> att;
		Boolean owner = false;

		res = this.petService.findOne(petId);
		owner = res.getAnimaniac().equals(this.animaniacService.findAnimaniacByPrincipal());
		att = this.photoService.findPhotosOfPet(res);

		attributeValues = this.attributeValueService.findAttributeValuesOfPet(res);

		result = new ModelAndView("pet/animaniac/view");
		result.addObject("pet", res);
		result.addObject("type", res.getType());
		result.addObject("owner", owner);
		result.addObject("attributeValues", attributeValues);
		result.addObject("photos", att);
		result.addObject("requestURI", "pet/animaniac/view.do?petId=" + petId);

		return result;
	}
	//Ancillary methods ----------------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final PetForm petForm) {
		ModelAndView result;

		result = this.createEditModelAndView(petForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final PetForm petForm, final String message) {
		ModelAndView result;
		result = new ModelAndView("pet/animaniac/edit");
		result.addObject("petForm", petForm);
		result.addObject("message", message);

		return result;
	}

}
