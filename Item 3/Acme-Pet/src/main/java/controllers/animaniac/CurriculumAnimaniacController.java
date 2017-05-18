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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculumService;
import controllers.AbstractController;
import domain.Curriculum;

@Controller
@RequestMapping("/curriculum/animaniac")
public class CurriculumAnimaniacController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	public CurriculumService	curriculumService;


	// Constructors -----------------------------------------------------------

	public CurriculumAnimaniacController() {
		super();
	}

	// Edit ------------------------------------------------------------------		

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Curriculum curriculum;

		curriculum = this.curriculumService.findCurriculumByPrincipal();
		if (curriculum == null)
			curriculum = this.curriculumService.create();

		result = this.createEditModelAndView(curriculum);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Curriculum curriculum, final BindingResult binding) {
		ModelAndView result;
		Curriculum res;

		res = this.curriculumService.reconstruct(curriculum, binding);

		if (binding.hasErrors())
			result = this.createEditModelAndView(curriculum);
		else
			try {
				this.curriculumService.save(res);
				result = new ModelAndView("redirect:/actor/myProfile.do");
			} catch (final IllegalArgumentException e) {
				result = this.createEditModelAndView(curriculum, e.getMessage());
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Curriculum curriculum, final BindingResult binding) {
		ModelAndView result;

		try {
			this.curriculumService.deletePrincipal();
			result = new ModelAndView("redirect:/actor/myProfile.do");
		} catch (final IllegalArgumentException e) {
			result = this.createEditModelAndView(curriculum, e.getMessage());
		}

		return result;
	}

	//Ancillary methods ----------------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Curriculum curriculum) {
		ModelAndView result;

		result = this.createEditModelAndView(curriculum, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Curriculum curriculum, final String message) {
		ModelAndView result;

		result = new ModelAndView("curriculum/animaniac/edit");

		result.addObject("curriculum", curriculum);
		result.addObject("message", message);

		return result;
	}

}
