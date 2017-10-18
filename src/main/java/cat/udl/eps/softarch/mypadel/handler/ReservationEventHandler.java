package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.Court;
import cat.udl.eps.softarch.mypadel.domain.CourtType;
import cat.udl.eps.softarch.mypadel.domain.Reservation;
import cat.udl.eps.softarch.mypadel.handler.exception.CourtNotFoundException;
import cat.udl.eps.softarch.mypadel.repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Component
@RepositoryEventHandler
public class ReservationEventHandler {

	@Autowired
	private CourtRepository courtRepository;

	@HandleBeforeCreate
	@Transactional
	public void handleReservationPreCreate(Reservation reservation) {
		List<Court> courts = courtRepository.findByAvailableTrue();
		List<Court> notReserved = courts.parallelStream()
			.filter(court -> court.getReservations().isEmpty())
			.filter(court -> {
				CourtType courtType = court.isIndoor() ? CourtType.INDOOR : reservation.getCourtType();
				return Objects.equals(courtType, reservation.getCourtType());
			}).collect(toList());
		if(!notReserved.isEmpty()){
			Court court = notReserved.get(0);
			reservation.setCourt(court);
		}
		else
			throw new CourtNotFoundException("Reservation aborted, no courts available at this moment.");
	}
}
