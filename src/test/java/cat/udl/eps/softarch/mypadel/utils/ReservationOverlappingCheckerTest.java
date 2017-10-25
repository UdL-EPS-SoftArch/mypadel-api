package cat.udl.eps.softarch.mypadel.utils;

import cat.udl.eps.softarch.mypadel.domain.Court;
import cat.udl.eps.softarch.mypadel.domain.Reservation;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

import static cat.udl.eps.softarch.mypadel.utils.ReservationOverlappingChecker.isOverlappingReservation;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ReservationOverlappingCheckerTest {

	@Test
	public void test_courtHasNoReservations_pendingReservationDoesNotOverlap() {
		Reservation pendingReservation = createReservationFor60MinutesThisMonth(11, 17, 30);
		Court court = createCourtWithoutReservations();

		boolean overlapping = isOverlappingReservation(court, pendingReservation);

		assertThat(overlapping, is(false));

	}

	private Court createCourtWithoutReservations() {
		Court court = new Court();
		court.setReservations(Lists.emptyList());
		return court;
	}

	private Reservation createReservationFor60MinutesThisMonth(int dayOfMonth, int startHour, int startMinutes) {
		ZonedDateTime startdate = ZonedDateTime.of(2017, 10, dayOfMonth, startHour, startMinutes, 0,
			0, ZoneId.of("+00:00"));

		Reservation reservation = new Reservation();
		reservation.setStartDate(startdate);
		reservation.setDuration(Duration.ofMinutes(60));
		return reservation;
	}

	@Test
	public void test_courtWithTwoReservations_pendingReservationOverlapsWithOne() {
		Reservation pendingReservation = createReservationFor60MinutesThisMonth(11, 17, 30);
		Court court = createCourtWithTwoReservations();

		boolean overlapping = isOverlappingReservation(court, pendingReservation);

		assertThat(overlapping, is(true));
	}

	private Court createCourtWithTwoReservations() {
		Reservation confirmedReservation = createReservationFor60MinutesThisMonth(11, 17, 30);
		Reservation confirmedReservation2 = createReservationFor60MinutesThisMonth(15, 17, 30);

		Court court = new Court();
		court.setReservations(Arrays.asList(confirmedReservation, confirmedReservation2));
		return court;
	}

	@Test
	public void test_courtWithThreeReservations_theSecondReservationOverlaps() {
		Reservation pendingReservation = createReservationFor60MinutesThisMonth(11, 18, 00);
		Court court = createCourtWithThreeReservations();

		boolean overlapping = isOverlappingReservation(court, pendingReservation);

		assertThat(overlapping, is(true));

	}

	private Court createCourtWithThreeReservations() {
		Reservation reservationA = createReservationFor60MinutesThisMonth(31, 15, 00);
		Reservation overlappingReservation = createReservationFor60MinutesThisMonth(11, 17, 30);
		Reservation reservationB = createReservationFor60MinutesThisMonth(11, 10, 00);
		Court court = new Court();
		court.setReservations(Arrays.asList(reservationA, overlappingReservation, reservationB));
		return court;
	}

}
