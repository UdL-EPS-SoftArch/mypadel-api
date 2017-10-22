package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.Court;
import cat.udl.eps.softarch.mypadel.domain.Reservation;
import java.util.List;
import java.util.stream.Collectors;

import static cat.udl.eps.softarch.mypadel.handler.CourtTypeMatcher.matchCourtType;

public class CourtFilter {

	protected CourtFilter() {
	}

	protected List<Court> filterCompatibleCourtsWithReservation(Reservation pendingReservation, List<Court> availableCourts) {
		return availableCourts.parallelStream()
			.filter(court -> matchCourtType(court, pendingReservation))
			.filter(court -> courtCanBeReserved(court, pendingReservation))
			.collect(Collectors.toList());
	}

	private boolean courtCanBeReserved(Court court, Reservation pendingReservation) {
		if (court.getReservations().isEmpty())
			return true;
		else
			return ! isOverlappingReservation(court, pendingReservation);
	}

	private boolean isOverlappingReservation(Court court, Reservation pendingReservation) {
		List<Reservation> assignedReservations = court.getReservations();
		for (Reservation reservation : assignedReservations) {

		}
	}

}
