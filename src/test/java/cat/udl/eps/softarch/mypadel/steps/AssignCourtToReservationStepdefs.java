package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.domain.Court;
import cat.udl.eps.softarch.mypadel.domain.CourtType;
import cat.udl.eps.softarch.mypadel.domain.Reservation;
import cat.udl.eps.softarch.mypadel.repository.CourtRepository;
import cat.udl.eps.softarch.mypadel.repository.ReservationRepository;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AssignCourtToReservationStepdefs {

	@Autowired
	private StepDefs stepDefs;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private CourtRepository courtRepository;

	@Then("^An available court has been assigned to the reservation$")
	public void anAvailableCourtHasBeenAssignedToTheReservation() throws Throwable {
		Reservation reservation = reservationRepository.findOne(1L);
		Court reservedCourt = reservation.getCourt();
		assertThat(reservedCourt.getId(), is(1));
	}

	@And("^There is a reserved court at (\\d+) - (\\d+) - (\\d+) for (\\d+) minutes$")
	public void thereIsAReservedCourtAtForMinutes(int day, int month, int year, int duration) throws Throwable {
		ZonedDateTime startdate = ZonedDateTime.of(year, month, day, 0, 0, 0,
			0, ZoneId.of("+00:00"));
		Duration durationInMinutes = Duration.ofMinutes(duration);
		Reservation reservation = new Reservation();
		reservation.setStartDate(startdate);
		reservation.setDuration(durationInMinutes);
		reservation.setCourtType(CourtType.UNDEFINED);
		Court court = new Court();
		reservation.setCourt(court);
		courtRepository.save(court);
		reservationRepository.save(reservation);
	}
}
