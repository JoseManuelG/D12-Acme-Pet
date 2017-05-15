
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PetRepository;
import domain.Animaniac;
import domain.Pet;
import domain.Type;

@Service
@Transactional
public class PetService {

	// Managed Repository --------------------------------------
	@Autowired
	private PetRepository		petRepository;

	// Supporting Services --------------------------------------
	@Autowired
	private AnimaniacService	animaniacService;

	@Autowired
	private TypeService			typeService;


	public Pet create(final int typeId) {
		final Pet result = new Pet();
		final Animaniac animaniac = this.animaniacService.findAnimaniacByPrincipal();
		final Type type = this.typeService.findOne(typeId);
		result.setAnimaniac(animaniac);
		result.setType(type);
		return result;
	}

	public Collection<Pet> findAll() {
		Collection<Pet> result = new ArrayList<Pet>();
		result = this.petRepository.findAll();
		return result;
	}

	public Pet findOne(final int id) {
		Pet result;
		result = this.petRepository.findOne(id);
		return result;
	}

	public void delete(final Pet pet) {
		Assert.notNull(pet);
		Assert.isTrue(pet.getId() != 0);
		Assert.notNull(pet.getAnimaniac());
		Assert.isTrue(pet.getAnimaniac() == this.animaniacService.findAnimaniacByPrincipal());
		this.petRepository.delete(pet.getId());
	}

	public Pet save(final Pet pet) {
		Assert.notNull(pet);
		Assert.notNull(pet.getAnimaniac());
		Assert.isTrue(pet.getAnimaniac() == this.animaniacService.findAnimaniacByPrincipal());
		Pet result;
		result = this.petRepository.save(pet);
		return result;
	}
	//Simple CRUD methods-------------------------------------------------------------------

	// other business methods --------------------------------------

}
