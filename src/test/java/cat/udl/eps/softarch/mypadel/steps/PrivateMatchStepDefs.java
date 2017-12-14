package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.CourtType;
import cat.udl.eps.softarch.mypadel.domain.Player;
import cat.udl.eps.softarch.mypadel.domain.PrivateMatch;
import cat.udl.eps.softarch.mypadel.repository.UserRepository;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

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


public class PrivateMatchStepDefs {

	@Autowired
	private StepDefs stepDefs;

	private ZonedDateTime startDate;

	private Duration duration;

	private ZonedDateTime deadLine;

	@Autowired
	private UserRepository userRepository;

	@When("^I create a new private match on (\\d+) - (\\d+) - (\\d+) at (\\d+) hours for (\\d+) minutes and deadline (\\d+) - (\\d+) - (\\d+)$")
	public void iCreateANewPrivateMatchOnAtHoursForMinutesAndDeadline(int day, int month, int year, int hours, int duration, int dayd, int monthd, int yeard) throws Throwable {

		startDate = ZonedDateTime.of(year, month, day, hours, 0, 0,
			0, ZoneId.of("+00:00"));

		this.duration = Duration.ofMinutes(duration);

		deadLine = ZonedDateTime.of(yeard, monthd,dayd,
			hours, 0, 0,0, ZoneId.of("+00:00"));

		PrivateMatch privateMatch = new PrivateMatch();
		privateMatch.setStartDate(startDate);
		privateMatch.setDuration(this.duration);
		privateMatch.setCancelationDeadline(deadLine);
		privateMatch.setCourtType(CourtType.UNDEFINED);

		createPrivateMatch(privateMatch);

	}

	private void createPrivateMatch(PrivateMatch privateMatch) throws Throwable {
		String message = stepDefs.mapper.writeValueAsString(privateMatch);
		stepDefs.result = stepDefs.mockMvc.perform(
			post("/privateMatches")
				.contentType(MediaType.APPLICATION_JSON)
				.content(message)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());
	}




	@And("^A private match has been created$")
	public void aPrivateMatchHasBeenCreated() throws Throwable {
		int id = 1;
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/privateMatches/{id}", id)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(jsonPath("$.id", is(id)))
			.andExpect(jsonPath("$.duration", is(duration.toString())))
			.andExpect(jsonPath("$.startDate", is(parseData(startDate.toString()))))
			.andExpect(jsonPath("$.cancelationDeadline", is(parseData(deadLine.toString()))))
			.andExpect(jsonPath("$.courtType", is(CourtType.UNDEFINED.toString())));
	}

	private Object parseData(String data) {
		String[] parts = data.split(":");
		return parts[0] + ":00:00" + data.substring(data.length()-1);
	}


	@And("^A private match isn't created$")
	public void aPrivateMatchIsnTCreated() throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/privateMatches/1")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}

	@When("^I create a new private match on (\\d+) - (\\d+) - (\\d+) at (\\d+) hours for (\\d+) minutes and deadline (\\d+) - (\\d+) - (\\d+) for the user \"([^\"]*)\"$")
	public void iCreateANewPrivateMatchOnAtHoursForMinutesAndDeadlineForTheUser(int day, int month, int year, int hours, int duration, int dayd, int monthd, int yeard, String username) throws Throwable {
		startDate = ZonedDateTime.of(year, month, day, hours, 0, 0,
			0, ZoneId.of("+00:00"));

		this.duration = Duration.ofMinutes(duration);

		deadLine = ZonedDateTime.of(yeard, monthd,dayd,
			hours, 0, 0,0, ZoneId.of("+00:00"));

		PrivateMatch privateMatch = new PrivateMatch();
		privateMatch.setStartDate(startDate);
		privateMatch.setDuration(this.duration);
		privateMatch.setCancelationDeadline(deadLine);
		privateMatch.setCourtType(CourtType.UNDEFINED);
		privateMatch.setMatchCreator((Player) userRepository.findByEmail(username));

		createPrivateMatch(privateMatch);
	}
}
