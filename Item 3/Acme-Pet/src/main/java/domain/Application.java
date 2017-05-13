
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {
		"request_id", "animaniac_id"
	})
})
public class Application extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	description;
	private String	state;
	private boolean	rated;


	public boolean getRated() {
		return this.rated;
	}

	public void setRated(final boolean rated) {
		this.rated = rated;
	}

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

	private Request		request;
	private Animaniac	animaniac;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Request getRequest() {
		return this.request;
	}

	public void setRequest(final Request request) {
		this.request = request;
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
