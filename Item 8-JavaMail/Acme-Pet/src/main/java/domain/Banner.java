
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Banner extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	picture;
	private String	link;


	@NotBlank
	@URL
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	@NotBlank
	@URL
	public String getLink() {
		return this.link;
	}

	public void setLink(final String link) {
		this.link = link;
	}


	// Relationships ----------------------------------------------------------

	private Partner	partner;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(final Partner partner) {
		this.partner = partner;
	}

}
