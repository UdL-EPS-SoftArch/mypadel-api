package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.Player;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegisterPlayerStepDefs {
    private static final Logger logger = LoggerFactory.getLogger(RegisterPlayerStepDefs.class);

    @Autowired
    private StepDefs stepDefs;

    @When("^I register a new player with username \"([^\"]*)\", email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iRegisterANewPlayerWithUsernameEmailAndPassword(String username, String email, String password) throws Throwable {
        Player player = new Player();
        player.setUsername(username);
        player.setEmail(email);
        player.setPassword(password);
        String message = stepDefs.mapper.writeValueAsString(player);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(message)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()))
                .andDo(print());
    }

    @And("^It has been created a player with username \"([^\"]*)\", email \"([^\"]*)\" and the password is not returned$")
    public void itHasBeenCreatedAPlayerWithUsernameEmailAndPassword(String username, String email) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/players/{username}", username)
                    .accept(MediaType.APPLICATION_JSON)
                    .with(authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.username", is(username)))
                .andExpect(jsonPath("$.email", is(email)))
                .andExpect(jsonPath("$.password").doesNotExist());
    }

    @And("^It has not been created a player with username \"([^\"]*)\"$")
    public void itHasNotBeenCreatedAPlayerWithUsername(String username) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/players/{username}", username)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
