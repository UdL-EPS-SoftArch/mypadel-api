package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.Court;
import cat.udl.eps.softarch.mypadel.domain.Reservation;
import java.util.List;
import java.util.stream.Collectors;

import static cat.udl.eps.softarch.mypadel.handler.CourtTypeMatcher.matchCourtType;

public class CourtFilter {

	protected CourtFilter() {
	}

	protected List<Court> filterCompatibleCourtsWithReservation(Reservation reservation, List<Court> availableCourts) {
		return availableCourts.parallelStream()
			.filter(court -> court.getReservations().isEmpty())
			.filter(court -> matchCourtType(reservation, court))
			.collect(Collectors.toList());
	}

}
