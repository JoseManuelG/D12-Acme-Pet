
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
		result.setPet(pet);
		result.setAttribute(attribute);
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

	public void deleteFromPet(final Pet pet) {
		Collection<AttributeValue> attributeValues;
		attributeValues = this.attributeValueRepository.findAttributeValuesOfPetDeleting(pet.getId());
		this.attributeValueRepository.delete(attributeValues);

	}

	public void addAttributeValues(final List<AttributeValue> attributeValues, final int petId, final Pet pet) {
		if (petId == 0)
			for (final AttributeValue a : attributeValues) {
				final AttributeValue attributeValue = this.create(pet, a.getAttribute());
				attributeValue.setValue(a.getValue());
				if (!a.getValue().isEmpty())
					this.save(attributeValue);
			}
		else {
			final List<AttributeValue> attributeValues2 = this.findAttributeValuesOfPet(pet);
			final List<AttributeValue> attributeValues3 = new ArrayList<AttributeValue>(attributeValues);
			for (int i = 0; i < attributeValues.size(); i++)
				for (int j = 0; j < attributeValues2.size(); j++)
					if (attributeValues.get(i).getAttribute() == attributeValues2.get(j).getAttribute()) {
						final AttributeValue attributeValue = this.create(pet, attributeValues2.get(j).getAttribute());
						attributeValue.setValue(attributeValues.get(i).getValue());
						attributeValue.setId(attributeValues2.get(j).getId());
						attributeValue.setVersion(attributeValues2.get(j).getVersion());

						if (!attributeValue.getValue().isEmpty()) {
							attributeValues3.remove(attributeValues.get(i));
							this.save(attributeValue);
						} else {
							attributeValues3.remove(attributeValues.get(i));
							this.delete(attributeValues2.get(j));
						}
					}
			for (final AttributeValue a : attributeValues3) {
				final AttributeValue attributeValue = this.create(pet, a.getAttribute());
				attributeValue.setValue(a.getValue());
				if (!a.getValue().isEmpty())
					this.save(attributeValue);
			}
		}
	}
	public void deleteAttributeValuesFromAttribute(final int attributeId) {
		Collection<AttributeValue> values;

		values = this.attributeValueRepository.findAllFromAttribute(attributeId);
		this.attributeValueRepository.delete(values);
	}

}
