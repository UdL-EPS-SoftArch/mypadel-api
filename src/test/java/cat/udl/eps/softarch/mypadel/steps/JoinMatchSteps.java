package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.CourtType;
import cat.udl.eps.softarch.mypadel.domain.JoinMatch;
import cat.udl.eps.softarch.mypadel.domain.Level;
import cat.udl.eps.softarch.mypadel.domain.PublicMatch;
import cat.udl.eps.softarch.mypadel.handler.JoinMatchEventHandler;
import cat.udl.eps.softarch.mypadel.repository.PublicMatchRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class JoinMatchSteps {

    @Autowired
    private StepDefs stepDefs;

	JoinMatch joinMatch = new JoinMatch();

	PublicMatch match = new PublicMatch();

	private ZonedDateTime startDate;

	private Duration duration1;

	private ZonedDateTime cancelationDeadline;

	@Autowired
	PlayerRepository playerRepository;
	PublicMatchRepository publicMatchRepository;

	@When("^I join to a match$")
    public void iJoinToAMatchWithDatetime() throws Throwable {
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

	@And("^the user creating it is \"([^\"]*)\"$")
	public void theUserCreatingItIs(String player) throws Throwable {
		joinMatch.setPlayer(playerRepository.findOne(player));
	}


	@When("^I join to a match on (\\d+) - (\\d+) - (\\d+) at (\\d+) pm for (\\d+) minutes and deadline (\\d+) - (\\d+) - (\\d+)$")
	public void iJoinToAMatchOnAtPmForMinutesAndDeadline(int day, int month, int year, int hour, int duration,
														 int cancelationDay, int cancelationMonth,
														 int cancelationYear) throws Throwable {

		startDate = ZonedDateTime.of(year, month, day, hour, 0, 0,0, ZoneId.of("+00:00"));
		duration1 = Duration.ofMinutes(duration);
		cancelationDeadline = ZonedDateTime.of(cancelationYear, cancelationMonth, cancelationDay,
			hour, 0, 0,0, ZoneId.of("+00:00"));
		match.setStartDate(startDate);
		match.setDuration(duration1);
		match.setCancelationDeadline(cancelationDeadline);
		match.setCourtType(CourtType.INDOOR);
		match.setLevel(Level.ADVANCED);
		joinMatch.setMatch(match);
		String message = stepDefs.mapper.writeValueAsString(joinMatch);

		stepDefs.result = stepDefs.mockMvc.perform(
			post("/joinMatches")
				.contentType(MediaType.APPLICATION_JSON)
				.content(message)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());
	}
}
