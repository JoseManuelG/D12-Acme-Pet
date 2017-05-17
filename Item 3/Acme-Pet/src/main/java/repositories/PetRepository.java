
package repositories;

import java.util.Collection;

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

}
