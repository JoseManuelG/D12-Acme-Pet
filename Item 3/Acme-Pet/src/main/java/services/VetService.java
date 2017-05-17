
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.VetRepository;
import security.Authority;
import security.LoginService;
import domain.Vet;
import forms.VetForm;

@Service
@Transactional
public class VetService {

	// Managed Repository --------------------------------------
	@Autowired
	private VetRepository		vetRepository;

	// Supporting Services --------------------------------------
	@Autowired
	private ActorService		actorService;

	@Autowired
	private UserAccountService	accountService;

	@Autowired
	private PetService			petService;

	@Autowired
	private Validator			validator;


	//Simple CRUD methods-------------------------------------------------------------------

	public Vet create() {
		Vet result;
		Authority authority;

		result = new Vet();
		this.actorService.setActorProperties(result);

		authority = new Authority();
		authority.setAuthority("VET");

		result.getUserAccount().addAuthority(authority);

		result.setAddress("");

		return result;
	}

	public Vet save(final Vet vet) {
		Vet result;

		Assert.notNull(vet, "vet.error.null");

		vet.setUserAccount(this.accountService.save(vet.getUserAccount()));
		result = this.vetRepository.save(vet);
		Assert.notNull(result, "vet.error.commit");
		return result;

	}

	public void delete() {
		Vet vet;
		vet = this.findVetByPrincipal();
		//TODO: borrar relaciones.
		this.petService.deleteFromVet(vet);
		this.vetRepository.delete(vet);
		this.accountService.delete(vet.getUserAccount().getId());

	}

	public Vet findOne(final int actorId) {
		return this.vetRepository.findOne(actorId);
	}

	public Collection<Vet> findAll() {
		return this.vetRepository.findAll();
	}

	public Long count() {
		return this.vetRepository.count();
	}
	// other business methods --------------------------------------

	public Vet findVetByPrincipal() {
		Vet result;
		result = this.vetRepository.findByUserAccountId(LoginService.getPrincipal().getId());
		return result;
	}

	public Vet reconstructNewVet(final VetForm vetForm, final BindingResult binding) {
		Vet result;
		Md5PasswordEncoder encoder;

		result = this.create();
		encoder = new Md5PasswordEncoder();

		this.actorService.setReconstructNewActorProperties(result, vetForm);
		this.setReconstructProperties(result, vetForm);

		this.validator.validate(result, binding);
		result.getUserAccount().setPassword(encoder.encodePassword(vetForm.getUserAccount().getPassword(), null));
		return result;
	}

	public Vet reconstruct(final VetForm vetForm, final Vet vet, final BindingResult binding) {
		Md5PasswordEncoder encoder;
		Vet result;

		result = this.create();
		encoder = new Md5PasswordEncoder();

		this.actorService.setReconstructActorProperties(result, vet, vetForm);
		this.setReconstructProperties(result, vetForm);

		this.validator.validate(result, binding);

		result.getUserAccount().setPassword(encoder.encodePassword(vetForm.getUserAccount().getPassword(), null));

		return result;
	}

	private void setReconstructProperties(final Vet result, final VetForm vetForm) {

		result.setAddress(vetForm.getAddress());

	}

}
