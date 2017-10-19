package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.Court;
import cat.udl.eps.softarch.mypadel.domain.CourtType;
import cat.udl.eps.softarch.mypadel.domain.Reservation;

public class CourtTypeMatcher {

	protected static boolean matchCourtType(Reservation reservation, Court court) {
		CourtType courtType = court.isIndoor() ? CourtType.INDOOR : reservation.getCourtType();
		return courtType.equals(reservation.getCourtType());
	}
}
