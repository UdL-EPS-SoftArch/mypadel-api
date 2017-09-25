package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.*;
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
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/courts/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.available", is(true)));
    }

    @When("^I create a new court$")
    public void iCreateANewCourt() throws Throwable {
        stepDefs.createCourt();
    }


    @And("^A new court has not been created$")
    public void aNewCourtHasNotBeenCreated() throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/courts/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
