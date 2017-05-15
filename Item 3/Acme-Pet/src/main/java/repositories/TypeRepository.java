
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {

	@Query("select count(p) from Pet p where p.type.id=?1")
	long countPetsWithType(int typeId);

}
