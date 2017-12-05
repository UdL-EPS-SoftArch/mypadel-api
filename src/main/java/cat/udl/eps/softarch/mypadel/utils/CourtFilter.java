package cat.udl.eps.softarch.mypadel.utils;

import cat.udl.eps.softarch.mypadel.domain.Court;
import cat.udl.eps.softarch.mypadel.domain.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;

import static cat.udl.eps.softarch.mypadel.utils.CourtTypeMatcher.matchCourtType;

import static cat.udl.eps.softarch.mypadel.utils.ReservationOverlappingChecker.isOverlappingReservation;
import static java.util.stream.Collectors.toList;

@Service
public class CourtFilter {

	public List<Court> filterCompatibleCourtsWithReservation(Reservation pendingReservation, List<Court>
		availableCourts) {
		return availableCourts.parallelStream()
			.filter(court -> matchCourtType(court, pendingReservation))
			.filter(court -> courtCanBeReserved(court, pendingReservation))
			.collect(toList());
	}

	private boolean courtCanBeReserved(Court court, Reservation pendingReservation) {
		return court.getReservations().isEmpty()
			|| !isOverlappingReservation(court, pendingReservation);
	}


}
