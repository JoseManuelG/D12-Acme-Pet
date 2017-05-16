
package controllers.administrator;

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
@RequestMapping("/vet/administrator")
public class VetAdministratorController extends AbstractController {

	//Services------------------------------------------------------------
	@Autowired
	private VetService	vetService;


	//Register ------------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		VetForm vetForm;

		vetForm = new VetForm();
		result = this.createRegisterModelAndView(vetForm);

		return result;
	}

	// Save ---------------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView saveNew(@Valid final VetForm vetForm, final BindingResult binding) {
		ModelAndView result;
		Vet vet;

		vet = this.vetService.reconstructNewVet(vetForm, binding);

		if (binding.hasErrors())
			result = this.createRegisterModelAndView(vetForm);
		else
			try {
				this.vetService.save(vet);
				result = new ModelAndView("redirect:/");
			} catch (final IllegalArgumentException oops) {
				result = this.createRegisterModelAndView(vetForm, oops.getMessage());
			}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createRegisterModelAndView(final VetForm vetForm) {
		ModelAndView result;

		result = this.createRegisterModelAndView(vetForm, null);

		return result;
	}

	protected ModelAndView createRegisterModelAndView(final VetForm vetForm, final String message) {
		ModelAndView result;
		String requestURI;

		requestURI = "vet/administrator/register.do";

		result = new ModelAndView("vet/register");
		result.addObject("vetForm", vetForm);
		result.addObject("isNew", true);
		result.addObject("requestURI", requestURI);
		result.addObject("message", message);

		return result;
	}
}
