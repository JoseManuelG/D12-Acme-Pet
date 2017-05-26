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
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.AbuseReportService;
import services.AnimaniacService;
import utilities.AbstractTest;
import domain.AbuseReport;
import domain.Animaniac;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AbuseReportTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private AbuseReportService	abuseReportService;

	@Autowired
	private AnimaniacService	animaniacService;


	// Tests ------------------------------------------------------------------

	//Caso de uso de crear un abuseReport:
	//test positivo
	@Test
	public void createAbuseReportTest1() {
		this.templateCreateAbuseReport("animaniac1", "animaniac2", "description", null);
	}
	//sin loguearse
	@Test
	public void createAbuseReportTest2() {
		this.templateCreateAbuseReport(null, "animaniac2", "description", IllegalArgumentException.class);
	}
	//reported nulo
	@Test
	public void createAbuseReportTest3() {
		this.templateCreateAbuseReport("animaniac1", null, "description", NullPointerException.class);
	}
	//blank  description
	@Test
	public void createAbuseReportTest4() {
		this.templateCreateAbuseReport("animaniac1", "animaniac2", "", ConstraintViolationException.class);
	}
	//logueado como usuario distinto de animaniac
	@Test
	public void createAbuseReportTest5() {
		this.templateCreateAbuseReport("partner1", "animaniac2", "description", NullPointerException.class);
	}
	//reportarse a si mismo
	@Test
	public void createAbuseReportTest6() {
		this.templateCreateAbuseReport("animaniac1", "animaniac1", "description", IllegalArgumentException.class);
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateCreateAbuseReport(final String username, final String reportedUA, final String description, final Class<?> expected) {
		Class<?> caught;
		AbuseReport abuseReport;
		Animaniac principal, reported;

		caught = null;
		try {
			this.authenticate(username);

			principal = this.animaniacService.findAnimaniacByPrincipal();
			reported = this.animaniacService.findOne(this.extract(reportedUA));
			abuseReport = this.abuseReportService.create(reported.getId());

			abuseReport.setDescription(description);
			abuseReport.setReporter(principal);
			abuseReport.setReportDate(new Date(System.currentTimeMillis() - 100));

			this.abuseReportService.save(abuseReport);
			this.abuseReportService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
