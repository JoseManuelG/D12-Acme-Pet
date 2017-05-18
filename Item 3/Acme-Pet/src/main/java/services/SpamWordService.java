
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SpamWordRepository;
import domain.Actor;
import domain.Administrator;
import domain.SpamWord;

@Service
@Transactional
public class SpamWordService {

	// Managed Repository --------------------------------------
	@Autowired
	private SpamWordRepository	spamWordRepository;

	// Other Services --------------------------------------
	@Autowired
	private ActorService		actorService;


	public SpamWord create() {
		final SpamWord result = new SpamWord();
		return result;
	}

	public Collection<SpamWord> findAll() {
		Collection<SpamWord> result = new ArrayList<SpamWord>();
		result = this.spamWordRepository.findAll();
		return result;
	}

	public SpamWord findOne(final int spamWordId) {
		SpamWord result;
		result = this.spamWordRepository.findOne(spamWordId);
		return result;
	}

	public void delete(final int spamWordId) {

		this.spamWordRepository.delete(spamWordId);
	}

	public SpamWord save(final SpamWord spamWord) {
		SpamWord result;
		SpamWord existing;
		Actor actor;
		actor = this.actorService.findActorByPrincipal();
		Assert.notNull(spamWord, "spamWord.error.nullSpamWord");
		Assert.isTrue(Administrator.class.equals(actor.getClass()), "spamWord.error.notAdmin");
		Assert.notNull(spamWord.getWord(), "spamWord.error.nullWord");
		existing = this.spamWordRepository.findByWord(spamWord.getWord());
		if (existing != null)
			Assert.isTrue(existing.getWord().equals(spamWord.getWord()), "spamWord.error.alreadyExists");

		result = this.spamWordRepository.save(spamWord);
		return result;
	}
	//Simple CRUD methods-------------------------------------------------------------------

	// other business methods --------------------------------------

}
