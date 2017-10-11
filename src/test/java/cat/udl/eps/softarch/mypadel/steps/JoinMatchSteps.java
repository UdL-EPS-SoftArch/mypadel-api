package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.JoinMatch;
import cat.udl.eps.softarch.mypadel.handler.JoinMatchEventHandler;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class JoinMatchSteps {

    @Autowired
    private StepDefs stepDefs;

    JoinMatchEventHandler handler = new JoinMatchEventHandler();


    @When("^I join to a match$")
    public void iJoinToAMatchWithDatetime() throws Throwable {
        JoinMatch joinMatch = new JoinMatch();
		handler.handleAdminPreCreate(joinMatch);
		String message = stepDefs.mapper.writeValueAsString(joinMatch);
        stepDefs.result = stepDefs.mockMvc.perform(
                        post("/joinMatches")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(message)
                                .accept(MediaType.APPLICATION_JSON)
                                .with(authenticate()))
                .andDo(print());
    }

    @Then("^I successfully joined a match$")
    public void iSuccessfullyJoinedAMatch() throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/joinMatches/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /*@And("^I join to a match in the same datetime$")
    public void iJoinToAMatchInTheSameDatetime() throws Throwable {
        JoinMatch joinMatch = new JoinMatch();
        String message = stepDefs.mapper.writeValueAsString(joinMatch);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/joinMatches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(message)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()))
                .andDo(print());
    }

    @And("^I couldn't join the second match$")
    public void iCouldnTJoinTheSecondMatch() throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/joinMatches/2")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()))
                .andDo(print())
                .andExpect(status().isNotFound());
    }*/
}
