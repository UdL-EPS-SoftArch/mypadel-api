package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.Court;
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

public class CreateCourtSteps {

    @Autowired
    private StepDefs stepDefs;

    @When("^I create a new \"([^\"]*)\" court$")
    public void iCreateANewCourt(String indoorOrOutdoor) throws Throwable {
        boolean indoor = indoorOrOutdoor.equalsIgnoreCase("indoor");
        Court court = new Court();
        court.setIndoor(indoor);
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
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/courts/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @And("^A new \"([^\"]*)\" court is \"([^\"]*)\"$")
    public void aNewCourtIs(String indoorOrOutdoor, String available) throws Throwable {
        boolean indoor = indoorOrOutdoor.equalsIgnoreCase("indoor");
        boolean isAvailable = available.equalsIgnoreCase("available");
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/courts/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.available", is(isAvailable)))
                .andExpect(jsonPath("$.indoor", is(indoor)));
    }
}
