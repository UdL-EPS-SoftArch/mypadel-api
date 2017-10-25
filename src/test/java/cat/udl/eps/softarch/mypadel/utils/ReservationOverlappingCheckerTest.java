package cat.udl.eps.softarch.mypadel.utils;

import cat.udl.eps.softarch.mypadel.domain.Court;
import cat.udl.eps.softarch.mypadel.domain.Reservation;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ReservationOverlappingCheckerTest {

	@Test
	public void test_courtHasNoReservations_pendingReservationDoesNotOverlap() {

		ZonedDateTime startdate = ZonedDateTime.of(2017, 10, 11, 17, 30, 0,
			0, ZoneId.of("+00:00"));

		Reservation pendingReservation = new Reservation();
		pendingReservation.setStartDate(startdate);
		pendingReservation.setDuration(Duration.ofMinutes(60));

		Court court = new Court();
		court.setReservations(Lists.emptyList());

		boolean overlapping = ReservationOverlappingChecker.isOverlappingReservation(court, pendingReservation);

		assertThat(overlapping, is(false));

	}

}
