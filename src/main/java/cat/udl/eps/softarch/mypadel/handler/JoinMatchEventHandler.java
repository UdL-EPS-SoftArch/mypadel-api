package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.JoinMatch;
import cat.udl.eps.softarch.mypadel.domain.MatchInvitation;
import cat.udl.eps.softarch.mypadel.domain.PrivateMatch;
import cat.udl.eps.softarch.mypadel.handler.exception.MissingInvitationException;
import cat.udl.eps.softarch.mypadel.repository.MatchInvitationRepository;
import cat.udl.eps.softarch.mypadel.repository.MatchRepository;
import cat.udl.eps.softarch.mypadel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

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
		/*if(joinMatch.getMatch() instanceof PrivateMatch) {
			List<MatchInvitation> matchInvitationList = joinMatch.getMatch().getInvitations();
			if(matchInvitationList.size() == 0){
				throw new MissingInvitationException("There is no invitations for this match");
			}
			for (MatchInvitation matchInvitation : matchInvitationList) {
				if(matchInvitation.getInvites().getEmail().equals(joinMatch.getPlayer().getEmail())){
					invite = true;
				}
			}
			if(!invite){
				throw new MissingInvitationException("You have not been invited to this match");
			}
		}*/
	}
}
