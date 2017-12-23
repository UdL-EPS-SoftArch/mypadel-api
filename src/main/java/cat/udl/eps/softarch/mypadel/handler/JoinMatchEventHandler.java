package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.JoinMatch;
import cat.udl.eps.softarch.mypadel.domain.Player;
import cat.udl.eps.softarch.mypadel.handler.exception.JoinMatchException;
import cat.udl.eps.softarch.mypadel.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
@RepositoryEventHandler
public class JoinMatchEventHandler {

	@Autowired
	private JoinMatchChecker joinMatchChecker;

	@HandleAfterCreate
	@Transactional
	public void handlePostCreate(JoinMatch joinMatch) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.systemDefault());
		joinMatch.setEventDate(dateTime);
		joinMatch.setPlayer((Player) auth.getPrincipal());


		if (joinMatchChecker.isMatchFull(joinMatch.getMatch())){
			joinMatchChecker.reserveCourt(joinMatch.getMatch());
		}
	}

	@HandleBeforeCreate
	@Transactional
	public void handleBeforeCreate(JoinMatch joinMatch) throws JoinMatchException {
		if(!joinMatchChecker.isInvited(joinMatch)){
			throw new JoinMatchException("You have not been invited to this match");
		}

		if(joinMatchChecker.isJoinedAtTheSameDatetime(joinMatch)){
			throw new JoinMatchException("You have already joined to a match in the same datetime");
		}

		if(joinMatchChecker.pendingResult(joinMatch)){
			throw new JoinMatchException("You have to verify previous matches");
		}
	}

	@HandleAfterDelete
	@Transactional
	public void handleAfterDelete(JoinMatch joinMatch){
		if (!joinMatchChecker.isMatchFull(joinMatch.getMatch())){
			joinMatchChecker.cancelReservation(joinMatch.getMatch());
		}
	}
}
