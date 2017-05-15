
package forms;

import domain.Animaniac;

public class AnimaniacForm extends ActorForm {

	private String	picture;
	private String	genre;
	private String	address;


	//Constructor
	public AnimaniacForm() {
		super();
	}

	public AnimaniacForm(final Animaniac animaniac) {
		super(animaniac);

		this.setPicture(animaniac.getPicture());
		this.setGenre(animaniac.getGenre());

	}

	//attributes------------

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(final String genre) {
		this.genre = genre;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

}
