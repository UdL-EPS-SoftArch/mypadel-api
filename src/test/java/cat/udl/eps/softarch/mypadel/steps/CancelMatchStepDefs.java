package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.controller.CancelationDeadlineController;
import cat.udl.eps.softarch.mypadel.domain.CourtType;
import cat.udl.eps.softarch.mypadel.domain.JoinMatch;
import cat.udl.eps.softarch.mypadel.domain.Level;
import cat.udl.eps.softarch.mypadel.domain.PublicMatch;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import static org.hamcrest.MatcherAssert.assertThat;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CancelMatchStepDefs {

	@Autowired
	private StepDefs stepDefs;

	@Autowired
	private CancelationDeadlineControllerDouble cancelationDeadlineController;


	@When("^I create a new public match on tomorrow at same time$")
	public void createMatchAtTomorrowSameTime() throws Throwable {
		PublicMatch match = new PublicMatch();
		ZonedDateTime startDate = ZonedDateTime.now(ZoneId.of("UTC")).plusHours(23).plusMinutes(35);
		Duration duration = Duration.ofMinutes(40);
		match.setStartDate(startDate);
		match.setDuration(duration);
		match.setCourtType(CourtType.INDOOR);
		match.setLevel(Level.ADVANCED);
		createMatch(match);
	}

	private void createMatch(PublicMatch match) throws Exception {
		String message = stepDefs.mapper.writeValueAsString(match);
		stepDefs.result = stepDefs.mockMvc.perform(
			post("/publicMatches")
				.contentType(MediaType.APPLICATION_JSON)
				.content(message)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());
	}

	@And("^It has been cancelled$")
	public void itHasBeenCancelled() throws Throwable {
		this.cancelationDeadlineController.searchReachedDeadlines();
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/publicMatches/{id}", 1)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(jsonPath("$.cancelled", is(true))
			);
		assertThat(cancelationDeadlineController.mailSend, is(true));
	}
}

@Component
class CancelationDeadlineControllerDouble extends CancelationDeadlineController{
	boolean mailSend = false;
	@Override
	protected void sendMailToPlayers(List<JoinMatch> joinMatches) {
		mailSend=true;
	}
}
