package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.MatchResult;
import cat.udl.eps.softarch.mypadel.domain.Player;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


public class MatchResultSteps {

	@Autowired
	private StepDefs stepDefs;

	@When("^I register a new MatchResult$")
	public void iCreateANewMatchResult (List<Player> winners, List<Player> losers, boolean draw) throws Throwable {
		MatchResult matchResult = new MatchResult ();
		matchResult.setWinningPair(winners);
		matchResult.setLoosingPair(losers);
		matchResult.setDraw(draw);
		String message = stepDefs.mapper.writeValueAsString(matchResult);
		stepDefs.result = stepDefs.mockMvc.perform(
			post("/matchResult")
				.contentType(MediaType.APPLICATION_JSON)
				.content(message)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());
	}

	@And("^A new MatchResult has been created$")
	public void aMatchResultHasBeenCreated() throws Throwable {
		int id = 1;
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/matchResult/{id}", id)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(jsonPath("$.id", is(id)))
			.andExpect(jsonPath("$.winningPair", is(winningPair.toString())))
			.andExpect(jsonPath("$.losingPair", is(losingPair.toString())))
			.andExpect(jsonPath("$.isDraw", is(isDraw.toString())));
	}
}
