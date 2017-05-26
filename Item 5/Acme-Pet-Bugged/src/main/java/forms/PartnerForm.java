
package forms;

import domain.Partner;

public class PartnerForm extends ActorForm {

	private String	link;
	private String	address;
	private String	description;


	//Constructor
	public PartnerForm() {
		super();
	}

	public PartnerForm(final Partner partner) {
		super(partner);

		this.setLink(partner.getLink());
		this.setDescription(partner.getDescription());
		this.setAddress(partner.getAddress());

	}

	//attributes------------

	public String getLink() {
		return this.link;
	}

	public void setLink(final String link) {
		this.link = link;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

}
