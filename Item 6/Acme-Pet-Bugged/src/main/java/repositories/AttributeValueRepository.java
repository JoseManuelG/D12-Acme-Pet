
package repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.AttributeValue;

public interface AttributeValueRepository extends JpaRepository<AttributeValue, Integer> {

	//Find all the sent chirps for a given actor
	@Query("select a from AttributeValue a where a.pet.id=?1")
	public List<AttributeValue> findAttributeValuesOfPet(int petId);

	//Find all the sent chirps for a given actor
	@Query("select a from AttributeValue a where a.pet.id=?1")
	public Collection<AttributeValue> findAttributeValuesOfPetDeleting(int id);

	@Query("select a from AttributeValue a where a.attribute.id=?1")
	public Collection<AttributeValue> findAllFromAttribute(int attributeId);

}
