
package administrator;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.DashboardService;
import controllers.AbstractController;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {

	@Autowired
	private DashboardService	dashboardService;


	// Constructors -----------------------------------------------------------
	public DashboardAdministratorController() {
		super();
	}


	private static DecimalFormat	df2	= new DecimalFormat("0.##");


	// List --------------------------------------------------------------------

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView result;
		result = new ModelAndView("dashboard/administrator/dashboard");

		result.addObject("requestURI", "dashboard/administrator/dashboard.do");

		result.addObject("averageOfMessagesReceivedPerActor", DashboardAdministratorController.df2.format(this.dashboardService.averageOfMessagesReceivedPerActor()));
		result.addObject("minOfMessagesReceivedPerActor", DashboardAdministratorController.df2.format(this.dashboardService.minOfMessagesReceivedPerActor()));
		result.addObject("maxOfMessagesReceivedPerActor", DashboardAdministratorController.df2.format(this.dashboardService.maxOfMessagesReceivedPerActor()));

		result.addObject("averageOfMessagesSentPerActor", DashboardAdministratorController.df2.format(this.dashboardService.averageOfMessagesSentPerActor()));
		result.addObject("minOfMessagesSentPerActor", DashboardAdministratorController.df2.format(this.dashboardService.minOfMessagesSentPerActor()));
		result.addObject("maxOfMessagesSentPerActor", DashboardAdministratorController.df2.format(this.dashboardService.maxOfMessagesSentPerActor()));

		result.addObject("averageOfCommentsWrittenPerActor", DashboardAdministratorController.df2.format(this.dashboardService.averageOfCommentsWrittenPerActor()));
		result.addObject("minOfCommentsWrittenPerActor", DashboardAdministratorController.df2.format(this.dashboardService.minOfCommentsWrittenPerActor()));
		result.addObject("maxOfCommentsWrittenPerActor", DashboardAdministratorController.df2.format(this.dashboardService.maxOfCommentsWrittenPerActor()));

		result.addObject("averageOfCommentsWrittenInCommentable", DashboardAdministratorController.df2.format(this.dashboardService.averageOfCommentsWrittenInCommentable()));

		result.addObject("reportedAnimaniacsRatio", DashboardAdministratorController.df2.format(this.dashboardService.reportedAnimaniacsRatio()));

		result.addObject("animaniacsByReports", this.dashboardService.animaniacsByReports());

		result.addObject("totalBannersDisplayed", this.dashboardService.totalBannersDisplayed());

		result.addObject("animaniacsWith10PercentMoreAcceptedApplicationsThanAvg", this.dashboardService.animaniacsWith10PercentMoreAcceptedApplicationsThanAvg());

		result.addObject("top3PopularAnimaniacs", this.dashboardService.top3PopularAnimaniacs());

		result.addObject("top3AnimaniacsByPetNumber", this.dashboardService.top3AnimaniacsByPetNumber());

		result.addObject("partnerWithMoreBanners", this.dashboardService.partnerWithMoreBanners());

		result.addObject("partnerWithHighestFee", this.dashboardService.partnerWithHighestFee());

		result.addObject("certifiedPetRatio", DashboardAdministratorController.df2.format(this.dashboardService.certifiedPetRatio()));

		result.addObject("animaniacsWithMorePets", DashboardAdministratorController.df2.format(this.dashboardService.animaniacsWithMorePets()));

		return result;
	}
}
