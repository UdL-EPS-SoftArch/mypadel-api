package cat.udl.eps.softarch.mypadel.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MatchResultVerificationCreateSteps {

	@Autowired
	private StepDefs stepDefs;

	@When("^I create a new MatchResultVerification$")
	public void iCreateANewMatchResultVerification() throws Throwable {
		throw new NotImplementedException();
	}

	@And("^A new MatchResultVerification is added$")
	public void aNewMatchResultVerificationIsAdded() throws Throwable {
		throw new NotImplementedException();
	}

	@And("^The new MatchResultVerification has not been created$")
	public void theNewMatchResultVerificationHasNotBeenCreated() throws Throwable {
		throw new NotImplementedException();
	}

}
