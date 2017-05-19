/*
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package usecases;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.BannerService;
import services.PartnerService;
import utilities.AbstractTest;
import domain.Banner;
import domain.Partner;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BannerTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private BannerService	bannerService;

	@Autowired
	private PartnerService	partnerService;


	// Tests ------------------------------------------------------------------

	//Caso de uso de crear un banner:
	//test positivo
	@Test
	public void createBannerTest1() {
		this.templateCreateBanner("partner1", "http://link.com", "http://image.com", null);
	}
	//sin loguearse
	@Test
	public void createBannerTest2() {
		this.templateCreateBanner(null, "http://link.com", "http://image.com", IllegalArgumentException.class);
	}
	//link no url
	@Test
	public void createBannerTest3() {
		this.templateCreateBanner("partner1", "no soy una url", "http://image.com", ConstraintViolationException.class);
	}
	//imagen no url
	@Test
	public void createBannerTest4() {
		this.templateCreateBanner("partner1", "http://link.com", "no soy una url", ConstraintViolationException.class);
	}
	//link vacío
	@Test
	public void createBannerTest5() {
		this.templateCreateBanner("partner1", "", "http://image.com", ConstraintViolationException.class);
	}
	//imagen vacía
	@Test
	public void createBannerTest6() {
		this.templateCreateBanner("partner1", "http://link.com", "", ConstraintViolationException.class);
	}
	//link nulo
	@Test
	public void createBannerTest7() {
		this.templateCreateBanner("partner1", null, "http://image.com", ConstraintViolationException.class);
	}
	//imagen nula
	@Test
	public void createBannerTest8() {
		this.templateCreateBanner("partner1", "http://link.com", null, ConstraintViolationException.class);
	}
	//logeado como no asociado
	@Test
	public void createBannerTest9() {
		this.templateCreateBanner("animaniac1", "http://link.com", "http://image.com", NullPointerException.class);
	}

	//Caso de uso de editar un banner:
	//test positivo
	@Test
	public void editBannerTest1() {
		this.templateEditBanner("partner1", "Banner1Partner1", "http://link.com", "http://image.com", null);
	}
	//sin loguearse
	@Test
	public void editBannerTest2() {
		this.templateEditBanner(null, "Banner1Partner1", "http://link.com", "http://image.com", IllegalArgumentException.class);
	}
	//link no url
	@Test
	public void editBannerTest3() {
		this.templateEditBanner("partner1", "Banner1Partner1", "no soy una url", "http://image.com", ConstraintViolationException.class);
	}
	//imagen no url
	@Test
	public void editBannerTest4() {
		this.templateEditBanner("partner1", "Banner1Partner1", "http://link.com", "no soy una url", ConstraintViolationException.class);
	}
	//link vacío
	@Test
	public void editBannerTest5() {
		this.templateEditBanner("partner1", "Banner1Partner1", "", "http://image.com", ConstraintViolationException.class);
	}
	//imagen vacía
	@Test
	public void editBannerTest6() {
		this.templateEditBanner("partner1", "Banner1Partner1", "http://link.com", "", ConstraintViolationException.class);
	}
	//link nulo
	@Test
	public void editBannerTest7() {
		this.templateEditBanner("partner1", "Banner1Partner1", null, "http://image.com", ConstraintViolationException.class);
	}
	//imagen nula
	@Test
	public void editBannerTest8() {
		this.templateEditBanner("partner1", "Banner1Partner1", "http://link.com", null, ConstraintViolationException.class);
	}
	//el banner no pertenece al asociado
	@Test
	public void editBannerTest9() {
		this.templateEditBanner("partner2", "Banner1Partner1", "http://link.com", "http://image.com", IllegalArgumentException.class);
	}
	//logeado como no asociado
	@Test
	public void editBannerTest10() {
		this.templateEditBanner("animaniac1", "Banner1Partner1", "http://link.com", "http://image.com", NullPointerException.class);
	}

	//Caso de uso de borrar un banner:
	//test positivo
	@Test
	public void deleteBannerTest1() {
		this.templateDeleteBanner("partner1", "Banner1Partner1", null);
	}
	//sin loguearse
	@Test
	public void deleteBannerTest2() {
		this.templateDeleteBanner(null, "Banner1Partner1", IllegalArgumentException.class);
	}
	//logeado como no asociado
	@Test
	public void deleteBannerTest3() {
		this.templateDeleteBanner("animaniac1", "Banner1Partner1", NullPointerException.class);
	}
	//banner no existe
	@Test
	public void deleteBannerTest4() {
		this.templateDeleteBanner("partner1", "noExist", IllegalArgumentException.class);
	}
	//el banner no pertenece al asociado
	@Test
	public void deleteBannerTest5() {
		this.templateDeleteBanner("partner2", "Banner1Partner1", IllegalArgumentException.class);
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateCreateBanner(final String username, final String link, final String picture, final Class<?> expected) {
		Class<?> caught;
		Banner banner;
		Partner partner;

		caught = null;
		try {
			this.authenticate(username);

			banner = this.bannerService.create();
			partner = this.partnerService.findPartnerByPrincipal();

			banner.setPicture(picture);
			banner.setLink(link);
			banner.setPartner(partner);

			this.bannerService.save(banner);
			this.bannerService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	protected void templateEditBanner(final String username, final String bannerBeanName, final String link, final String picture, final Class<?> expected) {
		Class<?> caught;
		Banner banner;

		caught = null;
		try {
			this.authenticate(username);

			banner = this.bannerService.findOne(this.extract(bannerBeanName));

			banner.setPicture(picture);
			banner.setLink(link);

			this.bannerService.save(banner);
			this.bannerService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	protected void templateDeleteBanner(final String username, final String bannerBeanName, final Class<?> expected) {
		Class<?> caught;
		Banner banner;

		caught = null;
		try {
			this.authenticate(username);

			banner = this.bannerService.findOne(this.extract(bannerBeanName));

			this.bannerService.delete(banner);
			this.bannerService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
