
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class SearchEngine extends DomainEntity {

	// Attributes -------------------------------------------------------------

	private Date	startDate;
	private Date	endDate;
	private String	address;
	private String	type;
	private Date	searchMoment;


	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getSearchMoment() {
		return this.searchMoment;
	}

	public void setSearchMoment(final Date searchMoment) {
		this.searchMoment = searchMoment;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	@NotNull
	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}


	// Relationships ----------------------------------------------------------

	private Collection<Request>	requests;
	private Animaniac			animaniac;


	@NotNull
	@Valid
	@ManyToMany
	public Collection<Request> getRequests() {
		return this.requests;
	}

	public void setRequests(final Collection<Request> requests) {
		this.requests = requests;
	}

	@NotNull
	@Valid
	@OneToOne(optional = false)
	public Animaniac getAnimaniac() {
		return this.animaniac;
	}

	public void setAnimaniac(final Animaniac animaniac) {
		this.animaniac = animaniac;
	}

}
