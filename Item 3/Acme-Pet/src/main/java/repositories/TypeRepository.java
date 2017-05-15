
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {

}
