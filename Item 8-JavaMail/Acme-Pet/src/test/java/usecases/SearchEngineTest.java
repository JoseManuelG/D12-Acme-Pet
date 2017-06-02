/*
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package usecases;

import java.util.Date;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.SearchEngineService;
import utilities.AbstractTest;
import domain.SearchEngine;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SearchEngineTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private SearchEngineService	searchEngineService;


	// Tests ------------------------------------------------------------------

	//Caso de uso de crear un application:
	//test positivo todo vacio
	@Test
	public void createApplicationTest1() {
		this.templateSearchEngine("animaniac1", "searchEngineAnimaniac1", null, null, "", "type1", null);
	}

	//test positivo todo completo
	@Test
	public void createApplicationTest2() {
		this.templateSearchEngine("animaniac1", "searchEngineAnimaniac1", new DateTime(System.currentTimeMillis()).minusMonths(3).toDate(), new DateTime(System.currentTimeMillis()).plusMonths(3).toDate(), "sevilla", "type1", null);
	}

	//test positivo sin fecha de fin
	@Test
	public void createApplicationTest3() {
		this.templateSearchEngine("animaniac1", "searchEngineAnimaniac1", new DateTime(System.currentTimeMillis()).minusMonths(3).toDate(), null, "sevilla", "type1", null);
	}

	//test positivo sin fecha de inicio
	@Test
	public void createApplicationTest4() {
		this.templateSearchEngine("animaniac1", "searchEngineAnimaniac1", null, new DateTime(System.currentTimeMillis()).plusMonths(3).toDate(), "sevilla", "type1", null);
	}

	//test positivo sin address
	@Test
	public void createApplicationTest5() {
		this.templateSearchEngine("animaniac1", "searchEngineAnimaniac1", new DateTime(System.currentTimeMillis()).minusMonths(3).toDate(), new DateTime(System.currentTimeMillis()).plusMonths(3).toDate(), "", "type1", null);
	}

	//test negativo modificando el search de otro animaniaco
	@Test
	public void createApplicationTest6() {
		this.templateSearchEngine("animaniac2", "searchEngineAnimaniac1", new DateTime(System.currentTimeMillis()).minusMonths(3).toDate(), new DateTime(System.currentTimeMillis()).plusMonths(3).toDate(), "sevilla", "type1", IllegalArgumentException.class);
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateSearchEngine(final String animaniacBean, final String searchEngineBean, final Date startDate, final Date endDate, final String address, final String typeBean, final Class<?> expected) {
		Class<?> caught;
		final SearchEngine searchEngine;

		caught = null;
		try {
			this.authenticate(animaniacBean);
			searchEngine = this.searchEngineService.findOne(this.extract(searchEngineBean));
			searchEngine.setStartDate(startDate);
			searchEngine.setEndDate(endDate);
			searchEngine.setAddress(address);
			final Integer typeId = this.extract(typeBean);
			searchEngine.setType(typeId.toString());
			this.searchEngineService.save(searchEngine);
			this.searchEngineService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
