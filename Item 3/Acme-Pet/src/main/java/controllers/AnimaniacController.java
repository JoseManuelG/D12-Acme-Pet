
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AnimaniacService;
import domain.Actor;
import domain.Animaniac;
import forms.AnimaniacForm;

@Controller
@RequestMapping("/animaniac")
public class AnimaniacController extends AbstractController {

	//Services------------------------------------------------------------
	@Autowired
	private AnimaniacService	animaniacService;

	@Autowired
	private ActorService		actorService;


	//View------------------------------------------------------------

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam final int animaniacId) {
		ModelAndView result;
		Animaniac animaniac;
		Actor principal;
		Boolean owner;

		principal = this.actorService.findActorByPrincipal();
		animaniac = this.animaniacService.findOne(animaniacId);
		owner = animaniac.equals(principal);

		result = new ModelAndView("animaniac/view");
		result.addObject("animaniac", animaniac);
		result.addObject("owner", owner);

		result.addObject("requestURI", "animaniac/view.do?animaniacId=" + animaniacId);

		return result;
	}

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
	public ModelAndView saveNew(@Valid final AnimaniacForm animaniacForm, final BindingResult binding) {
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
}
