package cat.udl.eps.softarch.mypadel.utils;

import cat.udl.eps.softarch.mypadel.domain.Court;
import cat.udl.eps.softarch.mypadel.domain.CourtType;
import cat.udl.eps.softarch.mypadel.domain.Match;
import cat.udl.eps.softarch.mypadel.domain.Reservation;
import cat.udl.eps.softarch.mypadel.repository.CourtRepository;
import cat.udl.eps.softarch.mypadel.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

import static cat.udl.eps.softarch.mypadel.domain.CourtType.INDOOR;
import static cat.udl.eps.softarch.mypadel.domain.CourtType.OUTDOOR;
import static java.util.stream.Collectors.toList;

@Service
public class ConflictiveMatchWithReservationFilter {

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private CourtRepository courtRepository;

	private List<Court> availableCourts;
	private boolean indoorCourtAvailable;
	private boolean outdoorCourtAvailable;

	private void initAvailableCourtTypes() {
		availableCourts = courtRepository.findByAvailableTrue();
		for (Court c : availableCourts) {
			if (c.isIndoor())
				indoorCourtAvailable = true;
			else
				outdoorCourtAvailable = true;
		}
	}

	public List<Match> findConflictiveMatchesWithReservation(Reservation reservation) {
		initAvailableCourtTypes();
		ZonedDateTime starDateTime = reservation.getStartDate();
		ZonedDateTime endDateTime = reservation.getStartDate().plus(reservation.getDuration());
		List<Match> matches = matchRepository.findByStartDateBetween(starDateTime, endDateTime)
			.stream()
			.filter(m -> !isReserved(m))
			.filter(m -> !hasAvailableCourt(m))
			.collect(toList());
		return matches;
	}

	private boolean isReserved(Match match) {
		return match.getReservation() != null;
	}

	private boolean hasAvailableCourt(Match match) {
		CourtType courtType = match.getCourtType();
		if (courtType == INDOOR)
			return indoorCourtAvailable;
		else if (courtType == OUTDOOR)
			return outdoorCourtAvailable;
		else
			return indoorCourtAvailable || outdoorCourtAvailable;
	}
}
