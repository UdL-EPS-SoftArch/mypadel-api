package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.CourtType;
import cat.udl.eps.softarch.mypadel.domain.CustomMatch;
import cat.udl.eps.softarch.mypadel.domain.MatchJoinRequest;
import cat.udl.eps.softarch.mypadel.domain.Player;
import cat.udl.eps.softarch.mypadel.repository.CustomMatchRepository;
import cat.udl.eps.softarch.mypadel.repository.UserRepository;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CreateMatchJoinRequestSteps {

	@Autowired
	private StepDefs stepDefs;
	private ZonedDateTime startDate;

	private Duration duration;

	private ZonedDateTime cancelationDeadline;
	private CustomMatch match;
	List<CustomMatch> matches=new ArrayList<>();
	@Autowired
	UserRepository userRepository;
	@Autowired
	CustomMatchRepository customMatchRepository;

	private Player player;

	@And("^There is a custom match created on (\\d+) - (\\d+) - (\\d+) with duration (\\d+) minutes and deadline (\\d+) - (\\d+) - (\\d+)$")
	public void thereIsACustomMatchCreatedOnWithDurationMinutesAndDeadline(int day, int month, int year, int duration, int cancelationDay, int cancelationMonth, int cancelationYear) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
			startDate = ZonedDateTime.of(year, month, day, 0, 0, 0,
				0, ZoneId.of("+00:00"));
			this.duration = Duration.ofMinutes(duration);
			cancelationDeadline = ZonedDateTime.of(cancelationYear, cancelationMonth, cancelationDay,
				0, 0, 0,0, ZoneId.of("+00:00"));

			match = new CustomMatch();
			match.setStartDate(startDate);
			match.setDuration(this.duration);
			match.setCancelationDeadline(cancelationDeadline);
			match.setCourtType(CourtType.INDOOR);
			String message = stepDefs.mapper.writeValueAsString(match);
			stepDefs.result = stepDefs.mockMvc.perform(
				post("/customMatches")
					.contentType(MediaType.APPLICATION_JSON)
					.content(message)
					.accept(MediaType.APPLICATION_JSON)
					.with(authenticate()))
				.andDo(print());



		}


	@When("^The player \"([^\"]*)\" creates a new matchJoinRequest for the match on (\\d+) - (\\d+) - (\\d+) with message \"([^\"]*)\"$")
	public void thePlayerCreatesANewMatchJoinRequestForTheMatchOnWithMessage(String username, int day, int month, int year, String message) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		startDate = ZonedDateTime.of(year, month, day, 0, 0, 0,
			0, ZoneId.of("+00:00"));

		MatchJoinRequest request = new MatchJoinRequest();
		long id=1;
		match=customMatchRepository.findOne(id);
		request.setCustomMatch(match);
		request.setMessage(message);
		String m = stepDefs.mapper.writeValueAsString(request);
		stepDefs.result = stepDefs.mockMvc.perform(
			post("/matchJoinRequests")
				.contentType(MediaType.APPLICATION_JSON)
				.content(m)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());

	}

	/*@And("^A matchJoinRequest has been created by player \"([^\"]*)\"$")
	public void aMatchJoinRequestHasBeenCreatedByPlayer(String username) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		int id = 1;
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/matchJoinRequests/{id}", id)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(jsonPath("$.player", is(username)));
*
	}**/



	@And("^A matchJoinRequest has been created by player \"([^\"]*)\" for the required match$")
	public void aMatchJoinRequestHasBeenCreatedByPlayerForTheRequiredMatch(String username) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		int id = 1;
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/matchJoinRequests/{id}", id)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(status().isOk());

		stepDefs.result = stepDefs.mockMvc.perform(
			get("/matchJoinRequests/{id}/customMatch", id)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(status().isOk());
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/matchJoinRequests/{id}/player", id)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(status().isOk());
	}
}

