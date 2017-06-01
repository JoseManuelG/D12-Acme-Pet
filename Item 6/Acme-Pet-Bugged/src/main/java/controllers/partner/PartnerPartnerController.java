
package controllers.partner;

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
@RequestMapping("/partner/partner")
public class PartnerPartnerController extends AbstractController {

	@Autowired
	private PartnerService	partnerService;


	// Edit ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Partner partner;
		PartnerForm partnerForm;

		partner = this.partnerService.findPartnerByPrincipal();

		partnerForm = new PartnerForm(partner);

		result = this.createEditModelAndView(partnerForm);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final PartnerForm partnerForm, final BindingResult binding) {
		ModelAndView result;
		Partner principal, partnerResult;

		principal = this.partnerService.findPartnerByPrincipal();

		partnerResult = this.partnerService.reconstruct(partnerForm, principal, binding);

		if (binding.hasErrors())
			result = this.createEditModelAndView(partnerForm);
		else
			try {
				this.partnerService.save(partnerResult);
				result = new ModelAndView("redirect:/actor/myProfile.do");
			} catch (final IllegalArgumentException oops) {
				result = this.createEditModelAndView(partnerForm, oops.getMessage());
			}

		return result;
	}

	// Delete ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final PartnerForm partnerForm) {
		ModelAndView result;

		try {
			this.partnerService.delete();
			result = new ModelAndView("redirect:/j_spring_security_logout");

		} catch (final IllegalArgumentException e) {
			result = this.createEditModelAndView(partnerForm, e.getMessage());
		}

		return result;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final PartnerForm partnerForm) {
		ModelAndView result;

		result = this.createEditModelAndView(partnerForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final PartnerForm partnerForm, final String message) {
		ModelAndView result;
		String requestURI;

		requestURI = "partner/partner/edit.do";

		result = new ModelAndView("partner/edit");
		result.addObject("partnerForm", partnerForm);
		result.addObject("isNew", false);
		result.addObject("requestURI", requestURI);
		result.addObject("message", message);

		return result;
	}
}
