
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Attribute;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer> {

	@Query("select count(a) from AttributeValue a where a.attribute.id=?1")
	long countAttributeValuesWithAttribute(int attributeId);

	@Query("select a from Attribute a where a.type.id=?1")
	Collection<Attribute> attributtesWithType(int typeId);

	@Query("select a from Attribute a where a.name=?1 and a.type.id=?2")
	Attribute findByNameAndType(String attributeName, int typeId);
}
