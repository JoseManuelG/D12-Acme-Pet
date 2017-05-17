
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AnimaniacService;
import controllers.AbstractController;

@Controller
@RequestMapping("/animaniac/administrator")
public class AnimaniacAdministratorController extends AbstractController {

	@Autowired
	private AnimaniacService	animaniacService;


	// Ban 	-------------------------------------------------------------------
	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public ModelAndView ban(@RequestParam final int animaniacId) {
		ModelAndView result;

		this.animaniacService.ban(animaniacId);

		result = new ModelAndView("redirect:/animaniac/list.do");

		return result;
	}

	// Unban 	-------------------------------------------------------------------
	@RequestMapping(value = "/unban", method = RequestMethod.GET)
	public ModelAndView unban(@RequestParam final int animaniacId) {
		ModelAndView result;

		this.animaniacService.unban(animaniacId);
		result = new ModelAndView("redirect:/animaniac/list.do");

		return result;
	}

}
