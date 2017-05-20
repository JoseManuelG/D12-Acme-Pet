
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

	@Autowired
	private SearchEngineService	searchEngineService;


	//Simple CRUD methods-------------------------------------------

	public Request create() {
		Collection<Pet> pets;
		Request request;
		Animaniac principal;

		request = new Request();
		pets = new HashSet<Pet>();
		principal = this.animaniacService.findAnimaniacByPrincipal();

		request.setPets(pets);
		request.setAddress(principal.getAddress());

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

		Assert.isTrue(request.getStartDate().after(new Date()), "request.error.start.date");
		Assert.isTrue(request.getStartDate().before(request.getEndDate()), "request.error.end.date");
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

		Assert.isTrue(owner.equals(principal), "request.error.not.owner");
		Assert.isTrue(active, "request.error.active");
		Assert.isTrue(request.getId() != 0);

		this.applicationService.deleteFromRequest(request);
		this.searchEngineService.deleteFromRequest(request);
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

	public void deleteFromPet(final Pet pet) {
		Collection<Request> requests;

		requests = this.requestRepository.findByPet(pet.getId());

		for (final Request request : requests) {
			Assert.isTrue(this.checkNotActiveRequest(request), "request.error.active.pet");

			this.applicationService.deleteFromRequest(request);
			this.searchEngineService.deleteFromRequest(request);
			this.requestRepository.delete(request);

		}
	}

	public void deleteFromPetFromAnimaniac(final Pet pet) {
		Collection<Request> requests;

		requests = this.requestRepository.findByPet(pet.getId());

		for (final Request request : requests) {
			Assert.isTrue(this.checkNotActiveRequest(request), "request.error.active.animaniac");

			this.applicationService.deleteFromRequest(request);
			this.searchEngineService.deleteFromRequest(request);
			this.requestRepository.delete(request);

		}
	}

	public Collection<Request> searchRequest(final String address, final String type, final Date startDate, final Date endDate) {
		// TODO Auto-generated method stub
		return this.requestRepository.searchRequest(address, type, startDate, endDate);
	}
	public Collection<Request> searchRequest(final String address, final Date startDate, final Date endDate) {
		// TODO Auto-generated method stub
		return this.requestRepository.searchRequest(address, startDate, endDate);
	}
}
