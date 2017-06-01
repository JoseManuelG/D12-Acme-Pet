
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Vet;

@Repository
public interface VetRepository extends JpaRepository<Vet, Integer> {

	@Query("select v from Vet v where v.userAccount.id = ?1")
	public Vet findByUserAccountId(int id);

}
