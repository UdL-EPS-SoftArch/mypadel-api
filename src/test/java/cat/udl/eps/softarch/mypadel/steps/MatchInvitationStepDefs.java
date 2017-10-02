package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.MatchInvitation;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MatchInvitationStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @When("^I create new match invitation for a new match$")
    public void iCreateNewMatchInvitationForANewMatch() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        MatchInvitation matchInv = new MatchInvitation();
        //matchInv.setId(1);
        //matchInv.setEventDate(date);

        //String message = stepDefs.mapper.writeValueAsString(matchInv);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/matchInvitations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()))
                .andDo(print());
    }



    @And("^It has been created a new match invitation$")//i need to catch parameter
    public void itHasBeenCreatedANewMatchInvitation() throws Throwable {// u pozivu Long id
        // Write code here that turns the phrase above into concrete actions
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/matchInvitations/1")//get("/matchInvitations/{id}, id")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @And("^It has not been created a new match invitation$")//i need to catch parameter
    public void itHasNotBeenCreatedANewMatchInvitation() throws Throwable {// u pozivu Long id
        // Write code here that turns the phrase above into concrete actions
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/matchInvitations")//get("/matchInvitations/{id}, id")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.matchInvitations", hasSize(0)))    ;
    }
}
