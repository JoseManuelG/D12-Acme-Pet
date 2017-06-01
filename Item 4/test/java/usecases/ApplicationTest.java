/*
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.AnimaniacService;
import services.ApplicationService;
import services.RequestService;
import utilities.AbstractTest;
import domain.Application;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ApplicationTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private ApplicationService	applicationService;
	@Autowired
	private RequestService		requestService;
	@Autowired
	private AnimaniacService	animaniacService;


	// Tests ------------------------------------------------------------------

	//Caso de uso de crear un application:
	//test positivo
	@Test
	public void createApplicationTest1() {
		this.templateRegisterApplication("animaniac1", "description", "request1Animaniac2", null);
	}
	//sin loguearse
	@Test
	public void createApplicationTest2() {
		this.templateRegisterApplication(null, "description", "request1Animaniac2", IllegalArgumentException.class);
	}
	//logueado como usuario distinto de animaniac
	@Test
	public void createApplicationTest3() {
		this.templateRegisterApplication("partner1", "description", "request1Animaniac2", NullPointerException.class);
	}
	//application duplicada para la misma request
	@Test
	public void createApplicationTest4() {
		this.templateRegisterApplication("animaniac7", "description", "request6Animaniac1", IllegalArgumentException.class);
	}
	//application a si mismo
	@Test
	public void createApplicationTest5() {
		this.templateRegisterApplication("animaniac2", "description", "request1Animaniac2", IllegalArgumentException.class);
	}
	//application a una request antigua
	@Test
	public void createApplicationTest6() {
		this.templateRegisterApplication("animaniac2", "description", "request1Animaniac1", IllegalArgumentException.class);
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateRegisterApplication(final String userName, final String description, final String requestBean, final Class<?> expected) {
		Class<?> caught;
		Application application;

		caught = null;
		try {
			this.authenticate(userName);
			application = this.applicationService.create(this.requestService.findOne(this.extract(requestBean)).getId());

			application.setDescription(description);
			application.setAnimaniac(this.animaniacService.findAnimaniacByPrincipal());
			application.setState("Pending");

			this.applicationService.save(application);
			this.applicationService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
