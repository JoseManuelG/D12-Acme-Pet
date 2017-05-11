
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Curriculum extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	name;
	private String	email;
	private String	picture;
	private String	educationSection;
	private String	experienceSection;
	private String	hobbiesSection;


	@NotBlank
	@SafeHtml
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	@Email
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@NotBlank
	@URL
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	@NotBlank
	@SafeHtml
	public String getEducationSection() {
		return this.educationSection;
	}

	public void setEducationSection(final String educationSection) {
		this.educationSection = educationSection;
	}

	@NotBlank
	@SafeHtml
	public String getExperienceSection() {
		return this.experienceSection;
	}

	public void setExperienceSection(final String experienceSection) {
		this.experienceSection = experienceSection;
	}

	@NotBlank
	@SafeHtml
	public String getHobbiesSection() {
		return this.hobbiesSection;
	}

	public void setHobbiesSection(final String hobbiesSection) {
		this.hobbiesSection = hobbiesSection;
	}


	// Relationships ----------------------------------------------------------

	private CareCenter	careCenter;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public CareCenter getCareCenter() {
		return this.careCenter;
	}

	public void setCareCenter(final CareCenter careCenter) {
		this.careCenter = careCenter;
	}

}
