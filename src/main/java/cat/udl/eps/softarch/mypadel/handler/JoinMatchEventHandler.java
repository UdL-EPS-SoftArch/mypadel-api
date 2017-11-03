package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.*;
import cat.udl.eps.softarch.mypadel.handler.exception.MissingInvitationException;
import cat.udl.eps.softarch.mypadel.repository.MatchInvitationRepository;
import cat.udl.eps.softarch.mypadel.repository.MatchRepository;
import cat.udl.eps.softarch.mypadel.repository.PlayerRepository;
import cat.udl.eps.softarch.mypadel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@Component
@RepositoryEventHandler
public class JoinMatchEventHandler {

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private MatchInvitationRepository matchInvitation;

	@Autowired
	private UserRepository playerRepository;

	@Autowired
	private MatchInvitationRepository matchInvitationRepository;

	protected MockMvc mockMvc;


	@HandleAfterCreate
	@Transactional
	public void handleAdminPostCreate(JoinMatch joinMatch) {
		ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.systemDefault());
		joinMatch.setEventDate(dateTime);
	}

	/*@HandleBeforeCreate
	@Transactional
	public void handleBeforeSave(JoinMatch joinMatch) throws MissingInvitationException {
		List<MatchInvitation> matchInvitationList = joinMatch.getMatch().getInvitations();
		boolean invite = false;
		if(joinMatch.getMatch() instanceof PrivateMatch && joinMatch.getMatch() != null) {
			//throw new MissingInvitationException("Hola");
			for (MatchInvitation matchInvitation : matchInvitationList) {
				if(matchInvitation.getInvites().getEmail().equals(joinMatch.getPlayer().getEmail())){
					invite = true;
				}
			}
			if(!invite){
				throw new MissingInvitationException("NAS");
			}
		}
	}*/
}
