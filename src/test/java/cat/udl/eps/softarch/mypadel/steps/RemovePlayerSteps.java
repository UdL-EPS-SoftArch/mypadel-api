package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.Player;
import cat.udl.eps.softarch.mypadel.repository.PlayerRepository;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RemovePlayerSteps {

    private static final Logger logger = LoggerFactory.getLogger(RegisterAdminStepDefs.class);

    @Autowired
    private StepDefs stepDefs;

    @Autowired
    private PlayerRepository playerRepository;

    @And("^There is an existing player with username \"([^\"]*)\", email \"([^\"]*)\",password \"([^\"]*)\", score (\\d+) and level \"([^\"]*)\"$")
    public void thereIsAnExistingPlayerWithUsernameEmailPasswordScoreAndLevel(String username, String email, String password, int score, String level) throws Throwable {

        Player player= new Player();
        player.setUsername(username);
        player.setEmail(email);
        player.setPassword(password);
        player.setScore(score);
        player.setLevel(Player.Level.valueOf(level));

        playerRepository.save(player);
    }


    @When("^I remove a player with username \"([^\"]*)\"$")
    public void iRemoveAPlayerWithUsername(String username) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/players/{username}", username)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()))
               ;
    }


    @And("^It has been removed the player with username \"([^\"]*)\"$")
    public void itHasBeenRemovedThePlayerWithUsername(String username) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/players/{username}", username)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }

    @And("^It has not been removed the player with username \"([^\"]*)\"$")
    public void itHasNotBeenRemovedThePlayerWithUsername(String username) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/players/{username}", username)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @When("^I remove a player with username \"([^\"]*)\" without authentication$")
    public void iRemoveAPlayerWithUsernameWithoutAuthentication(String username) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/players/{username}", username)
                        .accept(MediaType.APPLICATION_JSON))

        ;
    }


}
