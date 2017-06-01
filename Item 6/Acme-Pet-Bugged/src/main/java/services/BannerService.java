
package services;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.BannerRepository;
import domain.Banner;
import domain.Partner;

@Service
@Transactional
public class BannerService {

	//Managed Repository--------------------------------------------------------------------
	@Autowired
	private BannerRepository		bannerRepository;

	//Supported Services--------------------------------------------------------------------

	@Autowired
	private PartnerService			partnerService;

	@Autowired
	private ConfigurationService	configurationService;

	@Autowired
	private Validator				validator;


	//Simple CRUD methods-------------------------------------------------------------------

	public Banner create() {
		final Banner banner = new Banner();
		return banner;
	}

	public Banner findOne(final int bannerId) {
		Banner result;
		Assert.isTrue(bannerId > 0, "banner.error.id.invalid");

		result = this.bannerRepository.findOne(bannerId);
		return result;
	}

	public Banner save(final Banner banner) {
		Banner result;
		Assert.notNull(banner, "banner.error.null");
		Assert.isTrue(this.partnerService.findPartnerByPrincipal().getClass().equals(Partner.class), "banner.error.notpartner");
		Assert.isTrue(banner.getPartner().equals(this.partnerService.findPartnerByPrincipal()), "banner.error.notowner");
		result = this.bannerRepository.save(banner);
		return result;
	}
	public Collection<Banner> findAll() {
		return this.bannerRepository.findAll();
	}

	public void delete(final Banner banner) {
		Assert.notNull(banner, "banner.error.null");
		Assert.isTrue(this.bannerRepository.exists(banner.getId()), "banner.error.notexists");
		Assert.isTrue(this.partnerService.findPartnerByPrincipal().getClass().equals(Partner.class), "banner.error.notpartner");
		Assert.isTrue(banner.getPartner().equals(this.partnerService.findPartnerByPrincipal()), "banner.error.notowner");

		this.bannerRepository.delete(banner);

	}

	public void flush() {
		this.bannerRepository.flush();
	}

	//Other Business methods-------------------------------------------------------------------

	public void deleteFromPartner(final Partner partner) {
		Collection<Banner> banners;

		banners = this.bannerRepository.findByPartner(partner.getId());

		this.bannerRepository.delete(banners);
	}

	public Collection<Banner> findBannerByPartner(final int partnerId) {
		return this.bannerRepository.findByPartner(partnerId);
	}

	public Banner reconstruct(final Banner banner, final BindingResult binding) {
		final Banner result;
		Partner principal;

		result = this.create();
		principal = this.partnerService.findPartnerByPrincipal();

		if (banner.getId() != 0) {
			final Banner savedBanner = this.findOne(banner.getId());
			result.setId(savedBanner.getId());
			result.setVersion(savedBanner.getVersion());
			result.setPartner(savedBanner.getPartner());
		} else
			result.setPartner(principal);

		result.setPicture(banner.getPicture());
		result.setLink(banner.getLink());
		this.validator.validate(result, binding);

		return result;
	}

	public Banner randomBanner() {
		Banner result = this.create();
		final List<Banner> banners = this.bannerRepository.findAll();

		if (!banners.isEmpty()) {
			final Random randomIndex = new Random();
			final Integer index = randomIndex.nextInt(banners.size());
			result = banners.get(index);
		}
		this.updatePartnerFee(result);
		return result;
	}

	public void updatePartnerFee(final Banner banner) {
		Partner partner;

		partner = banner.getPartner();
		partner.setTotalFee(partner.getTotalFee() + this.configurationService.findConfiguration().getPartnerFee());

	}
}
