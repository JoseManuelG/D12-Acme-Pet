
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Application extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	description;
	private String	state;


	@NotBlank
	@SafeHtml
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@Pattern(regexp = "^Pending$|^Accepted$|^Denied$")
	public String getState() {
		return this.state;
	}

	public void setState(final String state) {
		this.state = state;
	}


	// Relationships ----------------------------------------------------------

	private Petition	petition;
	private Animaniac	animaniac;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Petition getPetition() {
		return this.petition;
	}

	public void setPetition(final Petition petition) {
		this.petition = petition;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Animaniac getAnimaniac() {
		return this.animaniac;
	}

	public void setAnimaniac(final Animaniac animaniac) {
		this.animaniac = animaniac;
	}

}
