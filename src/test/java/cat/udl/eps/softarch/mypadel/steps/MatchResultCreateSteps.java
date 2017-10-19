package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.MatchResult;
import cat.udl.eps.softarch.mypadel.domain.RandomGenerator;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MatchResultCreateSteps {

	private RandomGenerator randomGenerator = new RandomGenerator();

	@Autowired
	private StepDefs stepDefs;

	@When("^I create a new MatchResult$")
	public void iCreateANewMatchResult() throws Throwable {
		MatchResult matchResult = randomGenerator.generateMatchResult();
		String message = stepDefs.mapper.writeValueAsString(matchResult);
		stepDefs.result = stepDefs.mockMvc.perform(
			post("/matchResults")
				.contentType(MediaType.APPLICATION_JSON)
				.content(message)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());
	}

	@And("^A new MatchResult is added$")
	public void aNewMatchResultIsAdded() throws Throwable {
		int id = 1;
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/matchResults/{id}", id)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(jsonPath("$.id", is(id)))
			/*.andExpect(jsonPath("$.winningPair", is(winningPair.toString())))
			.andExpect(jsonPath("$.losingPair", is(losingPair.toString())))
			.andExpect(jsonPath("$.isDraw", is(isDraw.toString())))*/;
			//TODO check all the fucking properties
	}

	@And("^The new MatchResult has not been created$")
	public void theNewMatchResultHasNotBeenCreated() throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/matchResults/{id}",1)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}
}
