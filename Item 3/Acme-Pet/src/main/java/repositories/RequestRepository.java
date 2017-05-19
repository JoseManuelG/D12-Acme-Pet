
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Request;

public interface RequestRepository extends JpaRepository<Request, Integer> {

	@Query("select r from Request r join r.pets p where p.animaniac.id = ?1 group by r")
	Collection<Request> findFromAnimaniacId(int animaniacId);

	@Query("select r from Request r where ?1 member of r.pets")
	Collection<Request> findByPet(int id);

	@Query("select r from Request r where r.address = ?1 and r.startDate >= ?3 and r.endDate <=?4")
	Collection<Request> searchRequest(String address, String type, Date startDate, Date endDate);

	@Query("select r from Request r where r.address = ?1 and r.startDate >= ?3 and r.endDate <=?4")
	Collection<Request> searchRequest(String address, Date startDate, Date endDate);

}
