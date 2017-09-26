package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.Match;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.Duration;
import java.util.GregorianCalendar;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CreateMatchStepDefs {

    @Autowired
    private StepDefs stepDefs;

    private GregorianCalendar startDate = new GregorianCalendar(2017, 10, 1);

    private Duration duration = Duration.ofMinutes(30);

    @When("^I create a new match$")
    public void iCreateANewMatch() throws Throwable {
        Match match = new Match();
        match.setStartDate(startDate);
        match.setDuration(duration);
        match.setCancelationDeadline(new GregorianCalendar(2017, 9, 30));
        match.setCourt(Match.courtType.INDOOR);
        String message = stepDefs.mapper.writeValueAsString(match);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/matches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(message)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()))
                .andDo(print());
    }

    @And("^A match has been created$")
    public void aMatchHasBeenCreated() throws Throwable {
        long id = 1;
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/matches/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.startDate", is(startDate)))
                .andExpect(jsonPath("$.courtType", is(Match.courtType.INDOOR)));
    }
}