
package controllers.vet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.PetService;
import services.VetService;
import controllers.AbstractController;
import domain.Pet;
import domain.Vet;

@Controller
@RequestMapping("/pet/vet")
public class VetPetController extends AbstractController {

	@Autowired
	private VetService	vetService;

	@Autowired
	private PetService	petService;


	// Edit ---------------------------------------------------------------

	@RequestMapping(value = "/certificate", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Pet pet, final BindingResult binding) {
		ModelAndView result;
		Vet principal;

		principal = this.vetService.findVetByPrincipal();
		pet.setCertificateBy(principal.getName() + "," + principal.getSurname());

		if (binding.hasErrors())
			result = this.createEditModelAndView(pet);
		else
			try {
				this.petService.save(pet);
				result = new ModelAndView("redirect:/pet/view.do?petId=" + pet.getId());
			} catch (final IllegalArgumentException oops) {
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
		String requestURI;

		requestURI = "pet/vet/edit.do";

		result = new ModelAndView("pet/view.do?petId=" + pet.getId());
		result.addObject("pet", pet);
		result.addObject("requestURI", requestURI);
		result.addObject("message", message);

		return result;
	}
}
