
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import repositories.AbuseReportRepository;
import domain.AbuseReport;
import domain.Animaniac;
import domain.Attachment;

@Service
@Transactional
public class AbuseReportService {

	//Managed Repository--------------------------------------------------------------------
	@Autowired
	private AbuseReportRepository	abuseReportRepository;


	//Supported Services--------------------------------------------------------------------
	//Simple CRUD methods------------------------------------------------------------
	public AbuseReport create() {
		final AbuseReport result = new AbuseReport();
		return result;
	}

	public AbuseReport save(final AbuseReport abuseReport) {
		AbuseReport result;
		result = this.abuseReportRepository.save(abuseReport);
		return result;
	}

	public void delete(final AbuseReport abuseReport) {
		this.abuseReportRepository.delete(abuseReport);

	}

	//Other Bussnisnes methods------------------------------------------------------------

	public AbuseReport reconstruct(final AbuseReportForm abuseReportForm, final BindingResult binding) {
		AbuseReport result;
		Animaniac reported;
		final Animaniac principal;

		result = this.create();
		reported = animaniacService.findOne(abuseReportForm.get);
		result.setDescription(abuseReportForm.getDescription());
		result.setSubject(abuseReportForm.getSubject());
		result.setIsSender(true);

		this.validator.validate(result, binding);

		if (!binding.hasErrors())
			for (final Attachment a : abuseReportForm.getAttachments()) {
				a.setAbuseReport(result);
				this.validator.validate(a, binding);
			}
		return result;

	}
}
