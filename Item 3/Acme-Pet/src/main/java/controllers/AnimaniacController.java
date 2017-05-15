
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AnimaniacService;
import domain.Animaniac;
import forms.AnimaniacForm;

@Controller
@RequestMapping("/animaniac")
public class AnimaniacController extends AbstractController {

	//Services------------------------------------------------------------
	@Autowired
	private AnimaniacService	animaniacService;


	//Register ------------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		AnimaniacForm animaniacForm;

		animaniacForm = new AnimaniacForm();
		result = this.createRegisterModelAndView(animaniacForm);

		return result;
	}

	// Save ---------------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final AnimaniacForm animaniacForm, final BindingResult binding) {
		ModelAndView result;
		Animaniac animaniac;

		animaniac = this.animaniacService.reconstructNewAnimaniac(animaniacForm, binding);

		if (binding.hasErrors())
			result = this.createRegisterModelAndView(animaniacForm);
		else
			try {
				this.animaniacService.save(animaniac);
				result = new ModelAndView("redirect:/");
			} catch (final IllegalArgumentException oops) {
				result = this.createRegisterModelAndView(animaniacForm, oops.getMessage());
			}

		return result;
	}

	// Edit ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Animaniac animaniac;
		AnimaniacForm animaniacForm;

		animaniac = this.animaniacService.findAnimaniacByPrincipal();

		animaniacForm = new AnimaniacForm(animaniac);

		result = this.createEditModelAndView(animaniacForm);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid final AnimaniacForm animaniacForm, final BindingResult binding) {
		ModelAndView result;
		Animaniac principal, animaniacResult;

		principal = this.animaniacService.findAnimaniacByPrincipal();

		animaniacResult = this.animaniacService.reconstruct(animaniacForm, principal, binding);

		if (binding.hasErrors())
			result = this.createEditModelAndView(animaniacForm);
		else
			try {
				this.animaniacService.save(animaniacResult);
				result = new ModelAndView("redirect:/animaniac/animaniac/myProfile.do");
			} catch (final IllegalArgumentException oops) {
				result = this.createEditModelAndView(animaniacForm, oops.getMessage());
			}

		return result;
	}

	// Delete ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final AnimaniacForm animaniacForm) {
		ModelAndView result;

		try {
			this.animaniacService.delete();
			result = new ModelAndView("redirect:/j_spring_security_logout");

		} catch (final IllegalArgumentException e) {
			result = this.createEditModelAndView(animaniacForm, e.getMessage());
		}

		return result;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createRegisterModelAndView(final AnimaniacForm animaniacForm) {
		ModelAndView result;

		result = this.createRegisterModelAndView(animaniacForm, null);

		return result;
	}

	protected ModelAndView createRegisterModelAndView(final AnimaniacForm animaniacForm, final String message) {
		ModelAndView result;
		String requestURI;

		requestURI = "animaniac/register.do";

		result = new ModelAndView("animaniac/register");
		result.addObject("animaniacForm", animaniacForm);
		result.addObject("isNew", true);
		result.addObject("requestURI", requestURI);
		result.addObject("message", message);

		return result;
	}
	protected ModelAndView createEditModelAndView(final AnimaniacForm animaniacForm) {
		ModelAndView result;

		result = this.createEditModelAndView(animaniacForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final AnimaniacForm animaniacForm, final String message) {
		ModelAndView result;
		String requestURI;

		requestURI = "animaniac/edit.do";

		result = new ModelAndView("animaniac/edit");
		result.addObject("animaniacForm", animaniacForm);
		result.addObject("isNew", false);
		result.addObject("requestURI", requestURI);
		result.addObject("message", message);

		return result;
	}
}
