
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "certificateBy")
})
public class Pet extends Commentable {

	// Attributes -------------------------------------------------------------

	private String	name;
	private double	weigth;
	private String	genre;
	private String	certificateBy;


	@NotBlank
	@SafeHtml
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Min(0)
	public double getWeigth() {
		return this.weigth;
	}

	public void setWeigth(final double weigth) {
		this.weigth = weigth;
	}

	@Pattern(regexp = "^male$|^female$")
	public String getGenre() {
		return this.genre;
	}

	public void setGenre(final String genre) {
		this.genre = genre;
	}

	@SafeHtml
	public String getCertificateBy() {
		return this.certificateBy;
	}

	public void setCertificateBy(final String certificateBy) {
		this.certificateBy = certificateBy;
	}


	// Relationships ----------------------------------------------------------

	private Animaniac	animaniac;
	private Type		type;
	private Vet			vet;


	@Valid
	@ManyToOne(optional = true)
	public Vet getVet() {
		return this.vet;
	}

	public void setVet(final Vet vet) {
		this.vet = vet;
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
