
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Type extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	typeName;


	@NotBlank
	@SafeHtml
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(final String typeName) {
		this.typeName = typeName;
	}

	// Relationships ----------------------------------------------------------

}
