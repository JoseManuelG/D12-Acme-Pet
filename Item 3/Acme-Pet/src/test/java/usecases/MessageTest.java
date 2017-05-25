/*
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package usecases;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.FolderService;
import services.MessageService;
import utilities.AbstractTest;
import domain.Attachment;
import domain.Folder;
import domain.Message;
import forms.MessageForm;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MessageTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private MessageService	messageService;

	@Autowired
	private FolderService	folderService;


	// Tests ------------------------------------------------------------------

	//Caso de uso de enviar un mensaje:
	//final String actorBean, final String folderName, final Class<?> expected
	//test positivo
	@Test
	public void createFolderTest1() {
		this.templateSendMessage("animaniac1", "animaniac1", "FunctionalTestSubject", "FunctionalTestText", null);
	}
	//blank subject
	@Test
	public void createFolderTest2() {
		this.templateSendMessage("animaniac1", "animaniac1", "", "FunctionalTestText", IllegalArgumentException.class);
	}
	//blank text
	@Test
	public void createFolderTest3() {
		this.templateSendMessage("animaniac1", "animaniac1", "FunctionalTestSubject", "", IllegalArgumentException.class);
	}
	//sin loguearse
	@Test
	public void createFolderTest4() {
		this.templateSendMessage("", "animaniac1", "FunctionalTestSubject", "FunctionalTestText", IllegalArgumentException.class);
	}

	//RecipientNoExist
	@Test
	public void createFolderTest5() {
		this.templateSendMessage("animaniac1", "noExist", "FunctionalTestSubject", "FunctionalTestText", IllegalArgumentException.class);
	}

	//Caso de uso de mover a un mensaje:
	//final String actorBean, final String messageBean, final String FolderBean, final Class<?> expected
	//test positivo
	@Test
	public void MoveMessageTest1() {
		this.templateMoveMessage("animaniac1", "message1Animaniac1", "folder5Animaniac1", null);
	}
	//notLoged
	@Test
	public void MoveMessageTest2() {
		this.templateMoveMessage(null, "message1Animaniac1", "folder5Animaniac1", IllegalArgumentException.class);
	}
	//NullMessage
	@Test
	public void MoveMessageTest3() {
		this.templateMoveMessage("animaniac1", "noExist", "folder5Animaniac1", IllegalArgumentException.class);
	}
	//NullFolder
	@Test
	public void MoveMessageTest4() {
		this.templateMoveMessage("animaniac1", "message1Animaniac1", "noExist", IllegalArgumentException.class);
	}
	//NotMyFolder
	@Test
	public void MoveMessageTest5() {
		this.templateMoveMessage("animaniac1", "message1Animaniac1", "folder2Animaniac2", IllegalArgumentException.class);
	}

	//Caso de uso de responder un mensaje:
	//final String actorBean, final String messageBean, final String subject, final String text, final Class<?> expected
	//test positivo
	@Test
	public void ReplyMessageTest1() {
		this.templateReplyMessage("animaniac1", "message1Animaniac1", null);
	}
	@Test
	public void ReplyMessageTest2() {
		this.templateReplyMessage("", "message1Animaniac1", IllegalArgumentException.class);
	}
	@Test
	public void ReplyMessageTest3() {
		this.templateReplyMessage("", "noExist", IllegalArgumentException.class);
	}

	//Caso de uso de reenviar un mensaje:
	//final String actorBean, final String messageBean, final String subject, final String text, final Class<?> expected
	//test positivo
	@Test
	public void ForwardMessageTest1() {
		this.templateForwardMessage("animaniac1", "message1Animaniac1", null);
	}
	@Test
	public void ForwardMessageTest2() {
		this.templateForwardMessage("", "message1Animaniac1", IllegalArgumentException.class);
	}
	@Test
	public void ForwardMessageTest3() {
		this.templateForwardMessage("", "noExist", IllegalArgumentException.class);
	}

	//Caso de uso de alertar animaniacos cuando un animaniaco es baneado:
	//final String administratorBean, final String bannedAnimaniacBean, final Class<?> expected
	//test positivo
	@Test
	public void AlertAnimaniacsTest1() {
		this.templateAlertAnimaniacs("admin", "animaniac1", null);
	}
	//veterinario baneado
	@Test
	public void AlertAnimaniacsTest2() {
		this.templateAlertAnimaniacs("admin", "vetvet6", NumberFormatException.class);
	}
	//sin usuario de login
	@Test
	public void AlertAnimaniacsTest3() {
		this.templateAlertAnimaniacs(null, "animaniac1", IllegalArgumentException.class);
	}
	//logeado como no administrador
	@Test
	public void AlertAnimaniacsTest4() {
		this.templateAlertAnimaniacs("animaniac1", "animaniac1", NullPointerException.class);
	}

	// Ancillary methods ------------------------------------------------------

	protected void templateSendMessage(final String actorSenderBean, final String actorRecipientBean, final String subject, final String text, final Class<?> expected) {
		Class<?> caught;
		final Message message;
		caught = null;
		Integer recipientId = null;
		final List<Attachment> attachments = new ArrayList<Attachment>();
		try {
			this.authenticate(actorSenderBean);
			recipientId = this.extract(actorRecipientBean);
			message = this.messageService.create(recipientId);
			message.setSubject(subject);
			message.setText(text);

			this.messageService.save(message, attachments);
			this.messageService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	protected void templateMoveMessage(final String actorBean, final String messageBean, final String FolderBean, final Class<?> expected) {
		Class<?> caught;
		final Message message;
		caught = null;
		final Folder folder;
		try {
			this.authenticate(actorBean);
			message = this.messageService.findOne(this.extract(messageBean));
			folder = this.folderService.findOne(this.extract(FolderBean));
			message.setFolder(folder);
			this.messageService.moveMessage(message);
			this.messageService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}

	protected void templateReplyMessage(final String actorBean, final String messageBean, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		@SuppressWarnings("unused")
		final MessageForm form;
		try {
			this.authenticate(actorBean);
			form = this.messageService.replyMessage(this.extract(messageBean));

			//message = this.messageService.reconstruct(form, this.bindingResult);
			//no se como sacar el BindingResult
			this.messageService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	protected void templateForwardMessage(final String actorBean, final String messageBean, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		@SuppressWarnings("unused")
		final MessageForm form;
		try {
			this.authenticate(actorBean);
			form = this.messageService.forwardMessage(this.extract(messageBean));

			//message = this.messageService.reconstruct(form, this.bindingResult);
			//no se como sacar el BindingResult
			this.messageService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
	protected void templateAlertAnimaniacs(final String administratorBean, final String bannedAnimaniacBean, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			this.authenticate(administratorBean);
			this.messageService.alertAnimaniacs(this.extract(bannedAnimaniacBean));
			this.messageService.flush();
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		this.checkExceptions(expected, caught);
	}
}
