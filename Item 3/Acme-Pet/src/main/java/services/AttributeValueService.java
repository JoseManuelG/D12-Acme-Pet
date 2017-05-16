
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AttributeValueRepository;
import domain.Attribute;
import domain.AttributeValue;
import domain.Pet;

@Service
@Transactional
public class AttributeValueService {

	//Managed Repository--------------------------------------------------------------------
	@Autowired
	private AttributeValueRepository	attributeValueRepository;


	//Supported Services-------------------------------------------------------------

	//Simple CRUD methods------------------------------------------------------------
	public AttributeValue create(final Pet pet, final Attribute attribute) {
		final AttributeValue result = new AttributeValue();
		return result;
	}

	public AttributeValue save(final AttributeValue attributeValue) {
		AttributeValue result;
		result = this.attributeValueRepository.save(attributeValue);
		return result;
	}

	public void delete(final AttributeValue attributeValue) {
		Assert.notNull(attributeValue, "El objeto no puede ser nulo");
		Assert.isTrue(attributeValue.getId() != 0, "El objeto no puede tener id 0");
		this.attributeValueRepository.delete(attributeValue);

	}

	//Other Bussnisnes methods------------------------------------------------------------
	//Devuelve la lista de AttributeValues de un mensaje dado
	public List<AttributeValue> findAttributeValuesOfPet(final Pet pet) {
		final List<AttributeValue> result = this.attributeValueRepository.findAttributeValuesOfPet(pet.getId());
		return result;
	}
	//Guarda los attributeValues setteandolos al mensaje dado, AVISO: el mensaje debe estar almacenado ya en base de datos si no la ID seria 0.
	public void addAttributeValues(final Collection<AttributeValue> attributeValues, final Pet pet, final Attribute attribute) {
		final AttributeValue attributeValue = this.create(pet, attribute);
		for (final AttributeValue a : attributeValues) {
			attributeValue.setValue(a.getValue());
			this.save(attributeValue);
		}

	}
	//Borra los attributeValues de un mensaje, se debe llamar antes de borrar el mensaje para evitar problemas de persistencia
	public void deleteAttributeValuesOfPet(final Pet pet) {
		final List<AttributeValue> aux = this.findAttributeValuesOfPet(pet);
		for (final AttributeValue attributeValue : aux)
			this.delete(attributeValue);

	}

	public void deletePet(final Pet pet) {
		final Collection<AttributeValue> attributeValues = new ArrayList<AttributeValue>();
		attributeValues.addAll(this.attributeValueRepository.findAttributeValuesOfPetDeleting(pet.getId()));
		this.attributeValueRepository.delete(attributeValues);

	}

	public void addAttributeValues(final Collection<AttributeValue> attributeValues, final Pet pet) {
		for (final AttributeValue a : attributeValues) {
			final AttributeValue attributeValue = this.create(pet, a.getAttribute());
			attributeValue.setValue(a.getValue());
			this.save(attributeValue);
		}
	}

	public void deleteAttributeValuesFromAttribute(final int attributeId) {
		Collection<AttributeValue> values;

		values = this.attributeValueRepository.findAllFromAttribute(attributeId);
		this.attributeValueRepository.delete(values);
	}

}
