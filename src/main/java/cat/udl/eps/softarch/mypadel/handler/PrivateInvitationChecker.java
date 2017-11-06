package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.JoinMatch;
import cat.udl.eps.softarch.mypadel.domain.MatchInvitation;
import cat.udl.eps.softarch.mypadel.domain.PrivateMatch;
import cat.udl.eps.softarch.mypadel.handler.exception.MissingInvitationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivateInvitationChecker {

	public boolean isInvited(JoinMatch joinMatch) throws MissingInvitationException{
		if(joinMatch.getMatch() instanceof PrivateMatch) {
			List<MatchInvitation> matchInvitationList = joinMatch.getMatch().getInvitations();
			if(matchInvitationList.size() == 0){
				throw new MissingInvitationException("There is no invitations for this match");
			}
			for (MatchInvitation matchInvitation : matchInvitationList) {
				if(matchInvitation.getInvites().getEmail().equals(joinMatch.getPlayer().getEmail())){
					return true;
				}
			}
		}else {
			return true;
		}
		return false;
	}
}
