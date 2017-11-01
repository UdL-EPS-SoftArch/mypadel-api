package cat.udl.eps.softarch.mypadel.steps;

import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CheckAvailableCourtsStepdefs {

	@Autowired
	private StepDefs stepDefs;

	@Then("^The match has been canceled$")
	public void theMatchHasBeenCanceled() throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/publicMatches/1")
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(jsonPath("$.isCancelled", is(true))
			);

	}

}
