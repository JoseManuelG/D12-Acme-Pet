
package controllers.animaniac;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SearchEngineService;
import services.TypeService;
import controllers.AbstractController;
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


	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search() {

		ModelAndView result;
		final Collection<Request> results;
		SearchEngine search;
		final Collection<Type> types = this.typeService.findAll();
		search = this.searchEngineService.findByPrincipal();
		results = search.getRequests();

		result = new ModelAndView("searchEngine/animaniac/search.do");
		result.addObject("results", results);
		result.addObject("search", search);
		result.addObject("requestURI", "searchEngine/animaniac/search.do");
		result.addObject("types", types);

		return result;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "save")
	public ModelAndView search(final SearchEngine search, final BindingResult binding) {
		ModelAndView result;
		SearchEngine res;

		res = this.searchEngineService.reconstruct(search, binding);
		Collection<Request> results;
		if (binding.hasErrors()) {
			results = new ArrayList<Request>();

			result = new ModelAndView("searchEngine/animaniac/search.do");
			result.addObject("search", search);
			result.addObject("requestURI", "searchEngine/animaniac/search.do");
			result.addObject("results", results);

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
			}
		final Collection<Type> types = this.typeService.findAll();
		result.addObject("types", types);
		return result;
	}
}
