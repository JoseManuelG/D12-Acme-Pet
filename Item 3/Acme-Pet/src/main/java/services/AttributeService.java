
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AttributeRepository;
import domain.Attribute;
import domain.AttributeValue;
import domain.Pet;

@Service
@Transactional
public class AttributeService {

	// Managed Repository --------------------------------------
	@Autowired
	private AttributeRepository		attributeRepository;

	// Other Services --------------------------------------
	@Autowired
	private AttributeValueService	attributeValueService;

	@Autowired
	private AdministratorService	administratorService;


	public Attribute create() {
		final Attribute result = new Attribute();
		return result;
	}

	public Collection<Attribute> findAll() {
		Collection<Attribute> result = new ArrayList<Attribute>();
		result = this.attributeRepository.findAll();
		return result;
	}

	public Attribute findOne(final int id) {
		Attribute result;
		result = this.attributeRepository.findOne(id);
		return result;
	}

	public void delete(final Attribute attribute) {
		Assert.notNull(this.administratorService.findAdministratorByPrincipal());

		this.attributeValueService.deleteAttributeValuesFromAttribute(attribute.getId());
		this.attributeRepository.delete(attribute.getId());
	}

	public Attribute save(final Attribute attribute) {
		Attribute result;
		long cont;
		Attribute existing;

		Assert.notNull(this.administratorService.findAdministratorByPrincipal());

		if (attribute.getId() != 0) {
			cont = this.attributeRepository.countAttributeValuesWithAttribute(attribute.getId());

			Assert.isTrue(cont == 0, "attribute.error.editingWhenUsed");
		}

		existing = this.attributeRepository.findByNameAndType(attribute.getName(), attribute.getType().getId());
		if (existing != null)
			Assert.isTrue(existing.getId() == attribute.getId(), "attribute.error.alreadyExists");

		result = this.attributeRepository.save(attribute);
		return result;
	}

	public void flush() {
		this.attributeRepository.flush();
	}
	//Simple CRUD methods-------------------------------------------------------------------

	// other business methods --------------------------------------

	public Collection<AttributeValue> attributeValuesFromType(final int typeId, final Pet pet) {
		final Collection<AttributeValue> result = new ArrayList<AttributeValue>();
		final Collection<Attribute> attributes = this.attributeRepository.attributtesWithType(typeId);
		for (final Attribute a : attributes) {
			final AttributeValue attributeValue = this.attributeValueService.create(pet, a);
			result.add(attributeValue);
		}
		return result;
	}

}
