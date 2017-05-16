
package controllers.vet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.VetService;
import controllers.AbstractController;
import domain.Vet;
import forms.VetForm;

@Controller
@RequestMapping("/vet/vet")
public class VetVetController extends AbstractController {

	@Autowired
	private VetService	vetService;


	// Edit ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Vet vet;
		VetForm vetForm;

		vet = this.vetService.findVetByPrincipal();

		vetForm = new VetForm(vet);

		result = this.createEditModelAndView(vetForm);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final VetForm vetForm, final BindingResult binding) {
		ModelAndView result;
		Vet principal, vetResult;

		principal = this.vetService.findVetByPrincipal();

		vetResult = this.vetService.reconstruct(vetForm, principal, binding);

		if (binding.hasErrors())
			result = this.createEditModelAndView(vetForm);
		else
			try {
				this.vetService.save(vetResult);
				result = new ModelAndView("redirect:/actor/myProfile.do");
			} catch (final IllegalArgumentException oops) {
				result = this.createEditModelAndView(vetForm, oops.getMessage());
			}

		return result;
	}

	// Delete ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final VetForm vetForm) {
		ModelAndView result;

		try {
			this.vetService.delete();
			result = new ModelAndView("redirect:/j_spring_security_logout");

		} catch (final IllegalArgumentException e) {
			result = this.createEditModelAndView(vetForm, e.getMessage());
		}

		return result;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final VetForm vetForm) {
		ModelAndView result;

		result = this.createEditModelAndView(vetForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final VetForm vetForm, final String message) {
		ModelAndView result;
		String requestURI;

		requestURI = "vet/vet/edit.do";

		result = new ModelAndView("vet/edit");
		result.addObject("vetForm", vetForm);
		result.addObject("isNew", false);
		result.addObject("requestURI", requestURI);
		result.addObject("message", message);

		return result;
	}
}
