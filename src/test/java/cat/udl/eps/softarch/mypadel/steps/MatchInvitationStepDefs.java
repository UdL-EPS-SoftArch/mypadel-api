package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.MatchInvitation;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.ZonedDateTime;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MatchInvitationStepDefs {
    @Autowired
    private StepDefs stepDefs;



    @And("^It has not been created a new match invitation$")
    public void itHasNotBeenCreatedANewMatchInvitation() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/matchInvitations")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.matchInvitations", hasSize(0)));
	}


	@When("^I create new match invitation for a new match with message \"([^\"]*)\"$")
	public void iCreateNewMatchInvitationForANewMatchWithMessage(String arg0) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		MatchInvitation matchInv = new MatchInvitation();
		matchInv.setMessage(arg0);

		String message = stepDefs.mapper.writeValueAsString(matchInv);
		stepDefs.result = stepDefs.mockMvc.perform(
			post("/matchInvitations")
				.contentType(MediaType.APPLICATION_JSON)
				.content(message)
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print());
	}

	@And("^It has been created a new match invitation with message \"([^\"]*)\"$")
	public void itHasBeenCreatedANewMatchInvitationWithMessage(String arg0) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/matchInvitations/1")//get("/matchInvitations/{id}, id")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) jsonPath("$.message", equalTo(arg0) ));
	}
}
