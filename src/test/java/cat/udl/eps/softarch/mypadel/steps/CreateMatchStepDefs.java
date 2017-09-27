package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.CourtType;
import cat.udl.eps.softarch.mypadel.domain.Level;
import cat.udl.eps.softarch.mypadel.domain.PublicMatch;
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

    private GregorianCalendar cancelationDeadline = new GregorianCalendar(2017, 9, 30);

    @When("^I create a new public match$")
    public void iCreateANewMatch() throws Throwable {
        PublicMatch match = new PublicMatch();
        match.setStartDate(startDate);
        match.setDuration(duration);
        match.setCancelationDeadline(cancelationDeadline);
        match.setCourtType(CourtType.INDOOR);
        match.setLevel(Level.ADVANCED);
        String message = stepDefs.mapper.writeValueAsString(match);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/publicMatches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(message)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()))
                .andDo(print());
    }

    @And("^A match has been created$")
    public void aMatchHasBeenCreated() throws Throwable {
        int id = 1;
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/publicMatches/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.startDate", is("2017-10-31T22:00:00.000+0000")))
                .andExpect(jsonPath("$.duration", is(duration.toString())))
                .andExpect(jsonPath("$.courtType", is(CourtType.INDOOR.toString())))
                .andExpect(jsonPath("$.cancelationDeadline", is("2017-10-29T22:00:00.000+0000")))
                .andExpect(jsonPath("$.level", is(Level.ADVANCED.toString())));
    }
}