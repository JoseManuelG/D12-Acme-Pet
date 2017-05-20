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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.TypeService;
import utilities.AbstractTest;
import domain.Type;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TypeTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private TypeService	typeService;


	// Tests ------------------------------------------------------------------

	//Caso de uso de crear un type:
	//test positivo
	@Test
	public void createTypeTest1() {
		this.templateCreateType("admin", "tipo", null);
	}
	//sin loguearse
	@Test
	public void createTypeTest2() {
		this.templateCreateType(null, "tipo", IllegalArgumentException.class);
	}
	//blank
	@Test
	public void createTypeTest3() {
		this.templateCreateType("admin", "", ConstraintViolationException.class);
	}
	//conectado como otro usuario distinto de admin
	@Test
	public void createTypeTest4() {
		this.templateCreateType("animaniac", "tipo", IllegalArgumentException.class);
	}
	//creando un type ya existente
	@Test
	public void createTypeTest5() {
		this.templateCreateType("admin", "dog", IllegalArgumentException.class);
	}

	//Caso de uso de editar un type:
	//test positivo
	@Test
	public void editTypeTest1() {
		this.templateEditType("admin", "type6", "tipo", null);
	}
	//sin loguearse
	@Test
	public void editTypeTest2() {
		this.templateEditType(null, "type6", "tipo", IllegalArgumentException.class);
	}
	//blank
	@Test
	public void editTypeTest3() {
		this.templateEditType("admin", "type6", "", ConstraintViolationException.class);
	}
	//conectado como usuario distinto de admin
	@Test
	public void editTypeTest4() {
		this.templateEditType("animaniac1", "type6", "tipo", IllegalArgumentException.class);
	}
	//tipo existente
	@Test
	public void editTypeTest5() {
		this.templateEditType("admin", "type6", "dog", DataIntegrityViolationException.class);
	}
	//editando un tipo q ya tiene mascotas
	@Test
	public void editTypeTest6() {
		this.templateEditType("admin", "type1", "tipo", IllegalArgumentException.class);
	}

	//Caso de uso de borrar un type:
	//test positivo
	@Test
	public void deleteTypeTest1() {
		this.templateDeleteType("admin", "type6", null);
	}
	//sin loguearse
	@Test
	public void deleteTypeTest2() {
		this.templateDeleteType(null, "type6", IllegalArgumentException.class);
	}
	//logeado como usuario distinto de administrator
	@Test
	public void deleteTypeTest3() {
		this.templateDeleteType("animaniac1", "type6", IllegalArgumentException.class);
	}
	//borrando un type que tiene mascotas
	@Test
	public void deleteTypeTest4() {
		this.templateDeleteType("admin", "type1", IllegalArgumentException.class);
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateCreateType(final String username, final String typeName, final Class<?> expected) {
		Class<?> caught;
		Type type;

		caught = null;
		try {
			this.authenticate(username);

			type = this.typeService.create();

			type.setTypeName(typeName);

			this.typeService.save(type);
			this.typeService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	protected void templateEditType(final String username, final String typeBean, final String typeName, final Class<?> expected) {
		Class<?> caught;
		Type type;

		caught = null;
		try {
			this.authenticate(username);

			type = this.typeService.findOne(this.extract(typeBean));

			type.setTypeName(typeName);

			this.typeService.save(type);
			this.typeService.flush();
			this.unauthenticate();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	protected void templateDeleteType(final String username, final String typeBean, final Class<?> expected) {
		Class<?> caught;
		Type type;
		caught = null;
		try {
			this.authenticate(username);
			type = this.typeService.findOne(this.extract(typeBean));

			this.typeService.delete(type);
			this.typeService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
