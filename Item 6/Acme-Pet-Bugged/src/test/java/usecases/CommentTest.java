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

import services.ActorService;
import services.AnimaniacService;
import services.CommentService;
import services.CommentableService;
import utilities.AbstractTest;
import domain.Animaniac;
import domain.Comment;
import domain.Commentable;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CommentTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private CommentService		CommentService;
	@Autowired
	private CommentableService	commentableService;
	@Autowired
	private AnimaniacService	animaniacService;
	@Autowired
	private ActorService		actorService;


	// Tests ------------------------------------------------------------------

	//Caso de uso de crear un vet:
	//final String actorBean, final String folderName, final Class<?> expected
	//test positivo
	@Test
	public void commentTest1() {
		this.templateComment("animaniac1", "animaniac1", "FunctionalTestBody", "animaniac1", null);
	}
	@Test
	public void commentTest2() {
		this.templateComment("animaniac1", "animaniac2", "FunctionalTestBody", "animaniac1", IllegalArgumentException.class);
	}
	@Test
	public void commentTest3() {
		this.templateComment(null, "animaniac1", "FunctionalTestBody", "animaniac1", IllegalArgumentException.class);
	}
	@Test
	public void commentTest4() {
		this.templateComment("animaniac1", "animaniac1", "FunctionalTestBody", "noExist", IllegalArgumentException.class);
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateComment(final String principal, final String actorBean, final String body, final String CommentableBean, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.authenticate(principal);
			final Commentable commentable = this.commentableService.findOne(this.extract(CommentableBean));
			final Comment comment = this.CommentService.create(commentable);
			final Animaniac animaniac = this.animaniacService.findOne(this.extract(actorBean));
			comment.setAnimaniac(animaniac);
			this.CommentService.save(comment);

			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

}
