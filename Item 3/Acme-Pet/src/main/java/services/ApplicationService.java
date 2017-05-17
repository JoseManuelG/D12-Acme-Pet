
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ApplicationRepository;
import domain.Animaniac;
import domain.Application;

@Service
@Transactional
public class ApplicationService {

	//Managed Repository--------------------------------------------------------------------
	@Autowired
	private ApplicationRepository	applicationRepository;


	//Supported Services--------------------------------------------------------------------

	//Simple CRUD methods-------------------------------------------------------------------

	//Other Business methods-------------------------------------------------------------------

	public void deleteFromAnimaniac(final Animaniac animaniac) {
		Collection<Application> applications;

		applications = this.applicationRepository.findByAnimaniac(animaniac.getId());

		this.applicationRepository.delete(applications);
	}

	public Collection<Application> findApplicationsByAnimaniac(final int animaniacId) {
		return this.applicationRepository.findByAnimaniac(animaniacId);
	}
}
