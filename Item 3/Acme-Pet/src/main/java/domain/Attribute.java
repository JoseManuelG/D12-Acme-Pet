
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {
		"attribute", "type_id"
	})
})
public class Attribute extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	attribute;


	@NotBlank
	@SafeHtml
	public String getAttribute() {
		return this.attribute;
	}

	public void setAttribute(final String attribute) {
		this.attribute = attribute;
	}


	// Relationships ----------------------------------------------------------

	private Type	type;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Type getType() {
		return this.type;
	}

	public void setType(final Type type) {
		this.type = type;
	}

}
