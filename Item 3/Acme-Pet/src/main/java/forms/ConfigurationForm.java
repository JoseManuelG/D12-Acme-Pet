
package forms;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Range;

public class ConfigurationForm {

	private int		hours;
	private int		minutes;
	private int		seconds;
	private double	patnerFee;


	// Constructor-------------------------------------
	public ConfigurationForm() {
		super();
	}
	// Attributes--------------------------------------

	@Min(0)
	public int getHours() {
		return this.hours;
	}

	public void setHours(final int hours) {
		this.hours = hours;
	}

	@Range(min = 0, max = 59)
	public int getMinutes() {
		return this.minutes;
	}

	public void setMinutes(final int minutes) {
		this.minutes = minutes;
	}

	@Range(min = 0, max = 59)
	public int getSeconds() {
		return this.seconds;
	}

	public void setSeconds(final int seconds) {
		this.seconds = seconds;
	}
	@Min(0)
	public double getPatnerFee() {
		return this.patnerFee;
	}

	public void setPatnerFee(final double patnerFee) {
		this.patnerFee = patnerFee;
	}

}
