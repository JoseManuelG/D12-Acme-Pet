
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SearchEngineRepository;
import domain.Animaniac;
import domain.Request;
import domain.SearchEngine;

@Service
@Transactional
public class SearchEngineService {

	//Managed Repository--------------------------------------------------------------------
	@Autowired
	private SearchEngineRepository	searchEngineRepository;

	//Supported Services--------------------------------------------------------------------
	@Autowired
	private ActorService			actorService;
	@Autowired
	private AnimaniacService		animaniacService;
	@Autowired
	private RequestService			requestService;
	@Autowired
	private ConfigurationService	configurationService;
	@Autowired
	private Validator				validator;


	//Simple CRUD methods-------------------------------------------------------------------

	public SearchEngine create(final int animaniacId) {
		SearchEngine result;
		final Collection<Request> requests = new ArrayList<Request>();
		result = new SearchEngine();
		result.setAnimaniac(this.animaniacService.findOne(animaniacId));
		result.setRequests(requests);
		result.setStartDate(new Date(System.currentTimeMillis() - 200));
		result.setEndDate(new Date(System.currentTimeMillis() - 100));
		result.setSearchMoment(new Date(System.currentTimeMillis() - this.configurationService.findConfiguration().getCacheTime()));
		result.setAddress("");
		result.setType("");

		return result;
	}
	public SearchEngine findOne(final int searchEngineId) {
		SearchEngine result;
		result = this.searchEngineRepository.findOne(searchEngineId);
		return result;
	}

	public SearchEngine saveParaCreate(final SearchEngine searchEngine) {
		return this.searchEngineRepository.save(searchEngine);
	}

	public SearchEngine save(final SearchEngine searchEngine) {
		SearchEngine result;
		final Collection<Request> requests;
		Date timeOfCache, lastSearch;
		Date startDate;
		final Date endDate;

		//Revisar que el search guardado sea del Principal
		Assert.isTrue(Animaniac.class.equals(this.actorService.findActorByPrincipal().getClass()));
		Assert.isTrue(this.animaniacService.findAnimaniacByPrincipal().equals(searchEngine.getAnimaniac()));

		//Fechas para comprobar el tiempo de caché

		timeOfCache = new DateTime(System.currentTimeMillis() - this.configurationService.findConfiguration().getCacheTime()).toDate();
		lastSearch = new DateTime(searchEngine.getSearchMoment().getTime()).toDate();

		result = searchEngine;

		//Comprobamos si el SearchTemplate ha sido modificado
		if (lastSearch.before(timeOfCache) || this.searchTemplateHasBeenModified(searchEngine)) {

			result.setSearchMoment(new DateTime(System.currentTimeMillis() - 1000).toDate());
			if (searchEngine.getStartDate() != null)
				startDate = searchEngine.getStartDate();
			else
				startDate = new DateTime(System.currentTimeMillis()).minusYears(100).toDate();

			if (searchEngine.getEndDate() != null)
				endDate = searchEngine.getEndDate();
			else
				endDate = new DateTime(System.currentTimeMillis()).plusYears(100).toDate();

			//Busqueda en base de Datos
			if ((!searchEngine.getType().equals("0")))

				requests = this.requestService.searchRequest(searchEngine.getAddress(), Integer.parseInt(searchEngine.getType()), startDate, endDate);

			else
				requests = this.requestService.searchRequest(searchEngine.getAddress(), startDate, endDate);

			result.setRequests(requests);

			result = this.searchEngineRepository.save(result);
		}

		Assert.notNull(result);

		return result;
	}
	//Comprueba si ha sido modificado el searchTemplate
	private boolean searchTemplateHasBeenModified(final SearchEngine searchEngine) {
		SearchEngine old;
		boolean result;
		old = this.searchEngineRepository.findOne(searchEngine.getId());

		result = !searchEngine.getAddress().equals(old.getAddress())

		|| !(searchEngine.getType().equals(old.getType()));

		if (searchEngine.getEndDate() != null && old.getEndDate() == null)
			result = true;
		else if (searchEngine.getEndDate() == null && old.getEndDate() != null)
			result = true;
		else
			result = result || !searchEngine.getEndDate().equals(old.getEndDate());

		if (searchEngine.getStartDate() != null && old.getStartDate() == null)
			result = true;
		else if (searchEngine.getStartDate() == null && old.getStartDate() != null)
			result = true;
		else
			result = result || !searchEngine.getStartDate().equals(old.getStartDate());

		return result;

	}

	public void flush() {
		this.searchEngineRepository.flush();
	}

	//Other Business methods-------------------------------------------------------------------

	public void deleteFromAnimaniac(final Animaniac animaniac) {
		SearchEngine searchEngine;

		searchEngine = this.searchEngineRepository.findByAnimaniac(animaniac.getId());

		this.searchEngineRepository.delete(searchEngine);
	}

	public SearchEngine findSearchEngineByAnimaniac(final int animaniacId) {
		return this.searchEngineRepository.findByAnimaniac(animaniacId);
	}
	public SearchEngine findByPrincipal() {
		final SearchEngine result;
		final int actorId = this.actorService.findActorByPrincipal().getId();
		result = this.findSearchEngineByAnimaniac(actorId);
		return result;
	}
	public void deleteFromRequest(final Request request) {
		Collection<SearchEngine> searchEngines;

		searchEngines = this.searchEngineRepository.findByRequest(request.getId());

		for (final SearchEngine engine : searchEngines)
			engine.getRequests().remove(request);

		this.searchEngineRepository.save(searchEngines);
	}

	public SearchEngine reconstruct(final SearchEngine searchEngine, final BindingResult binding) {
		SearchEngine res, old;

		old = this.findOne(searchEngine.getId());
		res = this.create(this.actorService.findActorByPrincipal().getId());

		//old things
		res.setId(old.getId());
		res.setVersion(old.getVersion());
		res.setSearchMoment(old.getSearchMoment());
		res.setRequests(old.getRequests());
		res.setAnimaniac(old.getAnimaniac());

		//New things
		res.setAddress(searchEngine.getAddress());
		res.setType(searchEngine.getType());
		res.setStartDate(searchEngine.getStartDate());
		res.setEndDate(searchEngine.getEndDate());
		this.validator.validate(res, binding);

		return res;
	}
}
