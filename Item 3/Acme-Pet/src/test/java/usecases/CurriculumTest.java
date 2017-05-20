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

import services.AnimaniacService;
import services.CurriculumService;
import utilities.AbstractTest;
import domain.Animaniac;
import domain.Curriculum;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CurriculumTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private CurriculumService	curriculumService;

	@Autowired
	private AnimaniacService	animaniacService;


	// Tests ------------------------------------------------------------------

	//Caso de uso de crear un curriculum:
	//test positivo
	@Test
	public void createCurriculumTest1() {
		this.templateCreateCurriculum("animaniac3", "education", "experiences", "hobbies", null);
	}
	//sin loguearse
	@Test
	public void createCurriculumTest2() {
		this.templateCreateCurriculum(null, "education", "experiences", "hobbies", IllegalArgumentException.class);
	}
	//blank  education
	@Test
	public void createCurriculumTest3() {
		this.templateCreateCurriculum("animaniac3", "", "experiences", "hobbies", ConstraintViolationException.class);
	}
	//blank  experiences
	@Test
	public void createCurriculumTest4() {
		this.templateCreateCurriculum("animaniac3", "education", "", "hobbies", ConstraintViolationException.class);
	}
	//blank  hobbies
	@Test
	public void createCurriculumTest5() {
		this.templateCreateCurriculum("animaniac3", "education", "experiences", "", ConstraintViolationException.class);
	}
	//conectado como usuario disinto de animaniac
	@Test
	public void createCurriculumTest6() {
		this.templateCreateCurriculum("partner1", "education", "experiences", "", ConstraintViolationException.class);
	}
	//conectado con animanaic que ya tiene curriculum
	@Test
	public void createCurriculumTest7() {
		this.templateCreateCurriculum("animaniac1", "education", "experiences", "", ConstraintViolationException.class);
	}
	//Caso de uso de editar un curriculum:
	//test positivo
	@Test
	public void editCurriculumTest1() {
		this.templateEditCurriculum("animaniac1", "education", "expercienes", "hobbies", null);
	}
	//sin loguearse
	@Test
	public void editCurriculumTest2() {
		this.templateEditCurriculum(null, "education", "expercienes", "hobbies", IllegalArgumentException.class);
	}
	//blank education
	@Test
	public void editCurriculumTest3() {
		this.templateEditCurriculum("animaniac1", "", "expercienes", "hobbies", ConstraintViolationException.class);
	}
	//blank experiences
	@Test
	public void editCurriculumTest4() {
		this.templateEditCurriculum("animaniac1", "education", "", "hobbies", ConstraintViolationException.class);
	}
	//blank hobbies
	@Test
	public void editCurriculumTest5() {
		this.templateEditCurriculum("animaniac1", "education", "expercienes", "", ConstraintViolationException.class);
	}
	//conectado como animaniac que no tiene curriculum
	@Test
	public void editCurriculumTest6() {
		this.templateEditCurriculum("animaniac3", "education", "expercienes", "hobbies", NullPointerException.class);
	}
	//conectado como usuario distinto de animaniac
	@Test
	public void editCurriculumTest7() {
		this.templateEditCurriculum("partner1", "education", "expercienes", "hobbies", NullPointerException.class);
	}

	//Caso de uso de borrar un curriculum:
	//test positivo
	@Test
	public void deleteCurriculumTest1() {
		this.templateDeleteCurriculum("animaniac1", null);
	}
	//sin loguearse
	@Test
	public void deleteCurriculumTest2() {
		this.templateDeleteCurriculum(null, IllegalArgumentException.class);
	}
	//logeado como usuario distinto de animaniac
	@Test
	public void deleteCurriculumTest3() {
		this.templateDeleteCurriculum("partner1", IllegalArgumentException.class);
	}
	//conectado con animaniac que no tiene curriculum
	@Test
	public void deleteCurriculumTest4() {
		this.templateDeleteCurriculum("animaniac3", IllegalArgumentException.class);
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateCreateCurriculum(final String username, final String education, final String experiences, final String hobbies, final Class<?> expected) {
		Class<?> caught;
		Curriculum curriculum;
		Animaniac animaniac;

		caught = null;
		try {
			this.authenticate(username);

			curriculum = this.curriculumService.create();
			animaniac = this.animaniacService.findAnimaniacByPrincipal();

			curriculum.setEducationSection(education);
			curriculum.setExperienceSection(experiences);
			curriculum.setHobbiesSection(hobbies);
			curriculum.setAnimaniac(animaniac);

			this.curriculumService.save(curriculum);
			this.curriculumService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	protected void templateEditCurriculum(final String username, final String education, final String experiences, final String hobbies, final Class<?> expected) {
		Class<?> caught;
		Curriculum curriculum;
		Animaniac principal;

		caught = null;
		try {
			this.authenticate(username);

			principal = this.animaniacService.findAnimaniacByPrincipal();
			curriculum = this.curriculumService.findCurriculumByAnimaniac(principal.getId());

			curriculum.setEducationSection(education);
			curriculum.setExperienceSection(experiences);
			curriculum.setHobbiesSection(hobbies);

			this.curriculumService.save(curriculum);
			this.curriculumService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	protected void templateDeleteCurriculum(final String username, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(username);

			this.curriculumService.deletePrincipal();
			this.curriculumService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
