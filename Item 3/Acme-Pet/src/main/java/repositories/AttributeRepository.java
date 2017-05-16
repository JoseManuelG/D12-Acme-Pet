
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Attribute;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer> {

	@Query("select count(a) from AttributeValue a where a.attribute.id=?1")
	long countAttributeValuesWithAttribute(int attributeId);

}
