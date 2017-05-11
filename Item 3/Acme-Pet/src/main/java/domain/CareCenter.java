
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class CareCenter extends Partner {

	// Attributes -------------------------------------------------------------

	private String	description;
	private String	link;
	private String	addres;


	@NotBlank
	@SafeHtml
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@NotBlank
	@URL
	public String getLink() {
		return this.link;
	}

	public void setLink(final String link) {
		this.link = link;
	}

	@NotBlank
	@SafeHtml
	public String getAddres() {
		return this.addres;
	}

	public void setAddres(final String addres) {
		this.addres = addres;
	}

	// Relationships ----------------------------------------------------------

}
