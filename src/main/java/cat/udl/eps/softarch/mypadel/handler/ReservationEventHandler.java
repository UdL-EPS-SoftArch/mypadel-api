package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.Court;
import cat.udl.eps.softarch.mypadel.domain.Match;
import cat.udl.eps.softarch.mypadel.domain.Reservation;
import cat.udl.eps.softarch.mypadel.handler.exception.CompatibleCourtNotFoundException;
import cat.udl.eps.softarch.mypadel.repository.CourtRepository;
import cat.udl.eps.softarch.mypadel.utils.CancellationMailSender;
import cat.udl.eps.softarch.mypadel.utils.ConflictiveMatchWithReservationFilter;
import cat.udl.eps.softarch.mypadel.utils.CourtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@RepositoryEventHandler
public class ReservationEventHandler {

	@Autowired
	private CourtRepository courtRepository;

	@Autowired
	private ConflictiveMatchWithReservationFilter conflictiveMatchWithReservationFilter;

	@Autowired
	private CourtFilter courtFilter;

	@Autowired
	private CancellationMailSender mailer;

	@HandleBeforeCreate
	@Transactional
	public void handleReservationPreCreate(Reservation reservation) {
		List<Court> availableCourts = courtRepository.findByAvailableTrue();
		List<Court> compatibleCourts = courtFilter.filterCompatibleCourtsWithReservation(reservation, availableCourts);
		if (compatibleCourts.isEmpty()) {
			throw new CompatibleCourtNotFoundException("Reservation aborted, there are no courts compatible");
		} else {
			Court court = compatibleCourts.get(0);
			reservation.setCourt(court);
		}
	}

	@HandleAfterCreate
	@Transactional
	public void handleReservationPostCreate(Reservation reservation) {
		List<Match> matches = conflictiveMatchWithReservationFilter.findConflictiveMatchesWithReservation(reservation);
		if (!matches.isEmpty()) {
			for (Match m : matches) {
				m.setCancelled(true);
				mailer.sendCancellationMailsToPlayers(m);
			}
		}
	}

}
