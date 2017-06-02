
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AnimaniacRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Animaniac;
import domain.SearchEngine;
import forms.AnimaniacForm;

@Service
@Transactional
public class AnimaniacService {

	// Managed Repository --------------------------------------
	@Autowired
	private AnimaniacRepository	animaniacRepository;

	// Supporting Services --------------------------------------
	@Autowired
	private ActorService		actorService;

	@Autowired
	private UserAccountService	accountService;

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private CommentService		commentService;

	@Autowired
	private CurriculumService	curriculumService;

	@Autowired
	private FolderService		folderService;

	@Autowired
	private PetService			petService;

	@Autowired
	private AbuseReportService	reportService;

	@Autowired
	private SearchEngineService	searchEngineService;

	@Autowired
	private MessageService		messageService;

	@Autowired
	private Validator			validator;


	//Simple CRUD methods-------------------------------------------------------------------

	public Animaniac create() {
		Animaniac result;
		Authority authority;

		result = new Animaniac();
		this.actorService.setActorProperties(result);

		authority = new Authority();
		authority.setAuthority("ANIMANIAC");

		result.getUserAccount().addAuthority(authority);

		result.setAddress("");
		result.setGenre("");
		result.setRate(0);
		result.setPicture("");

		return result;
	}

	public Animaniac save(final Animaniac animaniac) {
		Animaniac result;
		SearchEngine searchEngine;

		Assert.notNull(animaniac, "animaniac.error.null");

		animaniac.setUserAccount(this.accountService.save(animaniac.getUserAccount()));
		result = this.animaniacRepository.save(animaniac);
		Assert.notNull(result, "animaniac.error.commit");
		if (animaniac.getId() == 0) {
			searchEngine = this.searchEngineService.create(result.getId());
			this.searchEngineService.saveParaCreate(searchEngine);
			this.folderService.createBasicsFolders(result);
		}

		return result;

	}
	public void delete() {
		Animaniac animaniac;
		animaniac = this.findAnimaniacByPrincipal();
		this.reportService.deleteFromAnimaniac(animaniac);
		this.curriculumService.deleteFromAnimaniac(animaniac);
		this.petService.deleteFromAnimaniac(animaniac);
		this.searchEngineService.deleteFromAnimaniac(animaniac);
		this.applicationService.deleteFromAnimaniac(animaniac);
		this.actorService.deleteFromActor(animaniac);
		this.commentService.deleteAllCommentsOfAnimaniac(animaniac);
		this.animaniacRepository.delete(animaniac);
		this.accountService.delete(animaniac.getUserAccount().getId());
	}
	public Animaniac findOne(final int actorId) {

		return this.animaniacRepository.findOne(actorId);
	}

	public Collection<Animaniac> findAll() {
		return this.animaniacRepository.findAll();
	}

	public Long count() {
		return this.animaniacRepository.count();
	}

	public void flush() {
		this.animaniacRepository.flush();
	}
	// other business methods --------------------------------------

	public void ban(final int animaniacId) {
		final List<Authority> auths = new ArrayList<Authority>(this.actorService.findActorByPrincipal().getUserAccount().getAuthorities());
		final Authority auth = auths.get(0);
		Assert.isTrue(auth.getAuthority().equals("ADMINISTRATOR"), "animaniac.error.notadmin");
		final UserAccount ua = this.animaniacRepository.findOne(animaniacId).getUserAccount();
		ua.setEnabled(false);
		this.accountService.save(ua);
		this.messageService.alertAnimaniacs(animaniacId);
		final Animaniac animaniac = this.findOne(animaniacId);
		this.sendEMail(animaniac.getEmail(), animaniac.getName(), "your account has been banned for a misuse of it.", "Account Info.");
	}

	public void unban(final int animaniacId) {
		final List<Authority> auths = new ArrayList<Authority>(this.actorService.findActorByPrincipal().getUserAccount().getAuthorities());
		final Authority auth = auths.get(0);
		Assert.isTrue(auth.getAuthority().equals("ADMINISTRATOR"), "animaniac.error.notadmin");
		final UserAccount ua = this.animaniacRepository.findOne(animaniacId).getUserAccount();
		ua.setEnabled(true);
		this.accountService.save(ua);
		final Animaniac animaniac = this.findOne(animaniacId);
		this.sendEMail(animaniac.getEmail(), animaniac.getName(), "your account has been enabled again.", "Account Info.");

	}

	public void sendEMail(final String to, final String name, final String text, final String subject) {
		final Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		final Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("acmepetdp@gmail.com", "A8p2YNua");
			}
		});

		final MimeMessage message = new MimeMessage(session);
		final MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(name + ", " + text);
			Transport.send(message);
		} catch (final Exception e) {
			System.out.print(e.getMessage());
		}
	}

	public Animaniac findAnimaniacByPrincipal() {
		Animaniac result;
		result = this.animaniacRepository.findByUserAccountId(LoginService.getPrincipal().getId());
		return result;
	}

	public Animaniac reconstructNewAnimaniac(final AnimaniacForm animaniacForm, final BindingResult binding) {
		Animaniac result;
		Md5PasswordEncoder encoder;

		result = this.create();
		encoder = new Md5PasswordEncoder();

		this.actorService.setReconstructNewActorProperties(result, animaniacForm);
		this.setReconstructProperties(result, animaniacForm);

		this.validator.validate(result, binding);
		result.getUserAccount().setPassword(encoder.encodePassword(animaniacForm.getUserAccount().getPassword(), null));
		return result;
	}

	public Animaniac reconstruct(final AnimaniacForm animaniacForm, final Animaniac animaniac, final BindingResult binding) {
		Md5PasswordEncoder encoder;
		Animaniac result;

		result = this.create();
		encoder = new Md5PasswordEncoder();

		this.actorService.setReconstructActorProperties(result, animaniac, animaniacForm);
		this.setReconstructProperties(result, animaniacForm);
		result.setRate(animaniac.getRate());

		this.validator.validate(result, binding);

		result.getUserAccount().setPassword(encoder.encodePassword(animaniacForm.getUserAccount().getPassword(), null));

		return result;
	}

	private void setReconstructProperties(final Animaniac result, final AnimaniacForm animaniacForm) {

		result.setPicture(animaniacForm.getPicture());
		result.setGenre(animaniacForm.getGenre());
		result.setAddress(animaniacForm.getAddress());

	}

	public void saveRate(final Animaniac animaniac) {
		this.animaniacRepository.save(animaniac);
	}

	public void deleteBanned(final int animaniacId) {
		Animaniac animaniac;
		Assert.isTrue(this.actorService.findActorByPrincipal().getClass().equals(Administrator.class), "Debes ser administrador para esta acción");
		Assert.isTrue(animaniacId != 0);
		animaniac = this.findOne(animaniacId);
		Assert.isTrue(animaniac.getBanned(), "El animaniaco debe estar banneado");

		this.deleteByAdmin(animaniacId);
	}
	private void deleteByAdmin(final int animaniacId) {
		Animaniac animaniac;
		animaniac = this.findOne(animaniacId);
		this.reportService.deleteFromAnimaniac(animaniac);
		this.curriculumService.deleteFromAnimaniac(animaniac);
		this.petService.deleteFromAnimaniacByAdmin(animaniac);
		this.searchEngineService.deleteFromAnimaniac(animaniac);
		this.applicationService.deleteFromAnimaniac(animaniac);
		this.actorService.deleteFromActor(animaniac);
		this.commentService.deleteAllCommentsOfAnimaniac(animaniac);
		this.animaniacRepository.delete(animaniac);
		this.accountService.delete(animaniac.getUserAccount().getId());
	}

}
