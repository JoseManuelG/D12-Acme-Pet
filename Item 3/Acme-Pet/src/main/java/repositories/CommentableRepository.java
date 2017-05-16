
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Animaniac;

@Repository
public interface CommentableRepository extends JpaRepository<Animaniac, Integer> {

}
