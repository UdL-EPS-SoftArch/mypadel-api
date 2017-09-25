package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.*;
import cucumber.api.*;
import cucumber.api.java.en.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CreateCourtSteps {

    @Autowired
    private StepDefs stepDefs;

    @And("^A new court is available$")
    public void aNewCourtIsAvailable() throws Throwable {
        Integer courtId = 1;
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/courts/{id}", courtId)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.available", is(true)));
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
