package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.Court;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateCourtSteps {

    @Autowired
    private StepDefs stepDefs;

    @And("^A new court is available$")
    public void aNewCourtIsAvailable() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        throw new PendingException();
    }

    @When("^I create a new court$")
    public void iCreateANewCourt() throws Throwable {
        Court court = new Court();
        String message = stepDefs.mapper.writeValueAsString(court);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/courts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(message)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()))
                .andDo(print());
    }

    @And("^A new court has not been created$")
    public void aNewCourtHasNotBeenCreated() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
