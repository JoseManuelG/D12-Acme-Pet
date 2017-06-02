
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {

	@Query("select p from Pet p where p.animaniac.id = ?1")
	Collection<Pet> findPetsByAnimaniac(int animaniacId);

	@Query("select p from Pet p where p.vet.id = ?1")
	Collection<Pet> findPetsByVet(int id);

	@Query("select p from Pet p where p.animaniac.id = ?1 and p not in (select p2 from Request r join r.pets p2 where p2.animaniac.id = ?1 and r.endDate>?2)")
	Collection<Pet> findAvalaiblePetsFromAnimaniac(int animaniacId, Date now);
}
