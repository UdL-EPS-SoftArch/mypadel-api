package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.Court;
import cat.udl.eps.softarch.mypadel.domain.Reservation;
import cat.udl.eps.softarch.mypadel.repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RepositoryEventHandler
public class ReservationEventHandler {
	@Autowired
	private CourtRepository courtRepository;

	@HandleBeforeCreate
	@Transactional
	public void handleReservationPreCreate(Reservation reservation){
		Court court = courtRepository.findByAvailableTrue();
		reservation.setCourt(court);
	}
}
