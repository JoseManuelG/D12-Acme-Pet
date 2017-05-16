
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.CurriculumRepository;
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

	public Curriculum findCurriculumByAnimaniac(final int animaniacId) {
		return this.curriculumRepository.findByAnimaniac(animaniacId);
	}
}
