
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {

	//Find all the sendt chirps for a given actor
	@Query("select a from Photo a where a.pet.id=?1")
	public List<Photo> findPhotosOfPet(int petId);

	//Find all the sendt chirps for a given actor
	@Query("select a from Photo a where a.pet.id=?1")
	public Collection<Photo> findPhotosOfPetDeleting(int id);

}
