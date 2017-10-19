package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.*;
import cat.udl.eps.softarch.mypadel.repository.PublicMatchRepository;
import cat.udl.eps.softarch.mypadel.repository.UserRepository;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import cat.udl.eps.softarch.mypadel.repository.PlayerRepository;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



public class JoinMatchSteps {

    @Autowired
    private StepDefs stepDefs;


	JoinMatch joinMatch = new JoinMatch();

	PublicMatch match = new PublicMatch();

	private ZonedDateTime startDate;

	private Duration duration;

	private ZonedDateTime cancelationDeadline;

	@Autowired
	UserRepository playerRepository;

	@Autowired
	PublicMatchRepository pub;

	private int id;
	private static long i = 1;

	@When("^I join to a match$")
    public void iJoinToAMatch() throws Throwable {
		String message = stepDefs.mapper.writeValueAsString(joinMatch);
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/joinMatches")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(message)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(authenticate()))
                .andDo(print());
    }

    @Then("^I successfully joined a match$")
    public void iSuccessfullyJoinedAMatch() throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/joinMatches/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()))
                .andDo(print())
                .andExpect(status().isOk());
    }

	@And("^the user joining it is \"([^\"]*)\"$")
	public void theUserCreatingItIs(String player) throws Throwable {
		joinMatch.setPlayer((Player) playerRepository.findByEmail(player));
	}


	@When("^I join to a created match$")
	public void iJoinToACreatedMatch() throws Throwable {
		joinMatch.setMatch(pub.findOne(i));
		String message = stepDefs.mapper.writeValueAsString(joinMatch);
		stepDefs.result = stepDefs.mockMvc.perform(
			post("/joinMatches")
				.contentType(MediaType.APPLICATION_JSON)
				.content(message)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());

	}

	@And("^There is public match on (\\d+) - (\\d+) - (\\d+) at (\\d+) pm for (\\d+) minutes and deadline (\\d+) - (\\d+) - (\\d+)$")
	public void thereIsPublicMatchOnAtPmForMinutesAndDeadline(int day, int month, int year, int hour, int duration,
															  int cancelationDay, int cancelationMonth,
															  int cancelationYear) throws Throwable {
		startDate = ZonedDateTime.of(year, month, day, hour, 0, 0,
			0, ZoneId.of("+00:00"));
		this.duration = Duration.ofMinutes(duration);
		cancelationDeadline = ZonedDateTime.of(cancelationYear, cancelationMonth, cancelationDay,
			hour, 0, 0, 0, ZoneId.of("+00:00"));
		match.setStartDate(startDate);
		match.setDuration(this.duration);
		match.setCancelationDeadline(cancelationDeadline);
		match.setCourtType(CourtType.INDOOR);
		match.setLevel(Level.ADVANCED);

		String message = stepDefs.mapper.writeValueAsString(match);
		stepDefs.result = stepDefs.mockMvc.perform(
			post("/publicMatches")
				.contentType(MediaType.APPLICATION_JSON)
				.content(message)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());
	}

	@Then("^A player has successfully joined a match$")
	public void aPlayerHasSuccessfullyJoinedAMatch() throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/joinMatches/1")
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(status().isOk());
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/joinMatches/1/player")
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(status().isOk());
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/joinMatches/1/publicMatch")
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(status().isOk());
	}
}
