
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	//TODO: Excluir aquellos cullos commentable sean animaniacs baneados.
	// ¡Cuidado! Si se hace esto, hacer dos queries, ya que esta se usa al eliminar la cuenta
	@Query("select c from Comment c where c.commentable.id=?1")
	Collection<Comment> findAllCommentsByCommentableId(int id);

	@Query("select c from Comment c where c.animaniac.id=?1")
	Collection<Comment> findAllCommentsByAnimaniacId(int id);
}
