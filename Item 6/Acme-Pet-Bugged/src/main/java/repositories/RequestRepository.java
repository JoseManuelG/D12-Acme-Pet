
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

	@Query("select r from Request r where r.address like concat('%',?1,'%') and r.startDate >= ?3 and r.endDate <=?4 and r in (select r2 from Request r2 join r2.pets p2 where p2 in (select p3 from Pet p3 where p3.type.id = ?2))")
	Collection<Request> searchRequest(String address, int typeId, Date startDate, Date endDate);
	//	where p2 in (final select p3 from Pet where p3 = ?3)
	//	@Query("select r from Request r where r.address like concat('%',?1,'%') AND and r.startDate <= ?3 and r in (select r2 from Request r2 join r2.pets p2 where p2 in (select p3 from Pet p3 where p3.type.id = ?2))")
	//	Collection<Request> searchRequest(String address, int typeId, Date startDate);

	@Query("select r from Request r where r.address like concat('%',?1,'%') and r.startDate >= ?2 and r.endDate <=?3")
	Collection<Request> searchRequest(String address, Date startDate, Date endDate);

	@Query("select r from Request r join r.pets p where p.animaniac.id = ?1 and CURRENT_DATE between r.startDate and r.endDate group by r")
	Collection<Request> findActiveRequestsFromAnimaniacId(int animaniacId);

}
