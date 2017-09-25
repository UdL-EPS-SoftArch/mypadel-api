package cat.udl.eps.softarch.mypadel.steps;

import cucumber.api.java.*;
import cucumber.api.java.en.*;
import org.springframework.test.web.servlet.request.*;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

public class AuthenticationStepDefs {

    private static String currentUsername;
    private static String currentPassword;

    @Before
    public void setup() {
        // Clear authentication credentials at the start of every test.
        AuthenticationStepDefs.currentPassword = "";
        AuthenticationStepDefs.currentUsername = "";
    }

    static RequestPostProcessor authenticate() {
        return currentUsername != null ? httpBasic(currentUsername, currentPassword) : anonymous();
    }

    @Given("^I login as \"([^\"]*)\" with password \"([^\"]*)\"$")
    public void iLoginAsWithPassword(String username, String password) throws Throwable {
        AuthenticationStepDefs.currentUsername = username;
        AuthenticationStepDefs.currentPassword = password;
    }

    @Given("^I'm not logged in$")
    public void iMNotLoggedIn() throws Throwable {
        currentUsername = currentPassword = null;
    }
}
