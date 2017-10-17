package cat.udl.eps.softarch.mypadel.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AssignCourtToReservationStepdefs {
	@When("^A user creates a new Reservation$")
	public void aUserCreatesANewReservation() throws Throwable {
		throw new PendingException();
	}

	@Then("^An available court has been assigned to the reservation$")
	public void anAvailableCourtHasBeenAssignedToTheReservation() throws Throwable {
		throw new PendingException();
	}

	@Given("^There is a reserved court at <day> - <month> - <year> for <minutes> minutes with " +
		"CourtType <type>$")
	public void thereIsAReservedCourtAtDayMonthYearForMinutesMinutesWithCourtTypeType() throws Throwable {
		throw new PendingException();
	}

	@Given("^There is an \"([^\"]*)\" reserved court at (\\d+) - (\\d+) - (\\d+) for (\\d+) minutes$")
	public void thereIsAnReservedCourtAtForMinutes(String courtType,
												   int day, int month, int year,
												   int duration) throws Throwable {
		throw new PendingException();
	}

	@When("^I assign a court manually$")
	public void iAssignACourtManually() throws Throwable {
		throw new PendingException();
	}

	@And("^The court has not been assigned$")
	public void theCourtHasNotBeenAssigned() throws Throwable {
		throw new PendingException();
	}
}
