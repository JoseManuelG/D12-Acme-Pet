
package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AnimaniacService;
import controllers.AbstractController;
import controllers.AnimaniacController;

@Controller
@RequestMapping("/animaniac/administrator")
public class AnimaniacAdministratorController extends AbstractController {

	@Autowired
	private AnimaniacService	animaniacService;
	@Autowired
	private AnimaniacController	animaniacController;


	// Ban 	-------------------------------------------------------------------
	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public ModelAndView ban(@RequestParam final int animaniacId, @RequestParam(required = false) final Integer reported) {
		ModelAndView result;

		this.animaniacService.ban(animaniacId);

		if (reported != null)
			result = new ModelAndView("redirect:/abuseReport/administrator/list.do");
		else
			result = new ModelAndView("redirect:/animaniac/list.do");

		return result;
	}

	// Unban 	-------------------------------------------------------------------
	@RequestMapping(value = "/unban", method = RequestMethod.GET)
	public ModelAndView unban(@RequestParam final int animaniacId, @RequestParam(required = false) final Integer reported) {
		ModelAndView result;

		this.animaniacService.unban(animaniacId);

		if (reported != null)
			result = new ModelAndView("redirect:/abuseReport/administrator/list.do");
		else
			result = new ModelAndView("redirect:/animaniac/list.do");
		return result;
	}
	// delete 	-------------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteBanned(@RequestParam final int animaniacId) {
		ModelAndView result;
		result = this.animaniacController.list();
		try {
			this.animaniacService.deleteBanned(animaniacId);

		} catch (final Exception e) {
			result.addObject("message", e.getMessage());
		}

		return result;
	}

}
