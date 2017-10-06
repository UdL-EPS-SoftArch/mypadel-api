package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.Player;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class RegisterPlayerStepDef {
    private static final Logger logger = LoggerFactory.getLogger(RegisterAdminStepDefs.class);

    @Autowired
    private StepDefs stepDefs;

    @When("^I register a new player with username \"([^\"]*)\", email \"([^\"]*)\",password \"([^\"]*)\", score (\\d+) and level \"([^\"]*)\"$")
    public void iRegisterANewPlayerWithUsernameEmailPasswordScoreAndLevel(String username, String email, String password, int score, String level) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
		JSONObject player = new JSONObject();
		player.put("username", username);
		player.put("email", email);
		player.put("password", password);
		player.put("score", score);
		player.put("level", level);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(player.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()))
                .andDo(print());
    }

    @And("^It has been created a player with username \"([^\"]*)\", email \"([^\"]*)\", level \"([^\"]*)\", the password and score are not returned$")
    public void itHasBeenCreatedAPlayerWithUsernameEmailLevelThePasswordAndScoreAreNotReturned(String username, String email, String level ) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/players/{username}", username)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.username", is(username)))
                .andExpect(jsonPath("$.email", is(email)))
                .andExpect(jsonPath("$.level", is(level)))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andExpect(jsonPath("$.score").doesNotExist());
    }

    @And("^It has not been created a player with username \"([^\"]*)\"$")
    public void itHasNotBeenCreatedAPlayerWithUsername(String username) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/players/{username}", username)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
