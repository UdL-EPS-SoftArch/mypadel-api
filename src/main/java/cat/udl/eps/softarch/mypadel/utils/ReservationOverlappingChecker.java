package cat.udl.eps.softarch.mypadel.utils;

import cat.udl.eps.softarch.mypadel.domain.Court;
import cat.udl.eps.softarch.mypadel.domain.Reservation;

import java.time.ZonedDateTime;
import java.util.List;

public class ReservationOverlappingChecker {

	public static boolean isOverlappingReservation(Court court, Reservation pendingReservation) {
		List<Reservation> assignedReservations = court.getReservations();
		boolean overlapping = false;
		for (Reservation other : assignedReservations) {
			overlapping = checkDateCollision(pendingReservation, other);
			if (overlapping)
				break;
		}
		return overlapping;
	}

	private static boolean checkDateCollision(Reservation pendingReservation, Reservation other) {
		ZonedDateTime pendingStartDate = pendingReservation.getStartDate();
		ZonedDateTime pendingEndDate = pendingStartDate.plus(pendingReservation.getDuration());
		ZonedDateTime otherStartDate = other.getStartDate();
		ZonedDateTime otherEndDate = otherStartDate.plus(other.getDuration());
		if (otherEndDate.isBefore(pendingStartDate)) {
			return false;
		} else if (otherStartDate.isAfter(pendingEndDate)) {
			return false;
		} else {
			return true;
		}
	}
}
