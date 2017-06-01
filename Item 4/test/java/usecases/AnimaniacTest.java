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
import services.AnimaniacService;
import services.UserAccountService;
import utilities.AbstractTest;
import domain.Animaniac;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AnimaniacTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private AnimaniacService	animaniacService;
	@Autowired
	private UserAccountService	userAccountService;


	// Tests ------------------------------------------------------------------

	//Caso de uso de crear un animaniac:
	//test positivo
	@Test
	public void createAnimaniacTest1() {
		this.templateRegisterAnimaniac("animaniacTest", "animaniacTest", "animaniacTest", "animaniacTest", "animaniacTest2@acme.com", "+34000000", "male", "animaniacTest dir", "http://www.acme.com/picture1.jpg", null);
	}
	//blank username
	@Test
	public void createAnimaniacTest2() {
		this.templateRegisterAnimaniac("", "animaniacTest", "animaniacTest", "animaniacTest", "animaniacTest2@acme.com", "+34000000", "male", "animaniacTest dir", "http://www.acme.com/picture1.jpg", ConstraintViolationException.class);
	}
	//blank password
	@Test
	public void createAnimaniacTest3() {
		this.templateRegisterAnimaniac("animaniacTest", "", "animaniacTest", "animaniacTest", "animaniacTest2@acme.com", "+34000000", "male", "animaniacTest dir", "http://www.acme.com/picture1.jpg", ConstraintViolationException.class);
	}
	//blank name
	@Test
	public void createAnimaniacTest4() {
		this.templateRegisterAnimaniac("animaniacTest", "animaniacTest", "", "animaniacTest", "animaniacTest2@acme.com", "+34000000", "male", "animaniacTest dir", "http://www.acme.com/picture1.jpg", ConstraintViolationException.class);
	}
	//blank surname
	@Test
	public void createAnimaniacTest5() {
		this.templateRegisterAnimaniac("animaniacTest", "animaniacTest", "animaniacTest", "", "animaniacTest2@acme.com", "+34000000", "male", "animaniacTest dir", "http://www.acme.com/picture1.jpg", ConstraintViolationException.class);
	}
	//incorrect mail
	@Test
	public void createAnimaniacTest6() {
		this.templateRegisterAnimaniac("animaniacTest", "animaniacTest", "animaniacTest", "animaniacTest", "animaniacTest2@", "+34000000", "male", "animaniacTest dir", "http://www.acme.com/picture1.jpg", ConstraintViolationException.class);
	}
	//incorrect phone
	@Test
	public void createAnimaniacTest7() {
		this.templateRegisterAnimaniac("animaniacTest", "animaniacTest", "animaniacTest", "animaniacTest", "animaniacTest2@acme.com", "", "male", "animaniacTest dir", "http://www.acme.com/picture1.jpg", ConstraintViolationException.class);
	}
	//incorrect genre
	@Test
	public void createAnimaniacTest8() {
		this.templateRegisterAnimaniac("animaniacTest", "animaniacTest", "animaniacTest", "animaniacTest", "animaniacTest2@acme.com", "+34000000", "sdf", "animaniacTest dir", "http://www.acme.com/picture1.jpg", ConstraintViolationException.class);
	}
	//blank address
	@Test
	public void createAnimaniacTest9() {
		this.templateRegisterAnimaniac("animaniacTest", "animaniacTest", "animaniacTest", "animaniacTest", "animaniacTest2@acme.com", "+34000000", "male", "", "http://www.acme.com/picture1.jpg", ConstraintViolationException.class);
	}
	//userName ya existente
	@Test
	public void createAnimaniacTest10() {
		this.templateRegisterAnimaniac("animaniac1", "animaniacTest", "animaniacTest", "animaniacTest", "animaniacTest2@acme.com", "+34000000", "male", "animaniacTest dir", "http://www.acme.com/picture1.jpg", DataIntegrityViolationException.class);
	}

	//Caso de uso de editar un animaniac:
	//test positivo
	@Test
	public void editAnimaniacTest1() {
		this.templateEditAnimaniac("animaniac1", "animaniacTest", "animaniacTest", "animaniacTest", "animaniacTest", "animaniacTest2@acme.com", "+34000000", "male", "animaniacTest dir", "http://www.acme.com/picture1.jpg", null);
	}
	//sin loguearse
	@Test
	public void editAnimaniacTest2() {
		this.templateEditAnimaniac(null, "animaniacTest", "animaniacTest", "animaniacTest", "animaniacTest", "animaniacTest2@acme.com", "+34000000", "male", "animaniacTest dir", "http://www.acme.com/picture1.jpg", IllegalArgumentException.class);
	}
	//userName ya existente
	@Test
	public void editAnimaniacTest3() {
		this.templateEditAnimaniac("animaniac1", "animaniac2", "animaniacTest", "animaniacTest", "animaniacTest", "animaniacTest2@acme.com", "+34000000", "male", "animaniacTest dir", "http://www.acme.com/picture1.jpg",
			DataIntegrityViolationException.class);
	}
	//conectado como usuario distinto de animaniac
	@Test
	public void editAnimaniacTest4() {
		this.templateEditAnimaniac("partner1", "animaniacTest", "animaniacTest", "animaniacTest", "animaniacTest", "animaniacTest2@acme.com", "+34000000", "male", "animaniacTest dir", "http://www.acme.com/picture1.jpg", NullPointerException.class);
	}
	//blanks
	@Test
	public void editAnimaniacTest5() {
		this.templateEditAnimaniac("animaniac1", "", "", "", "", "", "", "", "", "", ConstraintViolationException.class);
	}

	//Caso de uso de borrar un animaniac:
	//test positivo
	@Test
	public void deleteAnimaniacTest1() {
		this.templateDeleteAnimaniac("animaniac3", null);
	}
	//sin loguearse
	@Test
	public void deleteAnimaniacTest2() {
		this.templateDeleteAnimaniac(null, IllegalArgumentException.class);
	}
	//logeado como usuario distinto de animaniac
	@Test
	public void deleteAnimaniacTest3() {
		this.templateDeleteAnimaniac("admin", NullPointerException.class);
	}
	//usuario con peticion activa
	@Test
	public void deleteAnimaniacTest4() {
		this.templateDeleteAnimaniac("animaniac1", IllegalArgumentException.class);
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateRegisterAnimaniac(final String userName, final String password, final String name, final String surname, final String email, final String phone, final String genre, final String address, final String picture,
		final Class<?> expected) {
		Class<?> caught;
		Animaniac animaniac;
		UserAccount userAccount;
		final Collection<Authority> authorities;
		final Authority authority;

		caught = null;
		try {
			animaniac = this.animaniacService.create();
			userAccount = this.userAccountService.create();
			authorities = new ArrayList<Authority>();
			authority = new Authority();

			authority.setAuthority("ANIMANIAC");
			authorities.add(authority);
			userAccount.setAuthorities(authorities);

			animaniac.setUserAccount(userAccount);
			animaniac.getUserAccount().setUsername(userName);
			animaniac.getUserAccount().setPassword(password);
			animaniac.setEmail(email);
			animaniac.setName(name);
			animaniac.setSurname(surname);
			animaniac.setPhone(phone);
			animaniac.setGenre(genre);
			animaniac.setPicture(picture);
			animaniac.getUserAccount().setEnabled(true);
			animaniac.setRate(0);
			animaniac.setAddress(address);

			this.animaniacService.save(animaniac);
			this.animaniacService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	protected void templateEditAnimaniac(final String animaniacBean, final String userName, final String password, final String name, final String surname, final String email, final String phone, final String genre, final String address,
		final String picture, final Class<?> expected) {
		Class<?> caught;
		Animaniac animaniac;

		caught = null;
		try {
			this.authenticate(animaniacBean);

			animaniac = this.animaniacService.findAnimaniacByPrincipal();

			animaniac.getUserAccount().setUsername(userName);
			animaniac.getUserAccount().setPassword(password);
			animaniac.setEmail(email);
			animaniac.setName(name);
			animaniac.setSurname(surname);
			animaniac.setPhone(phone);
			animaniac.setGenre(genre);
			animaniac.setPicture(picture);
			animaniac.setAddress(address);

			this.animaniacService.save(animaniac);
			this.animaniacService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	protected void templateDeleteAnimaniac(final String animaniacBean, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			this.authenticate(animaniacBean);

			this.animaniacService.delete();
			this.animaniacService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
