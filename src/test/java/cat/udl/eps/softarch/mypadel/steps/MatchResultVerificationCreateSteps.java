package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.Level;
import cat.udl.eps.softarch.mypadel.domain.MatchResultVerification;
import cat.udl.eps.softarch.mypadel.domain.RandomGenerator;
import cat.udl.eps.softarch.mypadel.repository.MatchResultRepository;
import cat.udl.eps.softarch.mypadel.repository.PlayerRepository;
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

public class MatchResultVerificationCreateSteps {

	private RandomGenerator randomGenerator = new RandomGenerator();

	@Autowired
	private StepDefs stepDefs;

	@Autowired
	private RegisterPlayerStepDef registerPlayerStepDef;

	@Autowired
	private MatchResultCreateSteps matchResultCreateSteps;

	@Autowired
	private MatchResultRepository matchResultRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@When("^I create a new MatchResultVerification$")
	public void iCreateANewMatchResultVerification() throws Throwable {
		matchResultCreateSteps.iCreateANewMatchResult();
		registerPlayerStepDef.iRegisterANewPlayerWithUsernameEmailPasswordScoreAndLevel("username", "email@gmail.com", "password", 5, Level.ADVANCED.toString());
		MatchResultVerification matchResultVerification = randomGenerator.generateMatchResultVerification();
		matchResultVerification.setMatchToAgree(matchResultRepository.findAll().iterator().next());
		matchResultVerification.setPlayer(playerRepository.findAll().iterator().next());
		String message = stepDefs.mapper.writeValueAsString(matchResultVerification);
		stepDefs.result = stepDefs.mockMvc.perform(
			post("/matchResultVerifications")
				.contentType(MediaType.APPLICATION_JSON)
				.content(message)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());
	}

	@And("^A new MatchResultVerification is added$")
	public void aNewMatchResultVerificationIsAdded() throws Throwable {
		int id = 1;
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/matchResultVerifications/{id}", id)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(jsonPath("$.id", is(id)));
	}

	@And("^The new MatchResultVerification has not been created$")
	public void theNewMatchResultVerificationHasNotBeenCreated() throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/matchResultVerifications/{id}", 1)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}

}
