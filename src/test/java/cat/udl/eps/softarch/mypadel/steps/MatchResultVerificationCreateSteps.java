package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.MatchResult;
import cat.udl.eps.softarch.mypadel.domain.MatchResultVerification;
import cat.udl.eps.softarch.mypadel.domain.Player;
import cat.udl.eps.softarch.mypadel.repository.MatchResultRepository;
import cat.udl.eps.softarch.mypadel.repository.PlayerRepository;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class MatchResultVerificationCreateSteps {

	@Autowired
	private StepDefs stepDefs;

	@Autowired
	private MatchResultRepository matchResultRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@And("^There is a MatchResult for the public match player on \"([^\"]*)\"$")
	public void thereIsAMatchResultForThePublicMatchPlayerOn(String dateString) throws Throwable {
		final DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
		final Date date=dateFormat.parse(dateString);
		final MatchResult matchResult = matchResultRepository.findOne(1);
	}

	@When("^I agree through a MatchResultVerification with the MatchResult of the match played on \"([^\"]*)\"$")
	public void iAgreeThroughAMatchResultVerificationWithTheMatchResultOfTheMatchPlayedOn(String dateString) throws Throwable {
		final Player player = playerRepository.findOne("testplayer");
		final MatchResult matchResult = matchResultRepository.findOne(1);

		final MatchResultVerification matchResultVerification = new MatchResultVerification();
		matchResultVerification.setAgrees(true);
		matchResultVerification.setMatchToAgree(matchResult);
		matchResultVerification.setPlayer(player);

		final String message = stepDefs.mapper.writeValueAsString(matchResultVerification);
		stepDefs.result = stepDefs.mockMvc.perform(
			post("/matchResultVerifications")
				.contentType(MediaType.APPLICATION_JSON)
				.content(message)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());
	}

	@And("^A new MatchResultVerification is added for the MatchResult of the match played on \"([^\"]*)\"$")
	public void aNewMatchResultVerificationIsAddedForTheMatchResultOfTheMatchPlayedOn(String dateString) throws Throwable {
		stepDefs.result=stepDefs.mockMvc.perform(
			get("/matchResultVerifications/1")
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andExpect(jsonPath("$.id", is(1)))
			.andExpect(jsonPath("$._links.matchToAgree.href", is("http://localhost/matchResultVerifications/1/matchToAgree")))
			.andExpect(jsonPath("$._links.player.href", is("http://localhost/matchResultVerifications/1/player")));
	}
}
