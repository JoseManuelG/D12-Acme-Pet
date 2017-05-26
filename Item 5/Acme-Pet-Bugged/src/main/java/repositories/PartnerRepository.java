
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer> {

	@Query("select p from Partner p where p.userAccount.id = ?1")
	public Partner findByUserAccountId(int id);

}
