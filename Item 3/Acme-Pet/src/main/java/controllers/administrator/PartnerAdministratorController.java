
package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.PartnerService;
import controllers.AbstractController;
import domain.Partner;
import forms.PartnerForm;

@Controller
@RequestMapping("/partner/administrator")
public class PartnerAdministratorController extends AbstractController {

	//Services------------------------------------------------------------
	@Autowired
	private PartnerService	partnerService;


	//Register ------------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		PartnerForm partnerForm;

		partnerForm = new PartnerForm();
		result = this.createRegisterModelAndView(partnerForm);

		return result;
	}

	// Save ---------------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView saveNew(@Valid final PartnerForm partnerForm, final BindingResult binding) {
		ModelAndView result;
		Partner partner;

		partner = this.partnerService.reconstructNewPartner(partnerForm, binding);

		if (binding.hasErrors())
			result = this.createRegisterModelAndView(partnerForm);
		else
			try {
				this.partnerService.save(partner);
				result = new ModelAndView("redirect:/");
			} catch (final IllegalArgumentException oops) {
				result = this.createRegisterModelAndView(partnerForm, oops.getMessage());
			}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createRegisterModelAndView(final PartnerForm partnerForm) {
		ModelAndView result;

		result = this.createRegisterModelAndView(partnerForm, null);

		return result;
	}

	protected ModelAndView createRegisterModelAndView(final PartnerForm partnerForm, final String message) {
		ModelAndView result;
		String requestURI;

		requestURI = "partner/administrator/register.do";

		result = new ModelAndView("partner/register");
		result.addObject("partnerForm", partnerForm);
		result.addObject("isNew", true);
		result.addObject("requestURI", requestURI);
		result.addObject("message", message);

		return result;
	}
}
