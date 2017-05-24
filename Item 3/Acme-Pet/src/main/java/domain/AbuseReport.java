
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "reportDate")
})
public class AbuseReport extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private String	description;
	private Date	reportDate;


	@NotBlank
	@SafeHtml
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@NotNull
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(final Date reportDate) {
		this.reportDate = reportDate;
	}


	// Relationships ----------------------------------------------------------

	private Animaniac	reported;
	private Animaniac	reporter;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Animaniac getReported() {
		return this.reported;
	}

	public void setReported(final Animaniac reported) {
		this.reported = reported;
	}

	@Valid
	@ManyToOne(optional = true)
	public Animaniac getReporter() {
		return this.reporter;
	}

	public void setReporter(final Animaniac reporter) {
		this.reporter = reporter;
	}

}
