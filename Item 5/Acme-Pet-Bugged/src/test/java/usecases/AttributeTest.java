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

import services.AttributeService;
import services.TypeService;
import utilities.AbstractTest;
import domain.Attribute;
import domain.Type;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AttributeTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private TypeService			typeService;

	@Autowired
	private AttributeService	attributeService;


	// Tests ------------------------------------------------------------------

	//Caso de uso de crear un attribute:
	//test positivo
	@Test
	public void createAttributeTest1() {
		this.templateCreateAttribute("admin", "type1", "attributeName", null);
	}
	//sin loguearse
	@Test
	public void createAttributeTest2() {
		this.templateCreateAttribute(null, "type1", "attributeName", IllegalArgumentException.class);
	}
	//blank
	@Test
	public void createAttributeTest3() {
		this.templateCreateAttribute("admin", "type1", "", ConstraintViolationException.class);
	}
	//conectado como otro usuario distinto de admin
	@Test
	public void createAttributeTest4() {
		this.templateCreateAttribute("animaniac1", "type1", "attributeName", IllegalArgumentException.class);
	}
	//creando un attribute ya existente
	@Test
	public void createAttributeTest5() {
		this.templateCreateAttribute("admin", "type1", "ppp", IllegalArgumentException.class);
	}

	//Caso de uso de editar un attribute:
	//test positivo
	@Test
	public void editAttributeTest1() {
		this.templateEditAttribute("admin", "type1", "attribute3Type1", "attributeName", null);
	}
	//sin loguearse
	@Test
	public void editAttributeTest2() {
		this.templateEditAttribute(null, "type1", "attribute3Type1", "attributeName", IllegalArgumentException.class);
	}
	//blank
	@Test
	public void editAttributeTest3() {
		this.templateEditAttribute("admin", "type1", "attribute3Type1", "", ConstraintViolationException.class);
	}
	//conectado como usuario distinto de admin
	@Test
	public void editAttributeTest4() {
		this.templateEditAttribute("animaniac1", "type1", "attribute3Type1", "attributeName", IllegalArgumentException.class);
	}
	//editandolo para que intente crear uno ya existente 
	@Test
	public void editAttributeTest5() {
		this.templateEditAttribute("admin", "type1", "attribute3Type1", "castrated", DataIntegrityViolationException.class);
	}
	//editando un type q ya tiene mascotas
	@Test
	public void editAttributeTest6() {
		this.templateEditAttribute("admin", "type1", "attribute1Type1", "attributeName", IllegalArgumentException.class);
	}

	//Caso de uso de borrar un attribute:
	//test positivo
	@Test
	public void deleteAttributeTest1() {
		this.templateDeleteAttribute("admin", "attribute3Type1", null);
	}
	//sin loguearse
	@Test
	public void deleteAttributeTest2() {
		this.templateDeleteAttribute(null, "attribute3Type1", IllegalArgumentException.class);
	}
	//logeado como usuario distinto de administrator
	@Test
	public void deleteAttributeTest3() {
		this.templateDeleteAttribute("animaniac1", "attribute3Type1", IllegalArgumentException.class);
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateCreateAttribute(final String username, final String typeBean, final String attributeName, final Class<?> expected) {
		Class<?> caught;
		Attribute attribute;
		Type type;

		caught = null;
		try {
			this.authenticate(username);

			attribute = this.attributeService.create();
			type = this.typeService.findOne(this.extract(typeBean));

			attribute.setName(attributeName);
			attribute.setType(type);

			this.attributeService.save(attribute);
			this.attributeService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	protected void templateEditAttribute(final String username, final String typeBean, final String attributeBean, final String attributeName, final Class<?> expected) {
		Class<?> caught;
		Attribute attribute;
		Type type;

		caught = null;
		try {
			this.authenticate(username);

			attribute = this.attributeService.findOne(this.extract(attributeBean));
			type = this.typeService.findOne(this.extract(typeBean));

			attribute.setName(attributeName);
			attribute.setType(type);

			this.attributeService.save(attribute);
			this.attributeService.flush();
			this.unauthenticate();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	protected void templateDeleteAttribute(final String username, final String attributeBean, final Class<?> expected) {
		Class<?> caught;
		Attribute attribute;
		caught = null;
		try {
			this.authenticate(username);
			attribute = this.attributeService.findOne(this.extract(attributeBean));

			this.attributeService.delete(attribute);
			this.attributeService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
