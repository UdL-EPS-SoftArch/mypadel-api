package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.MatchJoin;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.GregorianCalendar;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


public class JoinMatch {

    @Autowired
    private StepDefs stepDefs;

    GregorianCalendar c;

    @When("^I join to a match with datetime \"([^\"]*)\" \"([^\"]*)\"$")
    public void iJoinToAMatchWithDatetime(String date, String time) throws Throwable {
        String[] dateArray = date.split("/");
        String[] timeArray = time.split(":");
        MatchJoin matchJoin = new MatchJoin();
        c = new GregorianCalendar(Integer.parseInt(dateArray[0]),
                Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[2]),
                Integer.parseInt(timeArray[0]), Integer.parseInt(timeArray[1]),
                Integer.parseInt(timeArray[2]));
        matchJoin.setDate(c);
        String message = stepDefs.mapper.writeValueAsString(matchJoin);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/joinmatch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(message)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()))
                .andDo(print());
    }

    @Then("^I join a match$")
    public void iJoinAMatch() throws Throwable {
        long id = 1;
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/joinmatch/{id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.c", is(c)));
    }
}
