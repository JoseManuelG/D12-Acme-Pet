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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.PetService;
import services.PhotoService;
import utilities.AbstractTest;
import domain.AttributeValue;
import domain.Pet;
import domain.Photo;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PetTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private PetService		petService;
	@Autowired
	private PhotoService	photoService;


	// Tests ------------------------------------------------------------------

	//Caso de uso de crear una pet:
	//test positivo
	@Test
	public void createPetTest1() {
		this.templateRegisterPet("animaniac1", "type1", "test", "male", 10.0, "http://test.com", null);
	}
	//test negativo nombre vacio
	@Test
	public void createPetTest2() {
		this.templateRegisterPet("animaniac1", "type1", "", "male", 10.0, "http://test.com", ConstraintViolationException.class);
	}
	//test negativo genre vacio
	@Test
	public void createPetTest3() {
		this.templateRegisterPet("animaniac1", "type1", "test", "", 10.0, "http://test.com", ConstraintViolationException.class);
	}
	//test negativo genre no adecuado
	@Test
	public void createPetTest4() {
		this.templateRegisterPet("animaniac1", "type1", "test", "asfwf", 10.0, "http://test.com", ConstraintViolationException.class);
	}
	//test negativo weigth negativo
	@Test
	public void createPetTest5() {
		this.templateRegisterPet("animaniac1", "type1", "test", "asfwf", -10.0, "http://test.com", ConstraintViolationException.class);
	}
	//test negativo link photo incorrecto
	@Test
	public void createPetTest6() {
		this.templateRegisterPet("animaniac1", "type1", "test", "male", 10.0, "test", ConstraintViolationException.class);
	}

	//Caso de uso de editar una pet:
	//test positivo
	@Test
	public void editPetTest1() {
		this.templateEditPet("animaniac1", "pet7Animaniac1", "testEdit", "female", 20.0, null);
	}

	//test negativo nombre blanco
	@Test
	public void editPetTest2() {
		this.templateEditPet("animaniac1", "pet7Animaniac1", "", "female", 20.0, ConstraintViolationException.class);
	}

	//test negativo genero blanco
	@Test
	public void editPetTest3() {
		this.templateEditPet("animaniac1", "pet7Animaniac1", "testEdit", "", 20.0, ConstraintViolationException.class);
	}

	//test negativo genero incorrecto
	@Test
	public void editPetTest4() {
		this.templateEditPet("animaniac1", "pet7Animaniac1", "testEdit", "adfsa", 20.0, ConstraintViolationException.class);
	}

	//test negativo weigth negativo
	@Test
	public void editPetTest5() {
		this.templateEditPet("animaniac1", "pet7Animaniac1", "testEdit", "female", -20.0, ConstraintViolationException.class);
	}

	//test negativo pet de otro animaniaco
	@Test
	public void editPetTest6() {
		this.templateEditPet("animaniac2", "pet7Animaniac1", "testEdit", "female", 20.0, IllegalArgumentException.class);
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateRegisterPet(final String animaniacBean, final String typeBean, final String petName, final String petGenre, final Double weigth, final String link, final Class<?> expected) {
		Class<?> caught = null;
		Pet pet;
		final Collection<AttributeValue> attributeValues = new ArrayList<AttributeValue>();
		final Collection<Photo> photos = new ArrayList<Photo>();
		Photo photo;
		try {
			this.authenticate(animaniacBean);
			pet = this.petService.create(this.extract(typeBean));
			pet.setGenre(petGenre);
			pet.setName(petName);
			pet.setWeigth(weigth);
			photo = this.photoService.create(pet);
			photo.setLink(link);
			photos.add(photo);
			this.petService.save(pet, attributeValues, photos);
			this.petService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	protected void templateEditPet(final String animaniacBean, final String petBean, final String petName, final String petGenre, final Double weigth, final Class<?> expected) {
		Class<?> caught = null;
		Pet pet;
		final Collection<AttributeValue> attributeValues = new ArrayList<AttributeValue>();
		final Collection<Photo> photos = new ArrayList<Photo>();
		try {
			this.authenticate(animaniacBean);
			pet = this.petService.findOne(this.extract(petBean));
			pet.setGenre(petGenre);
			pet.setName(petName);
			pet.setWeigth(weigth);
			this.petService.save(pet, attributeValues, photos);
			this.petService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);

	}
}
