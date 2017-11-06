package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.JoinMatch;
import cat.udl.eps.softarch.mypadel.handler.exception.MissingInvitationException;
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
	private PrivateInvitationChecker invitationChecker;

	@HandleAfterCreate
	@Transactional
	public void handleAdminPostCreate(JoinMatch joinMatch) {
		ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.systemDefault());
		joinMatch.setEventDate(dateTime);
	}

	@HandleBeforeCreate
	@Transactional
	public void handleBeforeSave(JoinMatch joinMatch) throws MissingInvitationException {
		if(!invitationChecker.isInvited(joinMatch)){
			throw new MissingInvitationException("You have not been invited to this match");
		}
	}
}
