
package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ActorRepository;
import repositories.DashboardRepository;
import repositories.PetRepository;
import domain.Animaniac;
import domain.Partner;

@Service
@Transactional
public class DashboardService {

	// Managed Repository --------------------------------------
	@Autowired
	private DashboardRepository		dashboardRepository;

	@Autowired
	private ActorRepository			actorRepository;

	@Autowired
	private CommentableRepository	commentableRepository;

	@Autowired
	private PetRepository			petRepository;


	//Dashboard - 01
	public Double averageOfMessagesReceivedPerActor() {
		Long actors;
		Double messages;
		Double res;

		actors = this.actorRepository.count();
		messages = this.dashboardRepository.averageOfMessagesReceivedPerActor();

		if (actors > 0)
			res = messages / actors;
		else
			res = 0.;

		return res;
	}

	//Dashboard - 01
	public Integer minOfMessagesReceivedPerActor() {
		Long actors;
		List<Long> messages;
		Integer res;

		actors = this.actorRepository.count();
		messages = new ArrayList<Long>();
		messages = this.dashboardRepository.minOfMessagesReceivedPerActor();

		if (actors > 0 && messages.size() == actors)
			res = messages.get(0).intValue();
		else
			res = 0;

		return res;
	}

	//Dashboard - 01
	public Integer maxOfMessagesReceivedPerActor() {
		List<Long> messages;
		Integer res;

		messages = new ArrayList<Long>();
		messages = this.dashboardRepository.maxOfMessagesReceivedPerActor();

		if (messages.size() > 0)
			res = messages.get(0).intValue();
		else
			res = 0;

		return res;
	}

	//Dashboard - 02
	public Double averageOfMessagesSentPerActor() {
		Long actors;
		Double messages;
		Double res;

		actors = this.actorRepository.count();
		messages = this.dashboardRepository.averageOfMessagesSentPerActor();

		if (actors > 0)
			res = messages / actors;
		else
			res = 0.;

		return res;
	}

	//Dashboard - 02
	public Integer minOfMessagesSentPerActor() {
		Long actors;
		List<Long> messages;
		Integer res;

		actors = this.actorRepository.count();
		messages = new ArrayList<Long>();
		messages = this.dashboardRepository.minOfMessagesSentPerActor();

		if (actors > 0 && messages.size() == actors)
			res = messages.get(0).intValue();
		else
			res = 0;

		return res;
	}

	//Dashboard - 02
	public Integer maxOfMessagesSentPerActor() {
		List<Long> messages;
		Integer res;

		messages = new ArrayList<Long>();
		messages = this.dashboardRepository.maxOfMessagesSentPerActor();

		if (messages.size() > 0)
			res = messages.get(0).intValue();
		else
			res = 0;

		return res;
	}
	//Dashboard - 03
	public Double averageOfCommentsWrittenPerActor() {
		Long actors;
		Double comments;
		Double res;

		actors = this.actorRepository.count();
		comments = this.dashboardRepository.averageOfCommentsWrittenPerActor();

		if (actors > 0)
			res = comments / actors;
		else
			res = 0.;

		return res;
	}

	//Dashboard - 03
	public Integer minOfCommentsWrittenPerActor() {
		Long actors;
		List<Long> comments;
		Integer res;

		actors = this.actorRepository.count();
		comments = new ArrayList<Long>();
		comments = this.dashboardRepository.minOfCommentsWrittenPerActor();

		if (actors > 0 && comments.size() == actors)
			res = comments.get(0).intValue();
		else
			res = 0;

		return res;
	}

	//Dashboard - 03
	public Integer maxOfCommentsWrittenPerActor() {
		List<Long> comments;
		Integer res;

		comments = new ArrayList<Long>();
		comments = this.dashboardRepository.maxOfCommentsWrittenPerActor();

		if (comments.size() > 0)
			res = comments.get(0).intValue();
		else
			res = 0;

		return res;
	}

	//Dashboard - 04
	public Double averageOfCommentsWrittenInCommentable() {
		Long commentables;
		Double comments;
		Double res;
		commentables = this.commentableRepository.count();
		comments = this.dashboardRepository.averageOfCommentsWrittenInCommentable();

		if (commentables > 0)
			res = comments / commentables;
		else
			res = 0.;

		return res;
	}

	//Dashoard - 05
	public Double countReportedAnimaniacs() {
		Integer reportedAnimaniacs;
		Integer nonReportedAnimaniacs;
		Double res;

		reportedAnimaniacs = this.dashboardRepository.reportsByAnimaniac().size();
		nonReportedAnimaniacs = this.animaniacRepository.count() - reportedAnimaniacs;

		if (nonReportedAnimaniacs > 0)
			res = 1.0 * (reportedAnimaniacs / nonReportedAnimaniacs);
		else
			res = 0.;

		return res;
	}

	//Dashboard - 06
	public List<Animaniac> animaniacsByReports() {
		List<Animaniac> res;

		res = this.dashboardRepository.animaniacsByReports();

		return res;
	}

	//Dashboard - 07
	public Integer totalBannersDisplayed() {
		Integer res;

		res = this.dashboardRepository.totalBannersDisplayed();

		return res;
	}

	//Dashboard - 08
	public List<Animaniac> animaniacsWith10PercentMoreAcceptedApplicationsThanAvg() {
		List<Animaniac> res;

		res = this.dashboardRepository.animaniacsWith10PercentMoreAcceptedApplicationsThanAvg();

		return res;
	}

	//Dashboard - 09
	public List<Animaniac> top3PopularAnimaniacs() {
		final List<Animaniac> res;
		List<Animaniac> aux;

		aux = this.dashboardRepository.animaniacsSortedByPopularityDesc();
		res.add(aux.get(0));
		res.add(aux.get(1));
		res.add(aux.get(2));

		return res;
	}

	//Dashboard - 10
	public List<Animaniac> top3AnimaniacsByPetNumber() {
		final List<Animaniac> res;
		List<Animaniac> aux;

		aux = this.dashboardRepository.animaniacsSortedByPetNumberDesc();
		res.add(aux.get(0));
		res.add(aux.get(1));
		res.add(aux.get(2));

		return res;
	}

	//Dashboard - 11
	public Partner partnerWithMoreBanners() {
		final Partner res;
		List<Partner> partners;

		partners = this.dashboardRepository.partnersSortedByBannerNumberDesc();
		res = partners.get(0);

		return res;
	}

	//Dashboard - 12
	public Partner partnerWithHighestFee() {
		final Partner res;

		res = this.dashboardRepository.partnerWithHighestFee();

		return res;
	}

	//Dashboard - 13
	public Double certifiedPetRatio() {
		final Integer certifiedPets;
		Long pets;
		final Double res;

		certifiedPets = this.dashboardRepository.numberOfCertificatedPets();
		pets = this.petRepository.count();

		if (pets > 0)
			res = 1.0 * (certifiedPets / pets);
		else
			res = 0.;
	}

	//Dashboard -14
	public List<Animaniac> animaniacsWithMorePets() {
		List<Animaniac> res;

		res = this.dashboardRepository.animaniacsWithMorePets();

		return res;
	}
}
