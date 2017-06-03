
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Animaniac;

@Repository
public interface AnimaniacRepository extends JpaRepository<Animaniac, Integer> {

	@Query("select a from Animaniac a where a.userAccount.id = ?1")
	public Animaniac findByUserAccountId(int id);

}
