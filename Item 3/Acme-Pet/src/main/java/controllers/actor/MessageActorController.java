/*
 * WelcomeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.actor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AttachmentService;
import services.MessageService;
import controllers.AbstractController;
import domain.Actor;
import domain.Attachment;
import domain.Message;
import forms.MessageForm;

@Controller
@RequestMapping("/message")
public class MessageActorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private MessageService			messageService;

	@Autowired
	private AttachmentService		attachmentService;

	@Autowired
	private ActorService			actorService;
	@Autowired
	private FolderActorController	folderController;


	// Constructors -----------------------------------------------------------

	public MessageActorController() {
		super();
	}

	// Methods -----------------------------------------------------------------

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam final int messageId) {
		ModelAndView result;
		Message res;
		final Collection<Attachment> attachments;

		res = this.messageService.findOne(messageId);
		attachments = this.attachmentService.findAttachmentsOfMessage(res);

		result = new ModelAndView("message/actor/view");
		result.addObject("res", res);
		result.addObject("attachments", attachments);
		result.addObject("requestURI", "message/actor/view.do?messageId=" + messageId);

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int messageId) {
		ModelAndView result;

		this.messageService.delete(messageId);

		result = new ModelAndView("redirect:/folder/list.do");

		return result;
	}

	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public ModelAndView reply(@RequestParam final int messageId) {
		ModelAndView result;
		MessageForm messageForm;

		messageForm = this.messageService.replyMessage(messageId);
		result = this.createEditModelAndView(messageForm);

		return result;
	}

	@RequestMapping(value = "/forward", method = RequestMethod.GET)
	public ModelAndView forward(@RequestParam final int messageId) {
		ModelAndView result;
		MessageForm messageForm;

		messageForm = this.messageService.forwardMessage(messageId);
		result = this.createEditModelAndView(messageForm);

		return result;
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public ModelAndView write() {
		ModelAndView result;
		MessageForm messageForm;
		Collection<Actor> actors;

		messageForm = new MessageForm();
		actors = this.actorService.findAll();
		result = this.createEditModelAndView(messageForm);
		result.addObject("actors", actors);

		return result;
	}
	@RequestMapping(value = "/write", method = RequestMethod.POST, params = "addAttachment")
	public ModelAndView addAttachment(final MessageForm messageForm) {
		ModelAndView result;

		messageForm.addAttachmentSpace();

		result = this.createEditModelAndView(messageForm);

		return result;
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST, params = "removeAttachment")
	public ModelAndView removeAttachment(final MessageForm messageForm) {
		ModelAndView result;

		messageForm.removeAttachmentSpace();

		result = this.createEditModelAndView(messageForm);

		return result;
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST, params = "save")
	public ModelAndView write(final MessageForm messageForm, final BindingResult bindingResult) {
		ModelAndView result;
		Message message;
		String error;

		message = this.messageService.reconstruct(messageForm, bindingResult);
		if (bindingResult.hasErrors()) {
			error = null;
			if (bindingResult.hasFieldErrors("url"))
				error = "message.url.error";
			result = this.createEditModelAndView(messageForm, error);
		} else
			try {
				this.messageService.save(message, messageForm.getAttachments());
				result = new ModelAndView("redirect:../folder/view.do?folderId=" + message.getFolder().getId());
			} catch (final IllegalArgumentException e) {
				result = this.createEditModelAndView(messageForm, e.getMessage());
			}

		return result;
	}
	///////////////////////////////////////////

	protected ModelAndView createEditModelAndView(final MessageForm messageForm) {
		ModelAndView result;

		result = this.createEditModelAndView(messageForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final MessageForm messageForm, final String message) {
		ModelAndView result;
		Collection<Actor> actors;

		result = new ModelAndView("message/actor/write");
		actors = this.actorService.findAll();

		result.addObject("actors", actors);
		result.addObject("messageForm", messageForm);
		result.addObject("message", message);
		result.addObject("requestURI", "message/write.do");

		return result;
	}

}
