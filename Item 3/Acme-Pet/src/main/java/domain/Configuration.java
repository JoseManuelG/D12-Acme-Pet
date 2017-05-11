
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private int	cacheTime;


	@Min(0)
	public int getCacheTime() {
		return this.cacheTime;
	}

	public void setCacheTime(final int cacheTime) {
		this.cacheTime = cacheTime;
	}

	// Relationships ----------------------------------------------------------

}
