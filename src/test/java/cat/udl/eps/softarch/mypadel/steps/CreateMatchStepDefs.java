package cat.udl.eps.softarch.mypadel.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateMatchStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @When("^I click into create public match option$")
    public void aNewCourtIsAvailable() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^A match has been created$")
    public void aMatchHasBeenCreated() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}