/*
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package usecases;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.SpamWordService;
import utilities.AbstractTest;
import domain.SpamWord;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SpamWordTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private SpamWordService	spamWordService;


	// Tests ------------------------------------------------------------------

	//Caso de uso de crear una pet:
	//test positivo
	@Test
	public void createSpamWordTest1() {
		this.templateCreateSpamWord("admin", "FunctionalTestSpamWord", null);
	}
	//test negativo palabra en blanco
	@Test
	public void createSpamWordTest2() {
		this.templateCreateSpamWord("admin", "", IllegalArgumentException.class);
	}
	//test negativo no logged
	@Test
	public void createSpamWordTest3() {
		this.templateCreateSpamWord(null, "FunctionalTestSpamWord", IllegalArgumentException.class);
	}
	//test negativo logged como animaniaco
	@Test
	public void createSpamWordTest4() {
		this.templateCreateSpamWord("animaniaco1", "FunctionalTestSpamWord", IllegalArgumentException.class);
	}

	//test negativo palabra ya existente
	@Test
	public void createSpamWordTest5() {
		this.templateCreateSpamWord("admin", "sex", IllegalArgumentException.class);
	}

	//Caso de uso de editar una pet:
	//test positivo
	@Test
	public void editSpamWord1() {

		this.templateEditSpamWord("admin", "spamWord2", "alalalal", null);
	}
	//test negativo palabra blanco
	@Test
	public void editSpamWord2() {
		this.templateEditSpamWord("admin", "spamWord1", "", IllegalArgumentException.class);
	}

	//test negativo no logged
	@Test
	public void editSpamWord3() {
		this.templateEditSpamWord(null, "spamWord1", "testFunctioanl", IllegalArgumentException.class);

	}

	//test negativo logged como animaniaco
	@Test
	public void editSpamWord4() {
		this.templateEditSpamWord("animaniac1", "spamWord1", "testFunctioanl", IllegalArgumentException.class);
	}

	//test negativo no existe la spaword
	@Test
	public void editSpamWord5() {
		this.templateEditSpamWord("admin", "noExist", "testFunctioanl", IllegalArgumentException.class);
	}

	//test negativo ya existe la spamword
	@Test
	public void editSpamWord6() {
		this.templateEditSpamWord("admin", "spamWord1", "love", IllegalArgumentException.class);
	}

	//Caso de uso de borrar una pet:
	//test positivo
	@Test
	public void deleteSpamWordTest1() {
		this.templateDeleteSpamWord("admin", "spamWord1", null);
	}

	//test negativo: logged how animaniac
	@Test
	public void deleteSpamWordTest2() {
		this.templateDeleteSpamWord("animaniac1", "spamWord1", IllegalArgumentException.class);
	}
	//test negativo:not logged 
	@Test
	public void deleteSpamWordTest3() {
		this.templateDeleteSpamWord(null, "spamWord1", IllegalArgumentException.class);
	}
	//test negativo:no exist
	@Test
	public void deleteSpamWordTest4() {
		this.templateDeleteSpamWord("admin", "noExist", IllegalArgumentException.class);
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateCreateSpamWord(final String actorBean, final String word, final Class<?> expected) {
		Class<?> caught = null;
		final SpamWord spamWord;

		try {
			this.authenticate(actorBean);
			spamWord = this.spamWordService.create();
			spamWord.setWord(word);
			this.spamWordService.save(spamWord);
			this.spamWordService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	protected void templateEditSpamWord(final String actorBean, final String spamWordBean, final String word, final Class<?> expected) {
		Class<?> caught = null;
		final SpamWord spamWord;
		final SpamWord spamWord2;

		try {
			this.authenticate(actorBean);
			spamWord = this.spamWordService.create();
			spamWord2 = this.spamWordService.findOne(this.extract(spamWordBean));
			spamWord.setWord(word);
			spamWord.setId(spamWord2.getId());
			spamWord.setVersion(spamWord2.getVersion());
			this.spamWordService.save(spamWord);
			this.spamWordService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	protected void templateDeleteSpamWord(final String actorBean, final String spamWordBean, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(actorBean);

			this.spamWordService.delete(this.extract(spamWordBean));
			this.spamWordService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
