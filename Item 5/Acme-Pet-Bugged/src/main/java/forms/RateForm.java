
package forms;

import org.hibernate.validator.constraints.Range;

public class RateForm {

	private int	requestId;
	private int	rate;


	//Constructor
	public RateForm() {
		super();
	}

	public RateForm(final int requestId) {
		this.setRate(0);
		this.setRequestId(requestId);
	}

	//attributes------------

	public int getRequestId() {
		return this.requestId;
	}

	public void setRequestId(final int requestId) {
		this.requestId = requestId;
	}

	@Range(min = -5, max = 5)
	public int getRate() {
		return this.rate;
	}

	public void setRate(final int rate) {
		this.rate = rate;
	}

}
