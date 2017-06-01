
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Curriculum extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	educationSection;
	private String	experienceSection;
	private String	hobbiesSection;


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

	private Animaniac	animaniac;


	@Valid
	@OneToOne(optional = true)
	public Animaniac getAnimaniac() {
		return this.animaniac;
	}

	public void setAnimaniac(final Animaniac animaniac) {
		this.animaniac = animaniac;
	}

}
