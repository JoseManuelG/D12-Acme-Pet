
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RequestRepository;
import domain.Animaniac;
import domain.Pet;
import domain.Request;

@Service
@Transactional
public class RequestService {

	// Managed Repository --------------------------------------
	@Autowired
	private RequestRepository	requestRepository;

	// Supporting Services --------------------------------------

	@Autowired
	private AnimaniacService	animaniacService;

	@Autowired
	private ApplicationService	applicationService;


	//Simple CRUD methods-------------------------------------------

	public Request create() {
		Collection<Pet> pets;
		Request request;

		request = new Request();
		pets = new HashSet<Pet>();

		request.setPets(pets);

		return request;
	}

	public Request findOne(final int id) {
		Request result;
		result = this.requestRepository.findOne(id);
		return result;
	}

	public Collection<Request> findAll() {
		return this.requestRepository.findAll();
	}

	public Request save(final Request request) {
		Request result;

		result = this.requestRepository.save(request);
		return result;
	}

	public void delete(final int requestId) {
		Request request;
		Boolean active;
		Animaniac principal, owner;

		request = this.findOne(requestId);
		active = this.checkNotActiveRequest(request);
		principal = this.animaniacService.findAnimaniacByPrincipal();
		owner = request.getPets().iterator().next().getAnimaniac();

		Assert.isTrue(owner.equals(principal));
		Assert.isTrue(active, "request.error.active");
		Assert.isTrue(request.getId() != 0);

		this.requestRepository.delete(request);
	}

	// other business methods --------------------------------------

	public Collection<Request> findAllFromPrincipal() {
		Collection<Request> requests;
		Animaniac principal;

		principal = this.animaniacService.findAnimaniacByPrincipal();

		requests = this.requestRepository.findFromAnimaniacId(principal.getId());

		return requests;
	}

	public Boolean checkNotActiveRequest(final Request request) {
		Date timeToDelete;
		long now, day;
		Boolean hasAcceptedApplication;

		now = System.currentTimeMillis();
		day = 24 * 60 * 60 * 100;
		timeToDelete = new Date(now + 5 * day);

		hasAcceptedApplication = this.applicationService.findAcceptedApplicationForRequest(request) != null;

		return !hasAcceptedApplication || (request.getEndDate().before(timeToDelete));

	}
}
