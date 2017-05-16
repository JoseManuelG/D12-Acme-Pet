
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.PartnerRepository;
import security.Authority;
import security.LoginService;
import domain.Partner;
import forms.PartnerForm;

@Service
@Transactional
public class PartnerService {

	// Managed Repository --------------------------------------
	@Autowired
	private PartnerRepository	partnerRepository;

	// Supporting Services --------------------------------------
	@Autowired
	private ActorService		actorService;

	@Autowired
	private UserAccountService	accountService;

	@Autowired
	private Validator			validator;


	//Simple CRUD methods-------------------------------------------------------------------

	public Partner create() {
		Partner result;
		Authority authority;

		result = new Partner();
		this.actorService.setActorProperties(result);

		authority = new Authority();
		authority.setAuthority("PARTNER");

		result.getUserAccount().addAuthority(authority);

		result.setAddress("");
		result.setLink("");
		result.setDescription("");
		result.setNumDisplay(0);
		result.setTotalFee(0);

		return result;
	}

	public Partner save(final Partner partner) {
		Partner result;

		Assert.notNull(partner, "partner.error.null");

		partner.setUserAccount(this.accountService.save(partner.getUserAccount()));
		result = this.partnerRepository.save(partner);
		Assert.notNull(result, "partner.error.commit");
		return result;

	}

	public void delete() {
		Partner partner;
		partner = this.findPartnerByPrincipal();
		//TODO: borrar relaciones.
		//		this.creditCardService.deleteFromPartner(partner);
		//		this.searchTemplateService.deleteFromPartner(partner);
		//		this.likesService.deleteFromPartner(partner);
		//		this.chirpService.deleteFromPartner(partner);
		//		this.registerService.deleteFromPartner(partner);
		this.partnerRepository.delete(partner);
		//		this.userAccountService.delete(partner.getUserAccount().getId());

	}

	public Partner findOne(final int actorId) {
		return this.partnerRepository.findOne(actorId);
	}

	public Collection<Partner> findAll() {
		return this.partnerRepository.findAll();
	}

	public Long count() {
		return this.partnerRepository.count();
	}
	// other business methods --------------------------------------

	public Partner findPartnerByPrincipal() {
		Partner result;
		result = this.partnerRepository.findByUserAccountId(LoginService.getPrincipal().getId());
		return result;
	}

	public Partner reconstructNewPartner(final PartnerForm partnerForm, final BindingResult binding) {
		Partner result;
		Md5PasswordEncoder encoder;

		result = this.create();
		encoder = new Md5PasswordEncoder();

		this.actorService.setReconstructNewActorProperties(result, partnerForm);
		this.setReconstructProperties(result, partnerForm);

		this.validator.validate(result, binding);
		result.getUserAccount().setPassword(encoder.encodePassword(partnerForm.getUserAccount().getPassword(), null));
		return result;
	}

	public Partner reconstruct(final PartnerForm partnerForm, final Partner partner, final BindingResult binding) {
		Md5PasswordEncoder encoder;
		Partner result;

		result = this.create();
		encoder = new Md5PasswordEncoder();

		this.actorService.setReconstructActorProperties(result, partner, partnerForm);
		this.setReconstructProperties(result, partnerForm);

		result.setNumDisplay(partner.getNumDisplay());
		result.setTotalFee(partner.getTotalFee());

		this.validator.validate(result, binding);

		result.getUserAccount().setPassword(encoder.encodePassword(partnerForm.getUserAccount().getPassword(), null));

		return result;
	}

	private void setReconstructProperties(final Partner result, final PartnerForm partnerForm) {

		result.setLink(partnerForm.getLink());
		result.setAddress(partnerForm.getAddress());
		result.setDescription(partnerForm.getDescription());

	}

}