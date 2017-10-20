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
			.filter(court -> matchCourtType(court, reservation))
			.filter(court -> courtCanBeReserved(court, reservation))
			.collect(Collectors.toList());
	}

	private boolean courtCanBeReserved(Court court, Reservation reservation) {
		if (court.getReservations().isEmpty())
			return true;
		else
			return ! isOverlappingReservation();
	}

	private boolean isOverlappingReservation() {
		return true;
	}

}
