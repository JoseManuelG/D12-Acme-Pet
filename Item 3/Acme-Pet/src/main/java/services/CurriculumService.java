
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

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
	@Autowired
	private AnimaniacService		animaniacService;

	@Autowired
	private Validator				validator;


	//Simple CRUD methods-------------------------------------------------------------------

	public Curriculum create() {
		Curriculum res;

		res = new Curriculum();

		return res;
	}

	public void save(final Curriculum res) {
		this.curriculumRepository.save(res);
	}

	public void deletePrincipal() {
		Curriculum curriculum;
		Animaniac principal;

		principal = this.animaniacService.findAnimaniacByPrincipal();
		curriculum = this.curriculumRepository.findByAnimaniac(principal.getId());

		this.curriculumRepository.delete(curriculum);
	}

	//Other Business methods-------------------------------------------------------------------

	public void deleteFromAnimaniac(final Animaniac animaniac) {
		Curriculum curriculum;

		curriculum = this.curriculumRepository.findByAnimaniac(animaniac.getId());

		this.curriculumRepository.delete(curriculum);
	}

	public Curriculum findCurriculumByAnimaniac(final int animaniacId) {
		return this.curriculumRepository.findByAnimaniac(animaniacId);
	}

	public Curriculum findCurriculumByPrincipal() {
		Animaniac principal;

		principal = this.animaniacService.findAnimaniacByPrincipal();
		return this.curriculumRepository.findByAnimaniac(principal.getId());
	}
	public Curriculum reconstruct(final Curriculum curriculum, final BindingResult binding) {
		Curriculum result, old;
		Animaniac principal;

		result = this.create();
		principal = this.animaniacService.findAnimaniacByPrincipal();

		old = this.findCurriculumByAnimaniac(principal.getId());

		if (old != null) {
			result.setId(old.getId());
			result.setVersion(old.getVersion());
		}
		result.setAnimaniac(principal);
		result.setEducationSection(curriculum.getEducationSection());
		result.setExperienceSection(curriculum.getExperienceSection());
		result.setHobbiesSection(curriculum.getHobbiesSection());

		this.validator.validate(result, binding);

		return result;
	}

}
