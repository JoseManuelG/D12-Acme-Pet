
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TypeRepository;
import domain.Type;

@Service
@Transactional
public class TypeService {

	// Managed Repository --------------------------------------
	@Autowired
	private TypeRepository	typeRepository;


	public Type create() {
		final Type result = new Type();
		return result;
	}

	public Collection<Type> findAll() {
		Collection<Type> result = new ArrayList<Type>();
		result = this.typeRepository.findAll();
		return result;
	}

	public Type findOne(final int id) {
		Type result;
		result = this.typeRepository.findOne(id);
		return result;
	}

	public void delete(final Type type) {
		long cont;

		cont = this.typeRepository.countPetsWithType(type.getId());

		Assert.isTrue(type.getId() != 0);
		Assert.isTrue(cont == 0, "type.error.deletingWhenUsed");

		this.typeRepository.delete(type.getId());
	}

	public Type save(final Type type) {
		Type result;
		long cont;
		Type existing;

		if (type.getId() != 0) {
			cont = this.typeRepository.countPetsWithType(type.getId());

			Assert.isTrue(cont == 0, "type.error.editingWhenUsed");
		}

		existing = this.typeRepository.findByName(type.getTypeName());
		if (existing != null)
			Assert.isTrue(existing.getId() == type.getId(), "type.error.alreadyExists");

		result = this.typeRepository.save(type);
		return result;
	}
	//Simple CRUD methods-------------------------------------------------------------------

	// other business methods --------------------------------------

}
