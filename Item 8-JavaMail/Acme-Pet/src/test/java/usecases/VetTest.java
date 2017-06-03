/*
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package usecases;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import security.Authority;
import security.UserAccount;
import services.PetService;
import services.UserAccountService;
import services.VetService;
import utilities.AbstractTest;
import domain.Pet;
import domain.Vet;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class VetTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private VetService			vetService;
	@Autowired
	private UserAccountService	userAccountService;
	@Autowired
	private PetService			petService;


	// Tests ------------------------------------------------------------------

	//Caso de uso de crear un vet:
	//test positivo
	@Test
	public void createVetTest1() {
		this.templateRegisterVet("admin", "vetvetTest", "vetvetTest", "vetvetTest", "vetvetTest", "vetvetTest2@acme.com", "+34000000", "vetvetTest dir", null);
	}
	//blank username
	@Test
	public void createVetTest2() {
		this.templateRegisterVet("admin", "", "vetvetTest", "vetvetTest", "vetvetTest", "vetvetTest2@acme.com", "+34000000", "vetvetTest dir", ConstraintViolationException.class);
	}
	//blank password
	@Test
	public void createVetTest3() {
		this.templateRegisterVet("admin", "vetvetTest", "", "vetvetTest", "vetvetTest", "vetvetTest2@acme.com", "+34000000", "vetvetTest dir", ConstraintViolationException.class);
	}
	//blank name
	@Test
	public void createVetTest4() {
		this.templateRegisterVet("admin", "vetvetTest", "vetvetTest", "", "vetvetTest", "vetvetTest2@acme.com", "+34000000", "vetvetTest dir", ConstraintViolationException.class);
	}
	//blank surname
	@Test
	public void createVetTest5() {
		this.templateRegisterVet("admin", "vetvetTest", "vetvetTest", "vetvetTest", "", "vetvetTest2@acme.com", "+34000000", "vetvetTest dir", ConstraintViolationException.class);
	}
	//incorrect mail
	@Test
	public void createVetTest6() {
		this.templateRegisterVet("admin", "vetvetTest", "vetvetTest", "vetvetTest", "vetvetTest", "vetvetTest2@", "+34000000", "vetvetTest dir", ConstraintViolationException.class);
	}
	//incorrect phone
	@Test
	public void createVetTest7() {
		this.templateRegisterVet("admin", "vetvetTest", "vetvetTest", "vetvetTest", "vetvetTest", "vetvetTest2@acme.com", "", "vetvetTest dir", ConstraintViolationException.class);
	}
	//blank address
	@Test
	public void createVetTest9() {
		this.templateRegisterVet("admin", "vetvetTest", "vetvetTest", "vetvetTest", "vetvetTest", "vetvetTest2@acme.com", "+34000000", "", ConstraintViolationException.class);
	}
	//userName ya existente
	@Test
	public void createVetTest10() {
		this.templateRegisterVet("admin", "vetvet1", "vetvetTest", "vetvetTest", "vetvetTest", "vetvetTest2@acme.com", "+34000000", "vetvetTest dir", DataIntegrityViolationException.class);
	}
	//sin loguearse
	@Test
	public void createVetTest11() {
		this.templateRegisterVet(null, "vetvetTest", "vetvetTest", "vetvetTest", "vetvetTest", "vetvetTest2@acme.com", "+34000000", "vetvetTest dir", IllegalArgumentException.class);
	}
	//logueado como usuario distinto de admin
	@Test
	public void createVetTest12() {
		this.templateRegisterVet("vetvet1", "vetvetTest", "vetvetTest", "vetvetTest", "vetvetTest", "vetvetTest2@acme.com", "+34000000", "vetvetTest dir", IllegalArgumentException.class);
	}

	//Caso de uso de editar un vet:
	//test positivo
	@Test
	public void editVetTest1() {
		this.templateEditVet("vetvet1", "vetvetTest", "vetvetTest", "vetvetTest", "vetvetTest", "vetvetTest2@acme.com", "+34000000", "vetvetTest dir", null);
	}
	//sin loguearse
	@Test
	public void editVetTest2() {
		this.templateEditVet(null, "vetvetTest", "vetvetTest", "vetvetTest", "vetvetTest", "vetvetTest2@acme.com", "+34000000", "vetvetTest dir", IllegalArgumentException.class);
	}
	//userName ya existente
	@Test
	public void editVetTest3() {
		this.templateEditVet("vetvet1", "vetvet2", "vetvetTest", "vetvetTest", "vetvetTest", "vetvetTest2@acme.com", "+34000000", "vetvetTest dir", DataIntegrityViolationException.class);
	}
	//conectado como usuario distinto de vet
	@Test
	public void editVetTest4() {
		this.templateEditVet("partner1", "vetvetTest", "vetvetTest", "vetvetTest", "vetvetTest", "vetvetTest2@acme.com", "+34000000", "vetvetTest dir", NullPointerException.class);
	}
	//blanks
	@Test
	public void editVetTest5() {
		this.templateEditVet("vetvet1", "", "", "", "", "", "", "", ConstraintViolationException.class);
	}

	//Caso de uso de borrar un vet:
	//test positivo
	@Test
	public void deleteVetTest1() {
		this.templateDeleteVet("vetvet1", null);
	}
	//sin loguearse
	@Test
	public void deleteVetTest2() {
		this.templateDeleteVet(null, IllegalArgumentException.class);
	}
	//logeado como usuario distinto de vet
	@Test
	public void deleteVetTest3() {
		this.templateDeleteVet("admin", NullPointerException.class);
	}

	//Test de certificar una mascote
	//Test positivo
	@Test
	public void certificatePetByVetTest1() {
		this.templateCertificateVet("vetvet1", "pet7Animaniac1", null);
	}
	//Test negativo, pet ya certificada
	@Test
	public void certificatePetByVetTest2() {
		this.templateCertificateVet("vetvet1", "pet1Animaniac1", IllegalArgumentException.class);
	}
	//Test negativo, no autenticado como vet sino como animaniaco
	@Test
	public void certificatePetByVetTest3() {
		this.templateCertificateVet("animaniac1", "pet1Animaniac1", NullPointerException.class);
	}

	//Test negativo, no autenticado 
	@Test
	public void certificatePetByVetTest4() {
		this.templateCertificateVet("vetvet1", "noExist", NullPointerException.class);
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateRegisterVet(final String administratorBean, final String userName, final String password, final String name, final String surname, final String email, final String phone, final String address, final Class<?> expected) {
		Class<?> caught;
		Vet vet;
		UserAccount userAccount;
		final Collection<Authority> authorities;
		final Authority authority;

		caught = null;
		try {
			this.authenticate(administratorBean);

			vet = this.vetService.create();
			userAccount = this.userAccountService.create();
			authorities = new ArrayList<Authority>();
			authority = new Authority();

			authority.setAuthority("VET");
			authorities.add(authority);
			userAccount.setAuthorities(authorities);

			vet.setUserAccount(userAccount);
			vet.getUserAccount().setUsername(userName);
			vet.getUserAccount().setPassword(password);
			vet.setEmail(email);
			vet.setName(name);
			vet.setSurname(surname);
			vet.setPhone(phone);
			vet.getUserAccount().setEnabled(true);
			vet.setAddress(address);

			this.vetService.save(vet);
			this.vetService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	protected void templateEditVet(final String vetBean, final String userName, final String password, final String name, final String surname, final String email, final String phone, final String address, final Class<?> expected) {
		Class<?> caught;
		Vet vet;

		caught = null;
		try {
			this.authenticate(vetBean);

			vet = this.vetService.findVetByPrincipal();

			vet.getUserAccount().setUsername(userName);
			vet.getUserAccount().setPassword(password);
			vet.setEmail(email);
			vet.setName(name);
			vet.setSurname(surname);
			vet.setPhone(phone);
			vet.setAddress(address);

			this.vetService.save(vet);
			this.vetService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	protected void templateDeleteVet(final String vetBean, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			this.authenticate(vetBean);

			this.vetService.delete();
			this.vetService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	//Template
	protected void templateCertificateVet(final String vetBean, final String petBean, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		Pet pet;
		try {
			this.authenticate(vetBean);
			pet = this.petService.findOne(this.extract(petBean));
			this.petService.saveCertificateByVet(pet.getId());
			this.petService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
