
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.RequestRepository;
import domain.Animaniac;
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


	// other business methods --------------------------------------

	public Collection<Request> findAll() {
		return this.requestRepository.findAll();
	}

	public Collection<Request> findAllFromPrincipal() {
		Collection<Request> requests;
		Animaniac principal;

		principal = this.animaniacService.findAnimaniacByPrincipal();

		requests = this.requestRepository.findFromAnimaniacId(principal.getId());

		return requests;
	}
}
