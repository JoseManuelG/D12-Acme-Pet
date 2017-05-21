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
import services.PartnerService;
import services.UserAccountService;
import utilities.AbstractTest;
import domain.Partner;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PartnerTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private PartnerService		partnerService;
	@Autowired
	private UserAccountService	userAccountService;


	// Tests ------------------------------------------------------------------

	//Caso de uso de crear un partner:
	//test positivo
	@Test
	public void createPartnerTest1() {
		this.templateRegisterPartner("admin", "partnerTest", "partnerTest", "partnerTest", "partnerTest", "partnerTest2@acme.com", "+34000000", "partnerTest dir", "description test", "http://www.acme.com/picture1.jpg", null);
	}
	//blank username
	@Test
	public void createPartnerTest2() {
		this.templateRegisterPartner("admin", "", "partnerTest", "partnerTest", "partnerTest", "partnerTest2@acme.com", "+34000000", "partnerTest dir", "description test", "http://www.acme.com/picture1.jpg", ConstraintViolationException.class);
	}
	//blank password
	@Test
	public void createPartnerTest3() {
		this.templateRegisterPartner("admin", "partnerTest", "", "partnerTest", "partnerTest", "partnerTest2@acme.com", "+34000000", "partnerTest dir", "description test", "http://www.acme.com/picture1.jpg", ConstraintViolationException.class);
	}
	//blank name
	@Test
	public void createPartnerTest4() {
		this.templateRegisterPartner("admin", "partnerTest", "partnerTest", "", "partnerTest", "partnerTest2@acme.com", "+34000000", "partnerTest dir", "description test", "http://www.acme.com/picture1.jpg", ConstraintViolationException.class);
	}
	//blank surname
	@Test
	public void createPartnerTest5() {
		this.templateRegisterPartner("admin", "partnerTest", "partnerTest", "partnerTest", "", "partnerTest2@acme.com", "+34000000", "partnerTest dir", "description test", "http://www.acme.com/picture1.jpg", ConstraintViolationException.class);
	}
	//incorrect mail
	@Test
	public void createPartnerTest6() {
		this.templateRegisterPartner("admin", "partnerTest", "partnerTest", "partnerTest", "partnerTest", "partnerTest2@", "+34000000", "partnerTest dir", "description test", "http://www.acme.com/picture1.jpg", ConstraintViolationException.class);
	}
	//incorrect phone
	@Test
	public void createPartnerTest7() {
		this.templateRegisterPartner("admin", "partnerTest", "partnerTest", "partnerTest", "partnerTest", "partnerTest2@acme.com", "", "partnerTest dir", "description test", "http://www.acme.com/picture1.jpg", ConstraintViolationException.class);
	}
	//blank address
	@Test
	public void createPartnerTest9() {
		this.templateRegisterPartner("admin", "partnerTest", "partnerTest", "partnerTest", "partnerTest", "partnerTest2@acme.com", "+34000000", "", "description test", "http://www.acme.com/picture1.jpg", ConstraintViolationException.class);
	}
	//blank description
	@Test
	public void createPartnerTest10() {
		this.templateRegisterPartner("admin", "partnerTest", "partnerTest", "partnerTest", "partnerTest", "partnerTest2@acme.com", "+34000000", "partnerTest dir", "", "http://www.acme.com/picture1.jpg", ConstraintViolationException.class);
	}
	//blank link
	@Test
	public void createPartnerTest11() {
		this.templateRegisterPartner("admin", "partnerTest", "partnerTest", "partnerTest", "partnerTest", "partnerTest2@acme.com", "+34000000", "partnerTest dir", "description test", "", ConstraintViolationException.class);
	}
	//userName ya existente
	@Test
	public void createPartnerTest12() {
		this.templateRegisterPartner("admin", "partner1", "partnerTest", "partnerTest", "partnerTest", "partnerTest2@acme.com", "+34000000", "partnerTest dir", "description test", "http://www.acme.com/picture1.jpg", DataIntegrityViolationException.class);
	}
	//sin loguearse
	@Test
	public void createPartnerTest13() {
		this.templateRegisterPartner(null, "partnerTest", "partnerTest", "partnerTest", "partnerTest", "partnerTest2@acme.com", "+34000000", "partnerTest dir", "description test", "http://www.acme.com/picture1.jpg", IllegalArgumentException.class);
	}
	//logueado como usuario distinto de admin
	@Test
	public void createPartnerTest14() {
		this.templateRegisterPartner("partner1", "partnerTest", "partnerTest", "partnerTest", "partnerTest", "partnerTest2@acme.com", "+34000000", "partnerTest dir", "description test", "http://www.acme.com/picture1.jpg", IllegalArgumentException.class);
	}

	//Caso de uso de editar un partner:
	//test positivo
	@Test
	public void editPartnerTest1() {
		this.templateEditPartner("partner1", "partnerTest", "partnerTest", "partnerTest", "partnerTest", "partnerTest2@acme.com", "+34000000", "partnerTest dir", "description test", "http://www.acme.com/picture1.jpg", null);
	}
	//sin loguearse
	@Test
	public void editPartnerTest2() {
		this.templateEditPartner(null, "partnerTest", "partnerTest", "partnerTest", "partnerTest", "partnerTest2@acme.com", "+34000000", "partnerTest dir", "description test", "http://www.acme.com/picture1.jpg", IllegalArgumentException.class);
	}
	//userName ya existente
	@Test
	public void editPartnerTest3() {
		this.templateEditPartner("partner1", "partner2", "partnerTest", "partnerTest", "partnerTest", "partnerTest2@acme.com", "+34000000", "partnerTest dir", "description test", "http://www.acme.com/picture1.jpg", DataIntegrityViolationException.class);
	}
	//conectado como usuario distinto de partner
	@Test
	public void editPartnerTest4() {
		this.templateEditPartner("vetvet1", "partnerTest", "partnerTest", "partnerTest", "partnerTest", "partnerTest2@acme.com", "+34000000", "partnerTest dir", "description test", "http://www.acme.com/picture1.jpg", NullPointerException.class);
	}
	//blanks
	@Test
	public void editPartnerTest5() {
		this.templateEditPartner("partner1", "", "", "", "", "", "", "", "", "", ConstraintViolationException.class);
	}

	//Caso de uso de borrar un partner:
	//test positivo
	@Test
	public void deletePartnerTest1() {
		this.templateDeletePartner("partner1", null);
	}
	//sin loguearse
	@Test
	public void deletePartnerTest2() {
		this.templateDeletePartner(null, IllegalArgumentException.class);
	}
	//logeado como usuario distinto de partner
	@Test
	public void deletePartnerTest3() {
		this.templateDeletePartner("admin", NullPointerException.class);
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateRegisterPartner(final String administratorBean, final String userName, final String password, final String name, final String surname, final String email, final String phone, final String address, final String description,
		final String link, final Class<?> expected) {
		Class<?> caught;
		Partner partner;
		UserAccount userAccount;
		final Collection<Authority> authorities;
		final Authority authority;

		caught = null;
		try {
			this.authenticate(administratorBean);

			partner = this.partnerService.create();
			userAccount = this.userAccountService.create();
			authorities = new ArrayList<Authority>();
			authority = new Authority();

			authority.setAuthority("PARTNER");
			authorities.add(authority);
			userAccount.setAuthorities(authorities);

			partner.setUserAccount(userAccount);
			partner.getUserAccount().setUsername(userName);
			partner.getUserAccount().setPassword(password);
			partner.setEmail(email);
			partner.setName(name);
			partner.setSurname(surname);
			partner.setPhone(phone);
			partner.getUserAccount().setEnabled(true);
			partner.setAddress(address);
			partner.setDescription(description);
			partner.setLink(link);
			partner.setNumDisplay(0);
			partner.setTotalFee(0.);

			this.partnerService.save(partner);
			this.partnerService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	protected void templateEditPartner(final String partnerBean, final String userName, final String password, final String name, final String surname, final String email, final String phone, final String address, final String description,
		final String link, final Class<?> expected) {
		Class<?> caught;
		Partner partner;

		caught = null;
		try {
			this.authenticate(partnerBean);

			partner = this.partnerService.findPartnerByPrincipal();

			partner.getUserAccount().setUsername(userName);
			partner.getUserAccount().setPassword(password);
			partner.setEmail(email);
			partner.setName(name);
			partner.setSurname(surname);
			partner.setPhone(phone);
			partner.setAddress(address);
			partner.setDescription(description);
			partner.setLink(link);

			this.partnerService.save(partner);
			this.partnerService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	protected void templateDeletePartner(final String partnerBean, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			this.authenticate(partnerBean);

			this.partnerService.delete();
			this.partnerService.flush();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
