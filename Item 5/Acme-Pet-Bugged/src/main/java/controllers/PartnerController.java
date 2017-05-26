
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.PartnerService;
import domain.Actor;
import domain.Partner;

@Controller
@RequestMapping("/partner")
public class PartnerController extends AbstractController {

	//Services------------------------------------------------------------
	@Autowired
	private PartnerService	partnerService;

	@Autowired
	private ActorService	actorService;


	//List------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Partner> partners;

		partners = this.partnerService.findAll();

		result = new ModelAndView("partner/list");
		result.addObject("partners", partners);
		result.addObject("requestURI", "partner/list.do");

		return result;
	}

	//View------------------------------------------------------------

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam final int partnerId) {
		ModelAndView result;
		Partner partner;
		Actor principal;
		Boolean owner;

		principal = this.actorService.findActorByPrincipal();
		partner = this.partnerService.findOne(partnerId);
		owner = partner.equals(principal);

		result = new ModelAndView("partner/view");
		result.addObject("partner", partner);
		result.addObject("owner", owner);

		result.addObject("requestURI", "partner/view.do?partnerId=" + partnerId);

		return result;
	}
}
