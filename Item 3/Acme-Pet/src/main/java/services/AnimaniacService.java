
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.AnimaniacRepository;
import security.LoginService;
import domain.Animaniac;

@Service
@Transactional
public class AnimaniacService {

	// Managed Repository --------------------------------------
	@Autowired
	private AnimaniacRepository	animaniacRepository;


	// Supporting Services --------------------------------------

	//Simple CRUD methods-------------------------------------------------------------------

	public Animaniac findOne(final int actorId) {
		return this.animaniacRepository.findOne(actorId);
	}

	public Collection<Animaniac> findAll() {
		return this.animaniacRepository.findAll();
	}

	public Long count() {
		return this.animaniacRepository.count();
	}
	// other business methods --------------------------------------

	public Animaniac findAnimaniacByPrincipal() {
		Animaniac result;
		result = this.animaniacRepository.findByUserAccountId(LoginService.getPrincipal().getId());
		return result;
	}

}
