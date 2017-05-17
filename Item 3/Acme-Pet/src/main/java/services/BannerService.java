
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.BannerRepository;
import domain.Banner;
import domain.Partner;

@Service
@Transactional
public class BannerService {

	//Managed Repository--------------------------------------------------------------------
	@Autowired
	private BannerRepository	bannerRepository;


	//Supported Services--------------------------------------------------------------------

	//Simple CRUD methods-------------------------------------------------------------------

	//Other Business methods-------------------------------------------------------------------

	public void deleteFromPartner(final Partner partner) {
		Collection<Banner> banners;

		banners = this.bannerRepository.findByPartner(partner.getId());

		this.bannerRepository.delete(banners);
	}

	public Collection<Banner> findBannerByPartner(final int partnerId) {
		return this.bannerRepository.findByPartner(partnerId);
	}
}
