package cat.udl.eps.softarch.mypadel.steps;

import cucumber.api.*;
import cucumber.api.java.en.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RemoveCourtSteps {

    @Autowired
    private StepDefs stepDefs;


    @And("^There is an existing court$")
    public void thereIsAnExistingCourt() throws Throwable {
        stepDefs.createCourt();
    }

    @When("^I remove a court$")
    public void iRemoveACourt() throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/courts/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()));
    }

    @And("^A court is unavailable")
    public void aCourtIsUnavaliable() throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/courts/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @And("^The court does not exist$")
    public void theCourtDoesNotExist() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^The court is available")
    public void theCourtIsAvaliable() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
