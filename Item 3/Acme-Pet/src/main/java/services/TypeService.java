
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		//TO-DO
		this.typeRepository.delete(type.getId());
	}

	public Type save(final Type type) {
		//TO-DO
		Type result;
		result = this.typeRepository.save(type);
		return result;
	}
	//Simple CRUD methods-------------------------------------------------------------------

	// other business methods --------------------------------------

}
