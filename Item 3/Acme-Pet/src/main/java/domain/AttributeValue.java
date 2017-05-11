
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class AttributeValue extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	value;


	@NotBlank
	@SafeHtml
	public String getValue() {
		return this.value;
	}

	public void setValue(final String value) {
		this.value = value;
	}


	// Relationships ----------------------------------------------------------

	private Pet			pet;
	private Attribute	attribute;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Pet getPet() {
		return this.pet;
	}

	public void setPet(final Pet pet) {
		this.pet = pet;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Attribute getAttribute() {
		return this.attribute;
	}

	public void setAttribute(final Attribute attribute) {
		this.attribute = attribute;
	}

}
