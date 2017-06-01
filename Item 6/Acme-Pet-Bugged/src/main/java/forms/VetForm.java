
package forms;

import domain.Vet;

public class VetForm extends ActorForm {

	private String	address;


	//Constructor
	public VetForm() {
		super();
	}

	public VetForm(final Vet vet) {
		super(vet);

		this.setAddress(vet.getAddress());

	}

	//attributes------------

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

}
