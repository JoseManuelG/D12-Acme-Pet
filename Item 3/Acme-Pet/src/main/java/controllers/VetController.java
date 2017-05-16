
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.VetService;
import domain.Actor;
import domain.Vet;

@Controller
@RequestMapping("/vet")
public class VetController extends AbstractController {

	//Services------------------------------------------------------------
	@Autowired
	private VetService		vetService;

	@Autowired
	private ActorService	actorService;


	//View------------------------------------------------------------

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam final int vetId) {
		ModelAndView result;
		Vet vet;
		Actor principal;
		Boolean owner;

		principal = this.actorService.findActorByPrincipal();
		vet = this.vetService.findOne(vetId);
		owner = vet.equals(principal);

		result = new ModelAndView("vet/view");
		result.addObject("vet", vet);
		result.addObject("owner", owner);

		result.addObject("requestURI", "vet/view.do?vetId=" + vetId);

		return result;
	}
}
