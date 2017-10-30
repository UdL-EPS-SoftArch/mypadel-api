package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.CourtType;
import cat.udl.eps.softarch.mypadel.domain.Level;
import cat.udl.eps.softarch.mypadel.domain.PublicMatch;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

import java.time.Duration;
import java.time.ZonedDateTime;

public class CancelMatchStepDefs {

	@When("^I set a new public match on tomorrow at same time$")
	public void setMatchAtTomorrowSameTime() {
		PublicMatch match = new PublicMatch();
		ZonedDateTime startDate = ZonedDateTime.now().plusMinutes(1430);//23 hours and 50 minutes
		Duration duration = Duration.ofMinutes(40);
		match.setStartDate(startDate);
		match.setDuration(duration);
		match.setCourtType(CourtType.INDOOR);
		match.setLevel(Level.ADVANCED);
	}

	@And("^It has been cancelled$")
	public void itHasBeenCancelled() throws Throwable {

	}
}
