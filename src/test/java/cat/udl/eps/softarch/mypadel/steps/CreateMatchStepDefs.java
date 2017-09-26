package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.Match;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.Duration;
import java.util.Date;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class CreateMatchStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @When("^I create a new match$")
    public void iCreateANewMatch() throws Throwable {
        Match match = new Match();
        match.setStartDate(new Date(2017, 10, 1));
        match.setDuration(Duration.ofMinutes(30));
        match.setCancelationDeadline(new Date(2017, 9, 30));
        String message = stepDefs.mapper.writeValueAsString(match);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/match")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(message)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()))
                .andDo(print());
    }

    @And("^A match has been created$")
    public void aMatchHasBeenCreated() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}