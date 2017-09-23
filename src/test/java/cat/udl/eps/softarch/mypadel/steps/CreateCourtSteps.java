package cat.udl.eps.softarch.mypadel.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateCourtSteps {

    @Autowired
    private StepDefs stepDefs;

    @And("^A new court is available$")
    public void aNewCourtIsAvailable() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        throw new PendingException();
    }

    @When("^I create a new court$")
    public void iCreateANewCourt() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^A new court has not been created$")
    public void aNewCourtHasNotBeenCreated() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
