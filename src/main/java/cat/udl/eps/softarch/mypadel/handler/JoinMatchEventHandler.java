package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.JoinMatch;
import cat.udl.eps.softarch.mypadel.handler.exception.JoinMatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
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
	public void handleAdminPostCreate(JoinMatch joinMatch) {
		ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.systemDefault());
		joinMatch.setEventDate(dateTime);

		if (joinMatchChecker.isMatchFull(joinMatch.getMatch())){
			joinMatchChecker.reserveCourt(joinMatch.getMatch());
		}
	}

	@HandleBeforeCreate
	@Transactional
	public void handleBeforeSave(JoinMatch joinMatch) throws JoinMatchException {
		if(!joinMatchChecker.isInvited(joinMatch)){
			throw new JoinMatchException("You have not been invited to this match");
		}

		if(joinMatchChecker.isJoinedAtTheSameDatetime(joinMatch)){
			throw new JoinMatchException("You have already joined to a match in the same datetime");
		}

	}
}
