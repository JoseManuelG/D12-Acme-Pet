
package controllers.vet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.PetService;
import services.VetService;
import controllers.AbstractController;
import domain.Pet;

@Controller
@RequestMapping("/pet/vet")
public class VetPetController extends AbstractController {

	@Autowired
	private VetService	vetService;

	@Autowired
	private PetService	petService;


	// Edit ---------------------------------------------------------------

	@RequestMapping(value = "/certificate", method = RequestMethod.GET)
	public ModelAndView cetificate(final int petId) {
		ModelAndView result;
		Pet pet;

		try {
			pet = this.petService.saveCertificateByVet(petId);
			result = new ModelAndView("redirect:/pet/animaniac/view.do?petId=" + pet.getId());
		} catch (final IllegalArgumentException oops) {
			pet = this.petService.findOne(petId);
			result = this.createEditModelAndView(pet, oops.getMessage());
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Pet pet) {
		ModelAndView result;

		result = this.createEditModelAndView(pet, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Pet pet, final String message) {
		ModelAndView result;

		result = new ModelAndView("pet/animaniac/view.do?petId=" + pet.getId());
		result.addObject("pet", pet);
		result.addObject("message", message);

		return result;
	}
}
