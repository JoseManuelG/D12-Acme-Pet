
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.CommentRepository;
import domain.Animaniac;
import domain.Comment;

@Service
@Transactional
public class CommentService {

	//Managed Repository--------------------------------------------------------------------
	@Autowired
	private CommentRepository	commentRepository;


	//Supported Services--------------------------------------------------------------------

	//Simple CRUD methods-------------------------------------------------------------------

	//Other Business methods-------------------------------------------------------------------
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
		this.commentRepository.delete(this.findAllCommentsByAnimaniac(animaniac.getId()));
		this.commentRepository.delete(this.findAllCommentsByCommentable(animaniac.getId()));

	}
}
