
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.CommentRepository;
import domain.Animaniac;
import domain.Comment;
import domain.Commentable;
import domain.Pet;

@Service
@Transactional
public class CommentService {

	//Managed Repository--------------------------------------------------------------------
	@Autowired
	private CommentRepository	commentRepository;

	//Supported Services--------------------------------------------------------------------
	@Autowired
	private AnimaniacService	animaniacService;
	@Autowired
	private Validator			validator;


	//Simple CRUD methods-------------------------------------------------------------------

	public Comment create(final Commentable commentable) {
		Comment result;
		Animaniac principal;
		Assert.notNull(commentable, "El commentable no debe ser nulo");
		Assert.isTrue(commentable.getId() != 0);
		principal = this.animaniacService.findAnimaniacByPrincipal();

		result = new Comment();
		result.setAnimaniac(principal);
		result.setCommentable(commentable);

		return result;
	}

	public Comment findOne(final int commentId) {
		Comment result;

		result = this.commentRepository.findOne(commentId);
		Assert.notNull(result);

		return result;
	}

	public Comment save(final Comment comment) {
		Date currentMoment;
		Comment result;

		Assert.notNull(comment, "comment.null.error");
		Assert.isTrue(comment.getId() == 0, "comment.edit.error");
		Assert.isTrue(comment.getAnimaniac().equals(this.animaniacService.findAnimaniacByPrincipal()), "comment.principal.error");

		currentMoment = new Date(System.currentTimeMillis() - 10000);
		comment.setPostMoment(currentMoment);

		result = this.commentRepository.save(comment);

		Assert.notNull(result, "comment.commit.error");

		return result;
	}
	public void delete(final Comment comment) {
		Assert.notNull(comment, "El comment no puede ser nulo");
		Assert.isTrue(comment.getId() != 0, "El comment debe estar antes en la base de datos");

		this.commentRepository.exists(comment.getId());
		Assert.isTrue(this.animaniacService.findAnimaniacByPrincipal().equals(comment.getAnimaniac()));

		this.commentRepository.delete(comment);

	}

	//Other Business methods-------------------------------------------------------------------
	public Comment reconstruct(final Comment comment, final BindingResult binding) {
		Comment result;

		result = this.create(comment.getCommentable());

		// Setear lo que viene del formulario:
		result.setBody(comment.getBody());
		result.setTitle(comment.getTitle());

		// Setear lo que no viene del formulario:
		result.setPostMoment(new Date(System.currentTimeMillis() - 100));
		result.setId(comment.getId());
		result.setVersion(comment.getVersion());
		this.validator.validate(result, binding);
		return result;
	}
	public Collection<Comment> findAllCommentsByCommentable(final int commentableId) {
		Collection<Comment> result;
		result = this.commentRepository.findAllCommentsByCommentableId(commentableId);
		return result;
	}

	public Collection<Comment> findAllCommentsByAnimaniac(final int animaniacId) {
		Collection<Comment> result;
		result = this.commentRepository.findAllCommentsByAnimaniacId(animaniacId);
		return result;
	}

	public void deleteAllCommentsOfAnimaniac(final Animaniac animaniac) {
		Collection<Comment> comments;

		this.animaniacService.findAll();

		comments = this.findAllCommentsByAnimaniac(animaniac.getId());
		if (comments != null)
			this.commentRepository.delete(comments);

		comments = this.findAllCommentsByCommentable(animaniac.getId());
		if (comments != null)
			this.commentRepository.delete(comments);

	}

	public void deleteAllCommentsOfPet(final Pet pet) {
		this.commentRepository.delete(this.findAllCommentsByCommentable(pet.getId()));

	}
}
