
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import domain.Actor;
import domain.Animaniac;
import domain.Partner;
import domain.Vet;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

	//Services------------------------------------------------------------

	@Autowired
	private ActorService	actorService;


	//View------------------------------------------------------------

	@RequestMapping(value = "/myProfile", method = RequestMethod.GET)
	public ModelAndView myProfile() {
		ModelAndView result;
		Actor principal;

		principal = this.actorService.findActorByPrincipal();

		if (principal instanceof Animaniac)
			result = new ModelAndView("redirect:/animaniac/view.do?animaniacId=" + principal.getId());
		else if (principal instanceof Partner)
			result = new ModelAndView("redirect:/partner/view.do?animaniacId=" + principal.getId());
		else if (principal instanceof Vet)
			result = new ModelAndView("redirect:/vet/view.do?animaniacId=" + principal.getId());
		else
			result = new ModelAndView("redirect:/administrator/view.do?animaniacId=" + principal.getId());

		return result;
	}
}
