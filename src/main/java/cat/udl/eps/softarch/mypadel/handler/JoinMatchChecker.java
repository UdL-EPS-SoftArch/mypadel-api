package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.JoinMatch;
import cat.udl.eps.softarch.mypadel.domain.Match;
import cat.udl.eps.softarch.mypadel.domain.MatchInvitation;
import cat.udl.eps.softarch.mypadel.domain.PrivateMatch;
import cat.udl.eps.softarch.mypadel.handler.exception.JoinMatchException;
import cat.udl.eps.softarch.mypadel.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class JoinMatchChecker {
	private MatchRepository matchRepository;
	private List<Match> matchList;

	boolean isInvited(JoinMatch joinMatch) throws JoinMatchException {
		if(joinMatch.getMatch() instanceof PrivateMatch) {
			List<MatchInvitation> matchInvitationList = joinMatch.getMatch().getInvitations();
			if(matchInvitationList.size() == 0){
				throw new JoinMatchException("There is no invitations for this match");
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

	boolean isJoinedAtTheSameDatetime(JoinMatch joinMatch) throws JoinMatchException{
		matchList = matchRepository.findByStartDateBetween(joinMatch.getMatch().getStartDate(), joinMatch.getMatch().getStartDate());

		for(Match match : matchList){
			if(Objects.equals(match.getStartDate(), joinMatch.getMatch().getStartDate()) && !Objects.equals(match.getId(), joinMatch.getMatch().getId())){
				return true;
			}
		}
		return false;
	}
}
