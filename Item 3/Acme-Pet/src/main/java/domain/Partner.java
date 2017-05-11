
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;

@Entity
@Access(AccessType.PROPERTY)
public class Partner extends Actor {

	// Attributes -------------------------------------------------------------

	private int	numDisplay;


	@Min(0)
	public int getNumDisplay() {
		return this.numDisplay;
	}

	public void setNumDisplay(final int numDisplay) {
		this.numDisplay = numDisplay;
	}

	// Relationships ----------------------------------------------------------

}
