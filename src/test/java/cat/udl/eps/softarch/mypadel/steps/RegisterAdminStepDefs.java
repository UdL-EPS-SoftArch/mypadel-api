package cat.udl.eps.softarch.mypadel.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegisterAdminStepDefs {
    private static final Logger logger = LoggerFactory.getLogger(RegisterAdminStepDefs.class);

    @Autowired
    private StepDefs stepDefs;

    @When("^I register a new admin with username \"([^\"]*)\", email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iRegisterANewAdminWithUsernameEmailAndPassword(String username, String email, String password) throws Throwable {
		JSONObject admin = new JSONObject();
		admin.put("username", username);
		admin.put("email", email);
		admin.put("password", password);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/admins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(admin.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(authenticate()))
                .andDo(print());
    }

    @And("^It has been created a admin with username \"([^\"]*)\", email \"([^\"]*)\" and the password is not returned$")
    public void itHasBeenCreatedAAdminWithUsernameEmailAndPassword(String username, String email) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/admins/{username}", username)
                    .accept(MediaType.APPLICATION_JSON)
                    .with(authenticate()))
                .andDo(print())
                .andExpect(jsonPath("$.username", is(username)))
                .andExpect(jsonPath("$.email", is(email)))
                .andExpect(jsonPath("$.password").doesNotExist());
    }

    @And("^It has not been created a admin with username \"([^\"]*)\"$")
    public void itHasNotBeenCreatedAAdminWithUsername(String username) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/admins/{username}", username)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

	@And("^I can login as \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void iCanLoginAsAndPassword(String email, String password) throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/identity")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.with(httpBasic(email, password)))
			.andDo(print())
			.andExpect(status().isOk());
	}
}
