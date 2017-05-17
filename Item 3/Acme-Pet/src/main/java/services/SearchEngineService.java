
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.SearchEngineRepository;
import domain.Animaniac;
import domain.SearchEngine;

@Service
@Transactional
public class SearchEngineService {

	//Managed Repository--------------------------------------------------------------------
	@Autowired
	private SearchEngineRepository	searchEngineRepository;


	//Supported Services--------------------------------------------------------------------

	//Simple CRUD methods-------------------------------------------------------------------

	//Other Business methods-------------------------------------------------------------------

	public void deleteFromAnimaniac(final Animaniac animaniac) {
		SearchEngine searchEngine;

		searchEngine = this.searchEngineRepository.findByAnimaniac(animaniac.getId());

		this.searchEngineRepository.delete(searchEngine);
	}

	public SearchEngine findSearchEngineByAnimaniac(final int animaniacId) {
		return this.searchEngineRepository.findByAnimaniac(animaniacId);
	}
}
