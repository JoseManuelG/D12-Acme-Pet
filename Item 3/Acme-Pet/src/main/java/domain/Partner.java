
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "totalFee")
})
public class Partner extends Actor {

	// Attributes -------------------------------------------------------------

	private int		numDisplay;
	private String	description;
	private String	link;
	private String	address;
	private double	totalFee;


	@Min(0)
	public double getTotalFee() {
		return this.totalFee;
	}

	public void setTotalFee(final double totalFee) {
		this.totalFee = totalFee;
	}

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
	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	@Min(0)
	public int getNumDisplay() {
		return this.numDisplay;
	}

	public void setNumDisplay(final int numDisplay) {
		this.numDisplay = numDisplay;
	}

	// Relationships ----------------------------------------------------------

}
