
package services;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.MessageRepository;
import domain.Actor;
import domain.Attachment;
import domain.Folder;
import domain.Message;
import forms.MessageForm;

@Service
@Transactional
public class MessageService {

	//Managed Repository--------------------------------------------------------------------
	@Autowired
	private MessageRepository	messageRepository;

	//Supported Services--------------------------------------------------------------------
	@Autowired
	private ActorService		actorService;
	@Autowired
	private FolderService		folderService;
	@Autowired
	private AttachmentService	attachmentService;
	@Autowired
	private Validator			validator;


	//Simple CRUD methods------------------------------------------------------------------
	public Message create(final int recipientId) {
		final Message result = new Message();
		Actor recipient;
		final Actor sender;
		final Folder senderFolder;

		recipient = this.actorService.findOne(recipientId);
		Assert.notNull(recipient);
		//Settear recipient
		result.setRecipient(recipient);

		//Encontrar la carpeta del sender
		sender = this.actorService.findActorByPrincipal();
		result.setSender(sender);
		senderFolder = this.folderService.findFolderOfActor(sender, "outbox");
		result.setFolder(senderFolder);

		//nombres de los actores
		result.setRecipientName(recipient.getName());
		result.setSenderName(sender.getName());

		//Momento de enviado
		result.setSendingMoment(new Date(System.currentTimeMillis() - 1000));

		return result;
	}
	public Message findOne(final int messageId) {

		Assert.notNull(messageId, "No Puedes Encontrar un mensaje sin ID");
		Assert.isTrue(messageId >= 0, "La Id no es valida");

		final Message result = this.messageRepository.findOne(messageId);
		//añadido assert para comprobar que el mensaje es suyo,
		//su copia ya sea la de enviado cuando eres el que envia o al recibido
		//el findOne se usa en view, reply, forward... etc asi que cubre todo

		Assert.notNull(result);
		Assert.isTrue(result.getFolder().getActor().equals(this.actorService.findActorByPrincipal()));

		return result;
	}

	//Se entiende que el mensaje tiene como sender el principal
	public Message save(final Message message, final Collection<Attachment> attachments) {
		Message result, copyMessage, savedCopyMessage;
		Actor sender;

		Assert.notNull(message.getRecipient(), "El mensaje debe tener un destinatario");

		Assert.notNull(message.getSender(), "El mensaje debe tener un remitente");

		sender = this.actorService.findActorByPrincipal();

		Assert.isTrue(sender.equals(message.getSender()), "El remitente debe ser el mismo que esta conectado");
		Assert.isTrue(message.getId() == 0, "No puedes editar un mensaje");

		// Creamos copia del mensaje en un segundo mensaje;
		copyMessage = this.copyMessage(message);

		//Se almacena el mensaje original(isSender= true) y sus attachments

		result = this.messageRepository.save(message);
		this.attachmentService.addAttachments(attachments, result);

		//Se almacena el mensaje copia(isSender= false) y sus attachments
		savedCopyMessage = this.messageRepository.save(copyMessage);
		this.attachmentService.addAttachments(attachments, savedCopyMessage);

		return result;
	}

	//Simplemente crea un mensaje nuevo y le settea todos los datos del mensaje de entrada menos el isSender.
	private Message copyMessage(final Message message) {
		final Message result = new Message();
		Folder folder;
		folder = this.folderService.findFolderOfActor(message.getRecipient(), "inbox");

		result.setFolder(folder);

		result.setSender(message.getSender());
		result.setRecipient(message.getRecipient());

		result.setSenderName(message.getSender().getName());
		result.setRecipientName(message.getRecipient().getName());

		result.setText(message.getText());
		result.setSubject(message.getSubject());
		result.setSendingMoment(message.getSendingMoment());
		result.setIsSender(false);

		return result;
	}

	public void delete(final int messageId) {
		final Message message;
		final Actor actor;
		final Folder folder;
		//en vez de pasar chirp pasas la id para no tocar en el controlador, y en el findOne
		//ya se hacen los asserts
		message = this.findOne(messageId);

		Assert.notNull(message);
		Assert.isTrue(message.getFolder().getActor().equals(this.actorService.findActorByPrincipal()));
		if (message.getFolder().getName().equals("trashbox")) {
			this.attachmentService.deleteAttachmentsOfMessage(message);
			this.messageRepository.delete(message);
		} else {
			actor = message.getFolder().getActor();
			folder = this.folderService.findFolderOfActor(actor, "trashbox");
			message.setFolder(folder);
			this.messageRepository.save(message);
		}
	}
	public void deleteAll(final Collection<Message> messages) {
		for (final Message m : messages)
			this.delete(m.getId());
	}
	public void flush() {
		this.messageRepository.flush();
	}

	//Other Bussnisnes methods------------------------------------------------------------
	//Devuelve los mensajes que estan en el folder, ya sean enviados o no por el dueño
	public List<Message> findMessagesInFolder(final int folderId) {

		final List<Message> result = this.messageRepository.findMessagesInFolder(folderId);
		return result;
	}

	public Message reconstruct(final MessageForm messageForm, final BindingResult binding) {
		final Message result = this.create(messageForm.getRecipient().getId());
		result.setText(messageForm.getText());
		result.setSubject(messageForm.getSubject());
		result.setIsSender(true);

		this.validator.validate(result, binding);

		if (!binding.hasErrors())
			for (final Attachment a : messageForm.getAttachments()) {
				a.setMessage(result);
				this.validator.validate(a, binding);
			}
		return result;

	}

	public MessageForm forwardMessage(final int messageId) {
		//Lo he cambiado para que pida chirpId en vez de chirp para no tener que
		//hacer en controlador cosas de servicios
		final Message message = this.findOne(messageId);
		final MessageForm result = new MessageForm();
		final LinkedList<Attachment> attachments = new LinkedList<Attachment>();
		result.setAction(2);
		attachments.addAll(this.attachmentService.copyAttachments(message));
		result.setText(message.getText());
		result.setSubject(message.getSubject());
		result.setAttachments(attachments);
		return result;
	}

	//Para responder el mensaje
	//Lo he cambiado para que pida chirpId en vez de chorbiId para no tener que
	//hacer en controlador cosas de servicios
	public MessageForm replyMessage(final int messageId) {

		final MessageForm result = new MessageForm();
		result.setAction(1);

		final Message message = this.findOne(messageId);
		Assert.notNull(message.getSender());

		final Actor recipient = message.getSender();
		result.setRecipient(recipient);
		return result;
	}

	public void deleteFromChorbi(final Actor actor) {
		final Collection<Message> messages;
		//Todos los mensajes de los que el actor es dueño, enviados o no por el.
		messages = (this.messageRepository.findMessagesOfActor(actor.getId()));

		this.attachmentService.deleteActor(actor);
		this.messageRepository.delete(messages);
		messages.clear();

		//Todos los mensajes de los que el actor es el "sender"
		messages.addAll(this.messageRepository.findMessageWithSender(actor.getId()));
		for (final Message message : messages)
			message.setSender(null);
		this.messageRepository.save(messages);
		//Todos los mensajes de los que el actor es el "recipient"
		messages.addAll(this.messageRepository.findMessageWithRecipient(actor.getId()));
		for (final Message message : messages)
			message.setRecipient(null);
		this.messageRepository.save(messages);
		messages.clear();
	}

}
