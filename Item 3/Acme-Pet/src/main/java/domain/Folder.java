
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {
		"name", "actor_id"
	})
})
public class Folder extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	name;
	private boolean	readOnly;


	public boolean getReadOnly() {
		return this.readOnly;
	}

	public void setReadOnly(final boolean readOnly) {
		this.readOnly = readOnly;
	}
	@SafeHtml
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}


	// Relationships ----------------------------------------------------------

	private Actor	actor;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Actor getActor() {
		return this.actor;
	}

	public void setActor(final Actor actor) {
		this.actor = actor;
	}

}
