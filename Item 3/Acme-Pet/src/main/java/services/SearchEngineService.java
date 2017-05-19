
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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


	//Simple CRUD methods-------------------------------------------------------------------

	public SearchEngine create(final int animaniacId) {
		SearchEngine result;
		final Collection<Request> requests = new ArrayList<Request>();
		result = new SearchEngine();
		result.setAnimaniac(this.animaniacService.findOne(animaniacId));
		result.setRequests(requests);
		result.setSearchMoment(new DateTime(System.currentTimeMillis() - this.configurationService.findConfiguration().getCacheTime()).toDate());
		result.setAddress("");

		return result;
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
		//TODO Probar a pasar el bloque if al FindOne() para que saliera actualizado automaticamente
		if (lastSearch.before(timeOfCache) || this.searchTemplateHasBeenModified(searchEngine)) {

			result.setSearchMoment(new DateTime(System.currentTimeMillis() - 1000).toDate());
			if (searchEngine.getStartDate() != null)
				startDate = searchEngine.getStartDate();
			else
				startDate = new DateTime(System.currentTimeMillis()).toDate();

			if (searchEngine.getEndDate() != null)
				endDate = searchEngine.getEndDate();
			else
				endDate = new DateTime(System.currentTimeMillis()).plusYears(1).toDate();

			//Busqueda en base de Datos
			if ((searchEngine.getType() != null))

				requests = this.requestService.searchRequest(searchEngine.getAddress(), searchEngine.getType(), startDate, endDate);

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

		|| !(searchEngine.getType().equals(old.getType()))

		|| !(searchEngine.getEndDate().equals(old.getEndDate()))

		|| !(searchEngine.getStartDate().equals(old.getStartDate()));

		return result;

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

	public void deleteFromRequest(final Request request) {
		Collection<SearchEngine> searchEngines;

		searchEngines = this.searchEngineRepository.findByRequest(request.getId());

		for (final SearchEngine engine : searchEngines)
			engine.getRequests().remove(request);

		this.searchEngineRepository.save(searchEngines);
	}
}
