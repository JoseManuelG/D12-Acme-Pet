
package forms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import domain.Attribute;
import domain.AttributeValue;
import domain.Pet;
import domain.Photo;
import domain.Type;

public class PetForm {

	private String					name;
	private Double					weigth;
	private String					genre;
	private List<AttributeValue>	attributeValues;
	private List<Attribute>			attributes;
	private LinkedList<Photo>		photos;
	private Type					Type;
	private int						id;


	//	private int						errorValue;
	//	private int						errorPhoto;

	//attributes------------

	@SafeHtml
	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Min(0)
	public Double getWeigth() {
		return this.weigth;
	}

	public void setWeigth(final Double weigth) {
		this.weigth = weigth;
	}

	@Pattern(regexp = "^male$|^female$")
	public String getGenre() {
		return this.genre;
	}

	public void setGenre(final String genre) {
		this.genre = genre;
	}

	public List<AttributeValue> getAttributeValues() {
		return this.attributeValues;
	}

	public void setAttributeValues(final List<AttributeValue> attributeValues) {
		this.attributeValues = attributeValues;
	}

	public List<Attribute> getAttributes() {
		return this.attributes;
	}

	public void setAttributes(final List<Attribute> attributes) {
		this.attributes = attributes;
	}

	public List<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(final LinkedList<Photo> photos) {
		this.photos = photos;
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public Type getType() {
		return this.Type;
	}

	public void setType(final Type type) {
		this.Type = type;
	}

	//	public int getErrorValue() {
	//		return this.errorValue;
	//	}
	//
	//	public void setErrorValue(final int errorValue) {
	//		this.errorValue = errorValue;
	//	}
	//
	//	public int getErrorPhoto() {
	//		return this.errorPhoto;
	//	}
	//
	//	public void setErrorPhoto(final int errorPhoto) {
	//		this.errorPhoto = errorPhoto;
	//	}

	//----Metodos de uso del formulario----

	public PetForm() {
		this.attributeValues = new ArrayList<AttributeValue>();
		this.photos = new LinkedList<Photo>();
		this.weigth = 0.;
		this.id = -1;
		//		this.errorPhoto = -1;
		//		this.errorValue = -1;
	}
	public PetForm(final Pet pet) {
		this.genre = pet.getGenre();
		this.name = pet.getName();
		this.Type = pet.getType();
		this.weigth = pet.getWeigth();
		this.attributeValues = new ArrayList<AttributeValue>();
		this.photos = new LinkedList<Photo>();
		this.setId(pet.getId());
		//		this.errorPhoto = -1;
		//		this.errorValue = -1;
	}

	public void addPhotoSpace() {
		this.photos.add(new Photo());
	}

	public void removePhotoSpace() {
		this.photos.removeLast();
	}

	public void fillAttributes(final Collection<AttributeValue> attributeValues) {
		final List<Attribute> attributes = new ArrayList<Attribute>();
		for (final AttributeValue a : attributeValues)
			attributes.add(a.getAttribute());
		this.setAttributes(attributes);
	}
}
