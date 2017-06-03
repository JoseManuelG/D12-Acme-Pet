
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private long	cacheTime;
	private double	partnerFee;


	@Min(0)
	public double getPartnerFee() {
		return this.partnerFee;
	}

	public void setPartnerFee(final double partnerFee) {
		this.partnerFee = partnerFee;
	}

	@Min(0)
	public long getCacheTime() {
		return this.cacheTime;
	}

	public void setCacheTime(final long cacheTime) {
		this.cacheTime = cacheTime;
	}

	// Relationships ----------------------------------------------------------

}
