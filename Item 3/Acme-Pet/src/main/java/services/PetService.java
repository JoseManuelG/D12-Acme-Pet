
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.PetRepository;
import domain.Administrator;
import domain.Animaniac;
import domain.AttributeValue;
import domain.Pet;
import domain.Photo;
import domain.Type;
import domain.Vet;
import forms.PetForm;

@Service
@Transactional
public class PetService {

	// Managed Repository --------------------------------------
	@Autowired
	private PetRepository			petRepository;

	// Supporting Services --------------------------------------
	@Autowired
	private AnimaniacService		animaniacService;

	@Autowired
	private CommentService			commentService;

	@Autowired
	private TypeService				typeService;

	@Autowired
	private AttributeValueService	attributeValueService;

	@Autowired
	private PhotoService			photoService;

	@Autowired
	private RequestService			requestService;

	@Autowired
	private VetService				vetService;

	@Autowired
	private Validator				validator;
	@Autowired
	private ActorService			actorService;


	//Simple CRUD methods-------------------------------------------------------------------

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
		Assert.isTrue(pet.getAnimaniac() == this.actorService.findActorByPrincipal() || Administrator.class.equals(this.actorService.findActorByPrincipal().getClass()));
		this.requestService.deleteFromPet(pet);
		this.attributeValueService.deleteFromPet(pet);
		this.commentService.deleteAllCommentsOfPet(pet);
		this.photoService.deleteFromPet(pet);
		this.petRepository.delete(pet.getId());
	}
	public void AdminDelete(final Pet pet) {
		Assert.notNull(pet);
		Assert.isTrue(pet.getId() != 0);
		Assert.notNull(pet.getAnimaniac());
		Assert.isTrue(Administrator.class.equals(this.actorService.findActorByPrincipal().getClass()));
		this.requestService.deleteFromPet(pet);
		this.attributeValueService.deleteFromPet(pet);
		this.commentService.deleteAllCommentsOfPet(pet);
		this.photoService.deleteFromPet(pet);
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

	public Pet saveCertificateByVet(final int petId) {
		Vet vet;
		Pet pet;
		pet = this.findOne(petId);
		Assert.notNull(pet);
		vet = this.vetService.findVetByPrincipal();
		Assert.isTrue(vet.getUserAccount().getAuthorities().iterator().next().getAuthority().equals("VET"));
		Assert.isNull(pet.getVet());
		pet.setCertificateBy(vet.getName() + "," + vet.getSurname());
		pet.setVet(vet);
		Pet result;
		result = this.petRepository.save(pet);
		return result;
	}

	public Pet save(final Pet pet, final Collection<AttributeValue> attributeValues, final Collection<Photo> photos) {
		Assert.notNull(pet);
		Assert.notNull(pet.getAnimaniac());
		Assert.isTrue(pet.getAnimaniac() == this.animaniacService.findAnimaniacByPrincipal());
		final List<AttributeValue> attributeValues2 = new ArrayList<AttributeValue>(attributeValues);
		final List<Photo> photos2 = new ArrayList<Photo>(photos);
		Pet result;
		result = this.petRepository.save(pet);
		this.attributeValueService.addAttributeValues(attributeValues2, pet.getId(), result);
		this.photoService.addPhotos(photos2, pet.getId(), result);
		return result;
	}

	public void flush() {
		this.petRepository.flush();
	}

	// other business methods --------------------------------------

	public Pet reconstruct(final PetForm petForm, final BindingResult binding) {
		final Pet result = this.create(petForm.getType().getId());
		result.setAnimaniac(this.animaniacService.findAnimaniacByPrincipal());
		result.setName(petForm.getName());
		result.setGenre(petForm.getGenre());
		result.setWeigth(petForm.getWeigth());
		result.setType(petForm.getType());

		this.validator.validate(result, binding);

		if (!binding.hasErrors()) {
			for (final Photo a : petForm.getPhotos()) {
				a.setPet(result);
				this.validator.validate(a, binding);
			}
			for (int i = 0; i < petForm.getAttributeValues().size(); i++) {
				petForm.getAttributeValues().get(i).setPet(result);
				petForm.getAttributeValues().get(i).setAttribute(petForm.getAttributes().get(i));

			}
		}
		return result;

	}
	public Pet reconstruct2(final PetForm petForm, final int petId, final BindingResult binding) {
		final Pet pet = this.petRepository.findOne(petId);
		final Pet result = this.create(petForm.getType().getId());
		result.setAnimaniac(this.animaniacService.findAnimaniacByPrincipal());
		result.setName(petForm.getName());
		result.setGenre(petForm.getGenre());
		result.setWeigth(petForm.getWeigth());
		result.setType(petForm.getType());
		result.setCertificateBy(pet.getCertificateBy());
		result.setId(petId);
		result.setVersion(pet.getVersion());

		this.validator.validate(result, binding);

		if (!binding.hasErrors()) {
			for (final Photo a : petForm.getPhotos()) {
				a.setPet(result);
				this.validator.validate(a, binding);
			}
			for (int i = 0; i < petForm.getAttributeValues().size(); i++) {
				petForm.getAttributeValues().get(i).setPet(result);
				petForm.getAttributeValues().get(i).setAttribute(petForm.getAttributes().get(i));

			}
		}

		return result;

	}

	// other business methods --------------------------------------

	public void deleteFromAnimaniac(final Animaniac animaniac) {
		Collection<Pet> pets;

		pets = this.petRepository.findPetsByAnimaniac(animaniac.getId());

		for (final Pet pet : pets) {
			this.requestService.deleteFromPetFromAnimaniac(pet);
			this.delete(pet);
		}
	}
	public void deleteFromAnimaniacByAdmin(final Animaniac animaniac) {
		Collection<Pet> pets;

		pets = this.petRepository.findPetsByAnimaniac(animaniac.getId());

		for (final Pet pet : pets) {
			this.requestService.deleteFromPetFromAnimaniacByAdmin(pet);
			this.delete(pet);
		}
	}
	public void deleteFromVet(final Vet vet) {
		Collection<Pet> pets;

		pets = this.petRepository.findPetsByVet(vet.getId());

		for (final Pet pet : pets)
			pet.setVet(null);

		this.petRepository.save(pets);
	}

	public Collection<Pet> findPetsByAnimaniac(final int animaniacId) {
		Collection<Pet> result = new ArrayList<Pet>();
		result = this.petRepository.findPetsByAnimaniac(animaniacId);
		return result;
	}

	public Collection<Pet> findAvalaiblePetsFromPrincipal() {
		/*
		 * Busca aquellas mascotas del animaniac conectado que, de estar ya en
		 * alguna request, esta tiene fecha fin anterior a hoy. Si no hay ninguna,
		 * da una IllegalArgumentException.
		 */
		Animaniac principal;
		Collection<Pet> pets;

		principal = this.animaniacService.findAnimaniacByPrincipal();
		pets = this.petRepository.findAvalaiblePetsFromAnimaniac(principal.getId(), new Date());

		Assert.notEmpty(pets, "request.error.available.pets.empty");

		return pets;
	}

}
