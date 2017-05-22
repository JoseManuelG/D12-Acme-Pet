/*
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package usecases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.PetService;
import services.RequestService;
import utilities.AbstractTest;
import domain.Pet;
import domain.Request;
import forms.RateForm;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RequestTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private RequestService	requestService;
	@Autowired
	private PetService		petService;


	// Tests ------------------------------------------------------------------

	//Caso de uso de crear un request:
	//test positivo
	@Test
	public void createRequestTest1() {
		this.templateRegisterRequest("animaniac1", 86400000l, 2 * 86400000l, "description", "address", 3, null);
	}
	//sin loguearse
	@Test
	public void createRequestTest2() {
		this.templateRegisterRequest(null, 86400000l, 2 * 86400000l, "description", "address", 1, IllegalArgumentException.class);
	}
	//blank address
	@Test
	public void createRequestTest3() {
		this.templateRegisterRequest("animaniac1", 86400000l, 2 * 86400000l, "description", "", 1, ConstraintViolationException.class);
	}
	//endDate anterior a startDate
	@Test
	public void createRequestTest4() {
		this.templateRegisterRequest("animaniac1", 2 * 86400000l, 86400000l, "description", "", 1, IllegalArgumentException.class);
	}
	//mascota en uso
	@Test
	public void createRequestTest5() {
		this.templateRegisterRequest("animaniac1", 86400000l, 2 * 86400000l, "description", "address", -1, IllegalArgumentException.class);
	}

	//Caso de uso de borrar un request:
	//test positivo
	@Test
	public void deleteRequestTest1() {
		this.templateDeleteRequest("animaniac1", "request1Animaniac1", null);
	}
	//sin loguearse
	@Test
	public void deleteRequestTest2() {
		this.templateDeleteRequest(null, "request1Animaniac1", IllegalArgumentException.class);
	}
	//logeado como usuario distinto de request
	@Test
	public void deleteRequestTest3() {
		this.templateDeleteRequest("parnet1", "request1Animaniac1", IllegalArgumentException.class);
	}
	//request activa
	@Test
	public void deleteRequestTest4() {
		this.templateDeleteRequest("animaniac1", "request6Animaniac1", IllegalArgumentException.class);
	}

	//Caso de uso de puntuar un request:
	//test positivo
	@Test
	public void rateRequestTest1() {
		this.templateRateRequest("animaniac1", "request2Animaniac1", 5, null);
	}
	//rate fuera de rango
	@Test
	public void rateRequestTest2() {
		this.templateRateRequest("animaniac1", "request2Animaniac1", 8, IllegalArgumentException.class);
	}
	//sin loguearse
	@Test
	public void rateRequestTest3() {
		this.templateRateRequest(null, "request2Animaniac1", 4, IllegalArgumentException.class);
	}
	//logueado con otro animaniac que no sea el propietario del request
	@Test
	public void rateRequestTest4() {
		this.templateRateRequest("animaniac2", "request2Animaniac1", 4, IllegalArgumentException.class);
	}
	//request ya puntuado
	@Test
	public void rateRequestTest5() {
		this.templateRateRequest("animaniac1", "request1Animaniac1", 4, IllegalArgumentException.class);
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateRegisterRequest(final String userName, final long startDate, final long endDate, final String description, final String address, final int numberPets, final Class<?> expected) {
		//-startDate y endDate son ms que se suman a la fecha actual
		//-numberPets indica el numero de mascotas de las disponibles para poner en request,
		//	si es -1 indica que pruebe con una mascota que esté en uso (pet1Animaniac1)
		Class<?> caught;
		Request request;
		List<Pet> availables, selected;

		caught = null;
		try {
			this.authenticate(userName);
			request = this.requestService.create();
			availables = new ArrayList<Pet>();
			availables.addAll(this.petService.findAvalaiblePetsFromPrincipal());
			selected = new ArrayList<Pet>();

			request.setDescription(description);
			request.setStartDate(new Date(System.currentTimeMillis() + startDate));
			request.setEndDate(new Date(System.currentTimeMillis() + endDate));
			request.setAddress(address);
			request.setRated(false);

			if (numberPets == -1)
				selected.add(this.petService.findOne(this.extract("pet1Animaniac1")));
			else
				for (int i = 0; i < numberPets; i++)
					selected.add(availables.get(i));

			request.setPets(selected);

			this.requestService.save(request);
			this.requestService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	protected void templateDeleteRequest(final String username, final String requestBean, final Class<?> expected) {
		Class<?> caught;
		Request request;
		caught = null;
		try {
			this.authenticate(username);

			request = this.requestService.findOne(this.extract(requestBean));

			this.requestService.delete(request.getId());
			this.requestService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	protected void templateRateRequest(final String username, final String requestBean, final int rate, final Class<?> expected) {
		Class<?> caught;
		Request request;
		caught = null;
		RateForm rateForm;
		try {
			this.authenticate(username);

			request = this.requestService.findOne(this.extract(requestBean));

			rateForm = new RateForm(request.getId());
			rateForm.setRate(rate);

			//Simula el @Valid que tiene el rateForm en el controlador
			Assert.isTrue(-5 <= rateForm.getRate() && rateForm.getRate() <= 5);

			this.requestService.rate(rateForm);
			this.requestService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
