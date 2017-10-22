package cat.udl.eps.softarch.mypadel.steps;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import cat.udl.eps.softarch.mypadel.domain.*;
import cat.udl.eps.softarch.mypadel.repository.UserRepository;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

public class CreateMatchStepDefs {

	@Autowired
	private StepDefs stepDefs;

	private ZonedDateTime startDate;

	private Duration duration;

	private PublicMatch match = new PublicMatch();

	@Autowired
	private UserRepository userRepository;

	private int id;

	@When("^I set a new public match on (\\d+) - (\\d+) - (\\d+) at (\\d+) pm for (\\d+) minutes$")
	public void iSetANewPublicMatchOnAtPmForMinutesAndDeadline(int day, int month, int year, int hour, int duration) throws Throwable {
		startDate = ZonedDateTime.of(year, month, day, hour, 0, 0,
			0, ZoneId.of("+00:00"));
		this.duration = Duration.ofMinutes(duration);
		match.setStartDate(startDate);
		match.setDuration(this.duration);
		match.setCourtType(CourtType.INDOOR);
		match.setLevel(Level.ADVANCED);
	}

	@And("^the user creating it is \"([^\"]*)\"$")
	public void theUserCreatingItIs(String username) throws Throwable {
		match.setMatchCreator((Player) userRepository.findByEmail(username));
	}

	@And("^I create it$")
	public void iCreateIt() throws Throwable {
		String message = stepDefs.mapper.writeValueAsString(match);
		stepDefs.result = stepDefs.mockMvc.perform(
			post("/publicMatches")
				.contentType(MediaType.APPLICATION_JSON)
				.content(message)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());
	}

	@And("^I create a match with a similar hour, (\\d+) pm$")
	public void iCreateAMatchWithASimilarHourPm(int matchHour) throws Throwable {
		match.setStartDate(match.getStartDate().withHour(matchHour));
		String message = stepDefs.mapper.writeValueAsString(match);
		stepDefs.result = stepDefs.mockMvc.perform(
			post("/publicMatches")
				.contentType(MediaType.APPLICATION_JSON)
				.content(message)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());
	}

	@And("^A match with the id (\\d+) has been created$")
	public void aMatchWithTheIdHasBeenCreated(int id) throws Throwable {
		this.id = id;
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/publicMatches/{id}", this.id)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(jsonPath("$.id", is(this.id)))
			.andExpect(jsonPath("$.duration", is(duration.toString())))
			.andExpect(jsonPath("$.startDate", is(parseData(startDate.toString()))))
			.andExpect(jsonPath("$.courtType", is(CourtType.INDOOR.toString())))
			.andExpect(jsonPath("$.cancelationDeadline", is(parseData(startDate.minusDays(1).toString()))))
			.andExpect(jsonPath("$.level", is(Level.ADVANCED.toString()))
			);
	}

	private String parseData(String data) {
		String[] parts = data.split(":");
		return parts[0] + ":00:00" + data.substring(data.length() - 1);
	}

	@And("^The match creator is \"([^\"]*)\"$")
	public void theMatchCreatorIs(String player) throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/publicMatches/{id}/matchCreator", id)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(jsonPath("$.username", is(player))
		);
	}

	@And("^A join match with the id (\\d+) has been created, having the match (\\d+) and the player \"([^\"]*)\"$")
	public void aJoinMatchHasBeenCreatedHavingTheMatchAndThePlayer(int joinId, int matchId, String playerUsername) throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/joinMatches/{id}/player", joinId)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(jsonPath("$.username", is(playerUsername))
		);
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/joinMatches/{id}/match", joinId)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(jsonPath("$.id", is(matchId))
		);
	}
}
