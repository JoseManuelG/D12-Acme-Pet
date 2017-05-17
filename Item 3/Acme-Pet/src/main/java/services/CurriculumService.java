
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.CurriculumRepository;
import domain.Animaniac;
import domain.Curriculum;

@Service
@Transactional
public class CurriculumService {

	//Managed Repository--------------------------------------------------------------------
	@Autowired
	private CurriculumRepository	curriculumRepository;


	//Supported Services--------------------------------------------------------------------

	//Simple CRUD methods-------------------------------------------------------------------

	//Other Business methods-------------------------------------------------------------------

	public void deleteFromAnimaniac(final Animaniac animaniac) {
		Curriculum curriculum;

		curriculum = this.curriculumRepository.findByAnimaniac(animaniac.getId());

		this.curriculumRepository.delete(curriculum);
	}

	public Curriculum findCurriculumByAnimaniac(final int animaniacId) {
		return this.curriculumRepository.findByAnimaniac(animaniacId);
	}
}
