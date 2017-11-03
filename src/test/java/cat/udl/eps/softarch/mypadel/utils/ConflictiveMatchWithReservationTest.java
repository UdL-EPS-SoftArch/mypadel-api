package cat.udl.eps.softarch.mypadel.utils;

import cat.udl.eps.softarch.mypadel.domain.CourtType;
import cat.udl.eps.softarch.mypadel.domain.Match;
import cat.udl.eps.softarch.mypadel.domain.Reservation;
import cat.udl.eps.softarch.mypadel.repository.MatchRepository;
import cat.udl.eps.softarch.mypadel.repository.ReservationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ConflictiveMatchWithReservationTest {
	@Autowired
	private ConflictiveMatchWithReservationFilter conflictiveMatchWithReservationFilter;

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Test
	public void test_reservationHasConflictiveMatches_matchesWillBeCanceled() {
		Match match = createMatch(11, 18, 30, 60);
		Reservation reservation = createReservationForXMinutesThisMonth(11, 18, 30, 60);
		List<Match> conflictiveMatches = conflictiveMatchWithReservationFilter.findConflictiveMatchesWithReservation
			(reservation);
		assertThat(conflictiveMatches.isEmpty(), is(false));
	}

	private Match createMatch(int dayOfMonth, int startHour, int startMinutes, int durationInMinutes) {
		ZonedDateTime startdate = ZonedDateTime.of(2017, 10, dayOfMonth, startHour, startMinutes, 0,
			0, ZoneId.of("+00:00"));
		Duration duration = Duration.ofMinutes(durationInMinutes);
		Match match = new Match();
		match.setStartDate(startdate);
		match.setDuration(duration);
		match.setCourtType(CourtType.INDOOR);
		matchRepository.save(match);
		return match;
	}

	private Reservation createReservationForXMinutesThisMonth(int dayOfMonth, int startHour, int startMinutes, int
		durationInMinutes) {
		ZonedDateTime startdate = ZonedDateTime.of(2017, 10, dayOfMonth, startHour, startMinutes, 0,
			0, ZoneId.of("+00:00"));

		Reservation reservation = new Reservation();
		reservation.setStartDate(startdate);
		reservation.setDuration(Duration.ofMinutes(durationInMinutes));
		reservation.setCourtType(CourtType.INDOOR);
		reservationRepository.save(reservation);
		return reservation;
	}

	@Test
	public void test_reservationHasNotConflictiveMatches_matchesWillNotBeCanceled() {
		Match match = createMatch(11, 11, 30, 60);
		Reservation reservation = createReservationForXMinutesThisMonth(11, 18, 30, 60);
		List<Match> matches = conflictiveMatchWithReservationFilter.findConflictiveMatchesWithReservation(reservation);
		assertThat(matches.isEmpty(), is(true));
	}
}
