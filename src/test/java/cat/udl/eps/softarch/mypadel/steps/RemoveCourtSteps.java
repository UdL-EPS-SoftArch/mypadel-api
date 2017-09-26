package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.*;
import cat.udl.eps.softarch.mypadel.repository.*;
import cucumber.api.java.en.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RemoveCourtSteps {

    @Autowired
    private StepDefs stepDefs;
    @Autowired
    private CourtRepository courtRepository;


    @And("^There is an existing court$")
    public void thereIsAnExistingCourt() throws Throwable {
        Court court = new Court();
        courtRepository.save(court);
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

    }

    @And("^The court is available")
    public void theCourtIsAvaliable() throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/courts/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
