/*
 * WelcomeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.administrator;

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

import services.TypeService;
import controllers.AbstractController;
import domain.Type;

@Controller
@RequestMapping("/type/administrator")
public class TypeAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	public TypeService	typeService;


	// Constructors -----------------------------------------------------------

	public TypeAdministratorController() {
		super();
	}

	// List ------------------------------------------------------------------		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<Type> types;

		result = new ModelAndView("type/administrator/list");
		types = this.typeService.findAll();

		result.addObject("types", types);
		result.addObject("requestURI", "type/administrator/list.do");

		return result;
	}

	// Edit ------------------------------------------------------------------		

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(required = false) final Integer typeId) {
		ModelAndView result;
		final Type type;

		if (typeId != null)
			type = this.typeService.findOne(typeId);
		else
			type = this.typeService.create();

		Assert.notNull(type);

		result = this.createEditModelAndView(type);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Type type, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(type);
		else
			try {
				this.typeService.save(type);
				result = new ModelAndView("redirect:list.do");
			} catch (final IllegalArgumentException e) {
				result = this.createEditModelAndView(type, e.getMessage());
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Type type, final BindingResult binding) {
		ModelAndView result;

		try {
			this.typeService.delete(type);
			result = new ModelAndView("redirect:list.do");
		} catch (final IllegalArgumentException e) {
			result = this.createEditModelAndView(type, e.getMessage());
		}

		return result;
	}

	//Ancillary methods ----------------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Type type) {
		ModelAndView result;

		result = this.createEditModelAndView(type, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Type type, final String message) {
		ModelAndView result;

		result = new ModelAndView("type/administrator/edit");

		result.addObject("type", type);
		result.addObject("message", message);

		return result;
	}

}
