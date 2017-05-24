
package controllers.animaniac;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BannerService;
import services.SearchEngineService;
import services.TypeService;
import controllers.AbstractController;
import domain.Banner;
import domain.Request;
import domain.SearchEngine;
import domain.Type;

@Controller
@RequestMapping("/searchEngine/animaniac")
public class SearchEngineAnimaniacController extends AbstractController {

	@Autowired
	private SearchEngineService	searchEngineService;
	@Autowired
	private TypeService			typeService;
	@Autowired
	private BannerService		bannerService;


	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search() {
		ModelAndView result;
		final Collection<Request> results;
		SearchEngine search;
		final Banner banner;

		final Collection<Type> types = new ArrayList<Type>();
		final Type type = this.typeService.create();
		type.setTypeName("All");
		types.add(type);

		types.addAll(this.typeService.findAll());
		search = this.searchEngineService.findByPrincipal();
		results = search.getRequests();
		banner = this.bannerService.randomBanner();

		result = new ModelAndView("searchEngine/animaniac/search.do");
		result.addObject("results", results);
		result.addObject("search", search);
		result.addObject("requestURI", "searchEngine/animaniac/search.do");
		result.addObject("types", types);
		result.addObject("banner", banner);

		return result;
	}
	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "save")
	public ModelAndView search(final SearchEngine search, final BindingResult binding) {
		ModelAndView result;
		SearchEngine res;
		final Banner banner;

		banner = this.bannerService.randomBanner();
		res = this.searchEngineService.reconstruct(search, binding);
		Collection<Request> results;
		if (binding.hasErrors()) {
			results = new ArrayList<Request>();

			result = new ModelAndView("searchEngine/animaniac/search.do");
			result.addObject("search", search);
			result.addObject("requestURI", "searchEngine/animaniac/search.do");
			result.addObject("results", results);
			result.addObject("banner", banner);

		} else
			try {
				this.searchEngineService.save(res);
				result = this.search();

			} catch (final IllegalArgumentException e) {
				results = new ArrayList<Request>();

				result = new ModelAndView("searchEngine/animaniac/search.do");
				result.addObject("search", search);
				result.addObject("requestURI", "searchEngine/animaniac/search.do");
				result.addObject("results", results);
				result.addObject("message", e.getMessage());
				result.addObject("banner", banner);
			}
		final Collection<Type> types = new ArrayList<Type>();
		final Type type = this.typeService.create();
		type.setTypeName("All");
		types.add(type);

		types.addAll(this.typeService.findAll());

		result.addObject("types", types);
		return result;
	}
}
