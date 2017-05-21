
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ApplicationRepository;
import domain.Animaniac;
import domain.Application;
import domain.Request;

@Service
@Transactional
public class ApplicationService {

	//Managed Repository--------------------------------------------------------------------
	@Autowired
	private ApplicationRepository	applicationRepository;

	//Supported Services--------------------------------------------------------------------
	@Autowired
	private AnimaniacService		animaniacService;
	@Autowired
	private RequestService			requestService;
	@Autowired
	private Validator				validator;


	//Simple CRUD methods-------------------------------------------------------------------

	public Application findOne(final int applicationId) {
		return this.applicationRepository.findOne(applicationId);
	}

	public Application create(final int requestId) {
		Application application;
		Animaniac principal;
		Request request;

		application = new Application();
		principal = this.animaniacService.findAnimaniacByPrincipal();
		request = this.requestService.findOne(requestId);

		application.setAnimaniac(principal);
		application.setRequest(request);
		application.setState("Pending");

		return application;
	}

	public Application save(final Application application) {
		Application result;

		Assert.isTrue(!this.checkRequestOwner(application.getRequest().getId()));
		Assert.isTrue(application.getRequest().getStartDate().after(new Date()), "application.error.start.date");

		//TODO: comprobar que no existe ya una application para la misma request y animaniac.
		result = this.applicationRepository.save(application);

		return result;

	}

	//Other Business methods-------------------------------------------------------------------

	public void deleteFromAnimaniac(final Animaniac animaniac) {
		Collection<Application> applications;

		applications = this.applicationRepository.findByAnimaniac(animaniac.getId());

		this.applicationRepository.delete(applications);
	}

	public Collection<Application> findApplicationsByAnimaniac(final int animaniacId) {
		return this.applicationRepository.findByAnimaniac(animaniacId);
	}

	public Application findAcceptedApplicationForRequest(final Request request) {
		return this.applicationRepository.findAcceptedApplicationForRequest(request.getId());
	}

	public void deleteFromRequest(final Request request) {
		Collection<Application> applications;

		applications = this.applicationRepository.findByRequest(request.getId());

		this.applicationRepository.delete(applications);

	}

	public Collection<Application> findAllFromPrincipal() {
		Animaniac principal;

		principal = this.animaniacService.findAnimaniacByPrincipal();

		return this.applicationRepository.findAllFromAnimaniac(principal.getId());
	}

	public Collection<Application> findFromRequest(final int requestId) {

		Assert.isTrue(this.checkRequestOwner(requestId));

		return this.applicationRepository.findFromRequest(requestId);
	}

	public Application reconstruct(final Application application, final BindingResult binding) {
		Application result;

		result = this.create(application.getRequest().getId());
		result.setDescription(application.getDescription());

		this.validator.validate(result, binding);

		return result;

	}

	public void accept(final int applicationId) {
		Application application;
		Collection<Application> applicationsDenied;

		application = this.findOne(applicationId);
		Assert.isTrue(this.checkRequestOwner(application.getRequest().getId()));

		applicationsDenied = this.findFromRequest(application.getRequest().getId());
		applicationsDenied.remove(application);

		application.setState("Accepted");
		this.applicationRepository.save(application);

		for (final Application denied : applicationsDenied) {
			denied.setState("Denied");
			this.applicationRepository.save(denied);
		}
	}

	public void deny(final int applicationId) {
		Application application;

		application = this.findOne(applicationId);
		Assert.isTrue(this.checkRequestOwner(application.getRequest().getId()));

		application.setState("Denied");
		this.applicationRepository.save(application);
	}

	public Boolean checkRequestOwner(final int requestId) {
		Animaniac principal;
		Request request;

		request = this.requestService.findOne(requestId);
		principal = this.animaniacService.findAnimaniacByPrincipal();

		return request.getPets().iterator().next().getAnimaniac().equals(principal);
	}
}
