
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.AttributeRepository;
import domain.Attribute;

@Service
@Transactional
public class AttributeService {

	// Managed Repository --------------------------------------
	@Autowired
	private AttributeRepository	attributeRepository;


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

		this.attributeRepository.delete(attribute.getId());
	}

	public Attribute save(final Attribute attribute) {
		Attribute result;
		result = this.attributeRepository.save(attribute);
		return result;
	}
	//Simple CRUD methods-------------------------------------------------------------------

	// other business methods --------------------------------------

}
