
package controllers.partner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import services.BannerService;
import services.PartnerService;
import controllers.AbstractController;
import domain.Banner;
import domain.Partner;

@Controller
@RequestMapping("/banner/partner")
public class BannerPartnerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private BannerService	bannerService;

	@Autowired
	private PartnerService	partnerService;


	// Constructors -----------------------------------------------------------

	public BannerPartnerController() {
		super();
	}

	// View ---------------------------------------------------------------

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam(required = true) final Integer bannerId) {
		ModelAndView result;

		result = new ModelAndView("banner/partner/view");
		final Banner banner = this.bannerService.findOne(bannerId);

		result.addObject("banner", banner);
		result.addObject("requestURI", "banner/partner/view.do?bannerId=" + banner.getId());
		return result;
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Banner> banners;
		Partner principal;

		principal = this.partnerService.findPartnerByPrincipal();

		banners = this.bannerService.findBannerByPartner(principal.getId());

		result = new ModelAndView("banner/partner/list");
		result.addObject("banners", banners);
		result.addObject("requestURI", "banner/partner/list.do");

		return result;
	}

	// Create ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Banner banner;

		banner = this.bannerService.create();
		result = this.createEditModelAndView(banner);

		return result;

	}

	// Edit -----------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(final int bannerId) {
		final Banner banner = this.bannerService.findOne(bannerId);
		final ModelAndView result = this.createEditModelAndView(banner);
		return result;

	}

	// Save ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Banner originalBanner, final BindingResult binding) {
		ModelAndView result = null;
		Banner banner;

		banner = this.bannerService.reconstruct(originalBanner, binding);
		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors());
			result = this.createEditModelAndView(originalBanner);
		} else
			try {
				this.bannerService.save(banner);
				result = new ModelAndView("redirect:/banner/partner/list.do");
			} catch (final IllegalArgumentException exception) {
				result = this.createEditModelAndView(originalBanner, exception.getMessage());
			}
		return result;

	}

	// Delete ---------------------------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public @ResponseBody
	ModelAndView save(final Banner b) {
		ModelAndView result = null;
		final Banner banner = this.bannerService.findOne(b.getId());
		try {
			this.bannerService.delete(banner);
			result = new ModelAndView("redirect:/banner/partner/list.do");

		} catch (final Throwable oops) {
			result = this.createEditModelAndView(banner, oops.getMessage());
		}

		return result;
	}

	//Ancillary methods ----------------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Banner banner) {
		ModelAndView result;

		result = this.createEditModelAndView(banner, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Banner banner, final String message) {
		ModelAndView result;
		result = new ModelAndView("banner/partner/edit");
		result.addObject("banner", banner);
		result.addObject("message", message);
		result.addObject("requestURI", "banner/partner/edit.do");

		return result;
	}
}
