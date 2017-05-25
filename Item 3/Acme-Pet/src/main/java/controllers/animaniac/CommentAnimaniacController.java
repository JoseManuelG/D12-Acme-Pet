
package controllers.animaniac;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.CommentableService;
import controllers.AbstractController;
import controllers.AnimaniacController;
import domain.Animaniac;
import domain.Comment;
import domain.Commentable;
import domain.Pet;

@Controller
@RequestMapping("/comment")
public class CommentAnimaniacController extends AbstractController {

	@Autowired
	private CommentService			commentService;
	@Autowired
	private CommentableService		commentableService;

	@Autowired
	private AnimaniacController		animaniacController;
	@Autowired
	private PetAnimaniacController	petController;


	// List --------------------------------------------------------------------

	// Create --------------------------------------------------------------------
	@RequestMapping(value = "/animaniac/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int commentableId) {
		final Commentable commentable;
		ModelAndView result;
		Comment comment;
		String requestURI = "";
		String cancelURI = "";

		commentable = this.commentableService.findOne(commentableId);

		comment = this.commentService.create(commentable);

		result = this.createEditModelAndView(comment);

		requestURI = "comment/animaniac/create.do?animaniacId=" + comment.getCommentable().getId();
		if (Animaniac.class.equals(comment.getCommentable().getClass()))
			cancelURI = "animaniac/view.do?animaniacId=" + comment.getCommentable().getId();
		if (Pet.class.equals(comment.getCommentable().getClass()))
			cancelURI = "pet/animaniac/view.do?petId=" + comment.getCommentable().getId();
		result.addObject("cancelURI", cancelURI);
		result.addObject("requestURI", requestURI);
		result.addObject("comment", comment);
		return result;

	}
	// Save ---------------------------------------------------------------
	@RequestMapping(value = "/animaniac/create", method = RequestMethod.POST, params = "save")
	public @ResponseBody
	ModelAndView save(final Comment comment, final BindingResult binding) {
		ModelAndView result = null;
		Comment commentReconstructed;

		commentReconstructed = this.commentService.reconstruct(comment, binding);
		if (binding.hasErrors())
			result = this.createEditModelAndView(comment);
		else
			try {

				this.commentService.save(commentReconstructed);
				if (Animaniac.class.equals(comment.getCommentable().getClass()))
					result = this.animaniacController.view(comment.getCommentable().getId());
				if (Pet.class.equals(comment.getCommentable().getClass()))
					result = this.petController.view(comment.getCommentable().getId());

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(comment, oops.getMessage());
			}

		return result;
	}

	// Delete ---------------------------------------------------------------
	@RequestMapping(value = "/animaniac/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int commentId) {
		ModelAndView result = null;
		Comment comment;
		final Collection<Comment> res;

		comment = this.commentService.findOne(commentId);

		this.commentService.delete(comment);

		if (Animaniac.class.equals(comment.getCommentable().getClass()))
			result = this.animaniacController.view(comment.getCommentable().getId());
		if (Pet.class.equals(comment.getCommentable().getClass()))
			result = this.petController.view(comment.getCommentable().getId());

		return result;
	}
	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Comment comment) {
		ModelAndView result;

		result = this.createEditModelAndView(comment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Comment comment, final String message) {
		ModelAndView result;
		result = new ModelAndView("comment/animaniac/create");

		result.addObject("comment", comment);
		result.addObject("message", message);
		return result;
	}

}
