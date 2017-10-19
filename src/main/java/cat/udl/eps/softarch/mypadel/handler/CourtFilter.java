package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.Court;
import cat.udl.eps.softarch.mypadel.domain.CourtType;
import cat.udl.eps.softarch.mypadel.domain.Reservation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CourtFilter {

	protected CourtFilter() {
	}

	protected List<Court> filterCompatibleCourtsWithReservation(Reservation reservation, List<Court> availableCourts) {
		return availableCourts.parallelStream()
			.filter(court -> court.getReservations().isEmpty())
			.filter(court -> {
				CourtType courtType = court.isIndoor() ? CourtType.INDOOR : reservation.getCourtType();
				return Objects.equals(courtType, reservation.getCourtType());
			}).collect(Collectors.toList());
	}

}
