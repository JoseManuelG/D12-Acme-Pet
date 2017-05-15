
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AnimaniacRepository;
import security.Authority;
import security.LoginService;
import domain.Animaniac;
import forms.AnimaniacForm;

@Service
@Transactional
public class AnimaniacService {

	// Managed Repository --------------------------------------
	@Autowired
	private AnimaniacRepository	animaniacRepository;

	// Supporting Services --------------------------------------
	@Autowired
	private ActorService		actorService;

	@Autowired
	private Validator			validator;


	//Simple CRUD methods-------------------------------------------------------------------

	public Animaniac create() {
		Animaniac result;
		Authority authority;

		result = new Animaniac();
		this.actorService.setActorProperties(result);

		authority = new Authority();
		authority.setAuthority("ANIMANIAC");

		result.getUserAccount().addAuthority(authority);

		result.setAddress("");
		result.setGenre("");
		result.setRate(0);
		result.setPicture("");

		return result;
	}

	public Animaniac save(final Animaniac animaniac) {
		Animaniac result;

		Assert.notNull(animaniac, "animaniac.error.null");

		result = this.animaniacRepository.save(animaniac);
		Assert.notNull(result, "animaniac.error.commit");
		if (animaniac.getId() == 0) {
			//TODO: crear buscador this.searchTemplateService.createForAnimaniac(result);
		}
		return result;

	}

	public void delete() {
		Animaniac animaniac;
		animaniac = this.findAnimaniacByPrincipal();
		//TODO: borrar relaciones.
		//		this.creditCardService.deleteFromAnimaniac(animaniac);
		//		this.searchTemplateService.deleteFromAnimaniac(animaniac);
		//		this.likesService.deleteFromAnimaniac(animaniac);
		//		this.chirpService.deleteFromAnimaniac(animaniac);
		//		this.registerService.deleteFromAnimaniac(animaniac);
		this.animaniacRepository.delete(animaniac);
		//		this.userAccountService.delete(animaniac.getUserAccount().getId());

	}

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

	public Animaniac reconstructNewAnimaniac(final AnimaniacForm animaniacForm, final BindingResult binding) {
		Animaniac result;
		Md5PasswordEncoder encoder;

		result = this.create();
		encoder = new Md5PasswordEncoder();

		this.actorService.setReconstructNewActorProperties(result, animaniacForm);
		this.setReconstructProperties(result, animaniacForm);

		this.validator.validate(result, binding);
		result.getUserAccount().setPassword(encoder.encodePassword(animaniacForm.getUserAccount().getPassword(), null));
		return result;
	}

	public Animaniac reconstruct(final AnimaniacForm animaniacForm, final Animaniac animaniac, final BindingResult binding) {
		Md5PasswordEncoder encoder;
		Animaniac result;

		result = this.create();
		encoder = new Md5PasswordEncoder();

		this.actorService.setReconstructActorProperties(result, animaniac, animaniacForm);
		this.setReconstructProperties(result, animaniacForm);
		result.setRate(animaniac.getRate());

		this.validator.validate(result, binding);

		result.getUserAccount().setPassword(encoder.encodePassword(animaniacForm.getUserAccount().getPassword(), null));

		return result;
	}

	private void setReconstructProperties(final Animaniac result, final AnimaniacForm animaniacForm) {

		result.setPicture(animaniacForm.getPicture());
		result.setGenre(animaniacForm.getGenre());
		result.setAddress(animaniacForm.getAddress());

	}

}
