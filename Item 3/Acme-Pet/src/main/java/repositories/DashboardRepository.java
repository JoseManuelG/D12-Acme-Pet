
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Animaniac;
import domain.DomainEntity;
import domain.Partner;

@Repository
public interface DashboardRepository extends JpaRepository<DomainEntity, Integer> {

	//Dashboard - 01
	@Query("select count(m) from Message m where m.isSender=false and m.recipient is not null")
	public Double averageOfMessagesReceivedPerActor();

	//Dashboard - 01
	@Query("select count(m) from Message m where m.isSender=false and m.recipient is not null group by m.recipient.id order by count(m) asc")
	public List<Long> minOfMessagesReceivedPerActor();

	//Dashboard - 01
	@Query("select count(m) from Message m where m.isSender=false and m.recipient is not null group by m.recipient.id order by count(m) desc")
	public List<Long> maxOfMessagesReceivedPerActor();

	//Dashboard - 02
	@Query("select count(m) from Message m where m.isSender=true and m.sender is not null")
	public Double averageOfMessagesSentPerActor();

	//Dashboard - 02
	@Query("select count(m) from Message m where m.isSender=true and m.sender is not null group by m.sender.id order by count(m) asc")
	public List<Long> minOfMessagesSentPerActor();

	//Dashboard - 02
	@Query("select count(m) from Message m where m.isSender=true and m.sender is not null group by m.sender.id order by count(m) desc")
	public List<Long> maxOfMessagesSentPerActor();

	//Dashboard - 03
	@Query("select count(c) from Comment c  group by c.animaniac.id order by count(c) asc")
	public List<Long> minOfCommentsWrittenPerActor();

	//Dashboard - 03
	@Query("select count(c) from Comment c  group by c.animaniac.id order by count(c) desc")
	public List<Long> maxOfCommentsWrittenPerActor();

	//Dashboard - 04
	@Query("select count(c) from Comment c  group by c.commentable.id order by count(c) asc")
	public List<Long> minOfCommentsWrittenInCommentable();

	//Dashboard - 04
	@Query("select count(c) from Comment c  group by c.commentable.id order by count(c) desc")
	public List<Long> maxOfCommentsWrittenInCommentable();

	//Dashboard - 05
	@Query("select distinct count(a) from AbuseReport a, in (a.reported) rep group by rep")
	public List<Integer> reportsByAnimaniac();

	//Dashboard - 06
	@Query("select distinct a.reported from AbuseReport a, in (a.reported) rep group by rep")
	public List<Animaniac> animaniacsByReports();

	//Dashboard - 07
	@Query("select sum(p.numDisplay) from Partner p")
	public Integer totalBannersDisplayed();

	//Dashboard - 08
	@Query("select distinct a.animaniac from Application a where(select count(a2) from Application a2 where a.animaniac.id=a2.animaniac.id and a2.state = 'ACCEPTED' ) >= (select count(a3) from Application a3 where a3.state='ACCEPTED')/(select count(a4) from Application a4)*1.1")
	public List<Animaniac> animaniacsWith10PercentMoreAcceptedApplicationsThanAvg();

	//Dashboard - 09
	@Query("select a from Animaniac a order by a.rate desc")
	public List<Animaniac> animaniacsSortedByPopularityDesc();

	//Dashboard - 10
	@Query("select distinct p.animaniac from Pet p group by p.animaniac order by count(p.animaniac) desc")
	public List<Animaniac> animaniacsSortedByPetNumberDesc();

	//Dashboard - 11
	@Query("select distinct b.partner from Banner b group by b.partner order by count(b.partner) desc")
	public List<Partner> partnersSortedByBannerNumberDesc();

	//Dashboard - 12
	@Query("select p from Partner p where p.totalFee = (select max(p2.totalFee) from Partner p2)")
	public Partner partnerWithHighestFee();

	//Dashboard - 13
	@Query("select count(p) from Pet p where p.certificateBy is not null")
	public Integer numberOfCertificatedPets();

	//Dashboard - 14
	@Query("select distinct p.animaniac from Pet p where(select count(p2) from Pet p2 where p.animaniac.id=p2.animaniac.id) >= (select count(p3) from Pet p3)*0.8")
	public List<Animaniac> animaniacsWithMorePets();
}
