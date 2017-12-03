package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.repository.MatchResultRepository;
import cat.udl.eps.softarch.mypadel.repository.PlayerRepository;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MatchResultVerificationCreateSteps {

	@Autowired
	private StepDefs stepDefs;

	@Autowired
	private RegisterPlayerStepDef registerPlayerStepDef;

	@Autowired
	private MatchResultCreateSteps matchResultCreateSteps;

	@Autowired
	private MatchResultRepository matchResultRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@And("^There is a MatchResult for the public match player on \"([^\"]*)\"$")
	public void thereIsAMatchResultForThePublicMatchPlayerOn(String dateString) throws Throwable {
		final DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
		final Date date=dateFormat.parse(dateString);
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I agree through a MatchResultVerification with the MatchResult of the match played on \"([^\"]*)\"$")
	public void iAgreeThroughAMatchResultVerificationWithTheMatchResultOfTheMatchPlayedOn(String arg0) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@And("^A new MatchResultVerification is added for the MatchResult of the match played on \"([^\"]*)\"$")
	public void aNewMatchResultVerificationIsAddedForTheMatchResultOfTheMatchPlayedOn(String arg0) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}
}
