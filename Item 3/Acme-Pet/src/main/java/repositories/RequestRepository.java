
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Request;

public interface RequestRepository extends JpaRepository<Request, Integer> {

	@Query("select r from Request r join r.pets p where p.animaniac.id = ?1 group by r")
	Collection<Request> findFromAnimaniacId(int animaniacId);

}
