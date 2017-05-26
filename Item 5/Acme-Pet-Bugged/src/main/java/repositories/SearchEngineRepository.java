
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.SearchEngine;

public interface SearchEngineRepository extends JpaRepository<SearchEngine, Integer> {

	@Query("select s from SearchEngine s where s.animaniac.id = ?1")
	SearchEngine findByAnimaniac(int animaniacId);

	@Query("select s from SearchEngine s where ?1 member of s.requests")
	Collection<SearchEngine> findByRequest(int requestId);

}
