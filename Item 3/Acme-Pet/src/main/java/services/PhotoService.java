
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PhotoRepository;
import domain.Pet;
import domain.Photo;

@Service
@Transactional
public class PhotoService {

	//Managed Repository--------------------------------------------------------------------
	@Autowired
	private PhotoRepository	photoRepository;


	//Supported Services--------------------------------------------------------------------
	//Simple CRUD methods------------------------------------------------------------
	public Photo create(final Pet pet) {
		final Photo result = new Photo();
		result.setPet(pet);
		return result;
	}

	public Photo save(final Photo photo) {
		Photo result;
		result = this.photoRepository.save(photo);
		return result;
	}

	public void delete(final Photo photo) {
		Assert.notNull(photo, "El objeto no puede ser nulo");
		Assert.isTrue(photo.getId() != 0, "El objeto no puede tener id 0");
		this.photoRepository.delete(photo);

	}

	//Other Bussnisnes methods------------------------------------------------------------
	//Devuelve la lista de Photos de un mensaje dado
	public List<Photo> findPhotosOfPet(final Pet pet) {
		final List<Photo> result = this.photoRepository.findPhotosOfPet(pet.getId());
		return result;
	}
	public void addPhotos(final List<Photo> photos, final int petId, final Pet pet) {
		if (petId == 0) {
			final Photo photo = this.create(pet);
			for (final Photo a : photos) {
				photo.setLink(a.getLink());
				photo.setPet(pet);
				this.save(photo);
			}
		} else {
			final List<Photo> photos2 = this.photoRepository.findPhotosOfPet(petId);
			for (int i = 0; i < photos.size(); i++) {
				photos.get(i).setId(photos2.get(i).getId());
				photos.get(i).setVersion(photos2.get(i).getVersion());
				photos.get(i).setPet(pet);
				this.save(photos.get(i));
			}
		}

	}

	//Devuelve una colleción con copias de los photos de un mensaje, se podria coger los photos por query en vez de pedirlos como entrada.
	public Collection<Photo> copyPhotos(final Pet pet) {
		final Photo photo = this.create(pet);
		final LinkedList<Photo> result = new LinkedList<Photo>();
		for (final Photo a : this.photoRepository.findPhotosOfPet(pet.getId())) {
			photo.setLink(a.getLink());
			result.add(photo);
		}
		return result;

	}
	//Borra los photos de un mensaje, se debe llamar antes de borrar el mensaje para evitar problemas de persistencia
	public void deletePhotosOfPet(final Pet pet) {
		final List<Photo> aux = this.findPhotosOfPet(pet);
		for (final Photo photo : aux)
			this.delete(photo);

	}

	public void deleteActor(final Pet pet) {
		final Collection<Photo> photos = new ArrayList<Photo>();
		photos.addAll(this.photoRepository.findPhotosOfPetDeleting(pet.getId()));
		this.photoRepository.delete(photos);

	}

}
