package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.Court;
import cat.udl.eps.softarch.mypadel.repository.CourtRepository;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RemoveCourtSteps {

	public static final String AVAILABLE = "available";
	@Autowired
	private StepDefs stepDefs;
	@Autowired
	private CourtRepository courtRepository;

	@When("^I remove a court$")
	public void iRemoveACourt() throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			delete("/courts/1")
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()));
	}

	@And("^The court has been removed")
	public void theCourtHasBeenRemoved() throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/courts/1")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}

	@And("^The court has not been removed")
	public void theCourtHasNotBeenRemoved() throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/courts/1")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}

	@Given("^There is an \"([^\"]*)\" court$")
	public void thereIsAnCourt(String availability) throws Throwable {
		boolean isAvailable = availability.equalsIgnoreCase(AVAILABLE);
		Court court = new Court();
		court.setAvailable(isAvailable);
		courtRepository.save(court);
	}
}
