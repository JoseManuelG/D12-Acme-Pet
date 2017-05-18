
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	@Query("select a from Application a where a.animaniac.id = ?1")
	Collection<Application> findByAnimaniac(int animaniacId);

	@Query("select a from Application a where a.request.id = ?1 and a.state='Accepted'")
	Application findAcceptedApplicationForRequest(int requestId);

}
