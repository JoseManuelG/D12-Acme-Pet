
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import domain.AbuseReport;

public interface AbuseReportRepository extends JpaRepository<AbuseReport, Integer> {

}
