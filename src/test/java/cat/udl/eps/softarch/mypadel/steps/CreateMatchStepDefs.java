package cat.udl.eps.softarch.mypadel.steps;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import cat.udl.eps.softarch.mypadel.domain.*;
import cat.udl.eps.softarch.mypadel.repository.PlayerRepository;
import cat.udl.eps.softarch.mypadel.repository.UserRepository;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;

public class CreateMatchStepDefs {

    @Autowired
    private StepDefs stepDefs;

    private ZonedDateTime startDate;

    private Duration duration;

    private ZonedDateTime cancelationDeadline;

	private PublicMatch match = new PublicMatch();

	@Autowired
	private UserRepository userRepository;

	@When("^I set a new public match on (\\d+) - (\\d+) - (\\d+) at (\\d+) pm for (\\d+) minutes and deadline (\\d+) - (\\d+) - (\\d+)$")
	public void iCreateANewPublicMatchOnAtPmForMinutesAndDeadline(int day, int month, int year, int hour, int duration,
																  int cancelationDay, int cancelationMonth,
																  int cancelationYear) throws Throwable {
		startDate = ZonedDateTime.of(year, month, day, hour, 0, 0,
			0, ZoneId.of("+00:00"));
		this.duration = Duration.ofMinutes(duration);
		cancelationDeadline = ZonedDateTime.of(cancelationYear, cancelationMonth, cancelationDay,
			hour, 0, 0,0, ZoneId.of("+00:00"));
		match.setStartDate(startDate);
		match.setDuration(this.duration);
		match.setCancelationDeadline(cancelationDeadline);
		match.setCourtType(CourtType.INDOOR);
		match.setLevel(Level.ADVANCED);
	}

	@And("^the user creating it is \"([^\"]*)\"$")
	public void theUserCreatingItIs(String username) throws Throwable {
		match.setMatchCreator((Player)userRepository.findByEmail(username));
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

    @And("^A match has been created$")
    public void aMatchHasBeenCreated() throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
			get("/publicMatches/1")
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
				.andDo(print())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.duration", is(duration.toString())))
				.andExpect(jsonPath("$.startDate", is(parseData(startDate.toString()))))
				.andExpect(jsonPath("$.cancelationDeadline", is(parseData(cancelationDeadline.toString()))))
				.andExpect(jsonPath("$.courtType", is(CourtType.INDOOR.toString())))
				.andExpect(jsonPath("$.level", is(Level.ADVANCED.toString())))
				.andExpect(jsonPath("$._links.matchCreator.href",
					is("http://localhost/publicMatches/1/matchCreator"))
		);
    }

	private String parseData(String data){
		String[] parts = data.split(":");
		return parts[0] + ":00:00" + data.substring(data.length()-1);
	}
}
