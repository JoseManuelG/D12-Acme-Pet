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

import services.AttributeService;
import services.TypeService;
import controllers.AbstractController;
import domain.Attribute;
import domain.Type;

@Controller
@RequestMapping("/attribute/administrator")
public class AttributeAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	public AttributeService	attributeService;

	@Autowired
	public TypeService		typeService;


	// Constructors -----------------------------------------------------------

	public AttributeAdministratorController() {
		super();
	}

	// List ------------------------------------------------------------------		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Attribute> attributes;

		result = new ModelAndView("attribute/administrator/list");
		attributes = this.attributeService.findAll();

		result.addObject("attributes", attributes);
		result.addObject("requestURI", "attribute/administrator/list.do");

		return result;
	}

	// Edit ------------------------------------------------------------------		

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(required = false) final Integer attributeId) {
		ModelAndView result;
		Attribute attribute;

		if (attributeId != null)
			attribute = this.attributeService.findOne(attributeId);
		else
			attribute = this.attributeService.create();

		Assert.notNull(attribute);

		result = this.createEditModelAndView(attribute);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Attribute attribute, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(attribute);
		else
			try {
				this.attributeService.save(attribute);
				result = new ModelAndView("redirect:list.do");
			} catch (final IllegalArgumentException e) {
				result = this.createEditModelAndView(attribute, e.getMessage());
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Attribute attribute, final BindingResult binding) {
		ModelAndView result;

		try {
			this.attributeService.delete(attribute);
			result = new ModelAndView("redirect:list.do");
		} catch (final IllegalArgumentException e) {
			result = this.createEditModelAndView(attribute, e.getMessage());
		}

		return result;
	}

	//Ancillary methods ----------------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Attribute attribute) {
		ModelAndView result;

		result = this.createEditModelAndView(attribute, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Attribute attribute, final String message) {
		ModelAndView result;
		Collection<Type> types;

		result = new ModelAndView("attribute/administrator/edit");
		types = this.typeService.findAll();

		result.addObject("attribute", attribute);
		result.addObject("message", message);
		result.addObject("types", types);

		return result;
	}

}
