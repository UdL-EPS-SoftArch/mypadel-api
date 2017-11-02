package cat.udl.eps.softarch.mypadel.utils;

import cat.udl.eps.softarch.mypadel.domain.Match;
import cat.udl.eps.softarch.mypadel.domain.Reservation;
import cat.udl.eps.softarch.mypadel.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ConflictiveMatchWithReservationFilter {
	@Autowired
	private MatchRepository matchRepository;


	public List<Match> findConflictiveMatchesWithReservation(Reservation reservation) {
		ZonedDateTime starDateTime = reservation.getStartDate();
		ZonedDateTime endDateTime = reservation.getStartDate().plus(reservation.getDuration());
		return matchRepository.findByStartDateBetween(starDateTime, endDateTime);
	}
}
