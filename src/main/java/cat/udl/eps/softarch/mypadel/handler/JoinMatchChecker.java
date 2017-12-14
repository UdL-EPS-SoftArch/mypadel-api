package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.*;
import cat.udl.eps.softarch.mypadel.handler.exception.JoinMatchException;
import cat.udl.eps.softarch.mypadel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class JoinMatchChecker {
	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private JoinMatchRepository joinMatchRepository;

	@Autowired
	private MatchResultRepository matchResultRepository;

	@Autowired
	private MatchResultVerificationRepository matchResultVerificationRepository;

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
		matchList = matchRepository.findByStartDateBetween(joinMatch.getMatch().getStartDate(),
			joinMatch.getMatch().getStartDate().plusMinutes(joinMatch.getMatch().getDuration().toMinutes()));

		for(Match match : matchList){
			if(Objects.equals(match.getStartDate(), joinMatch.getMatch().getStartDate()) &&
				!Objects.equals(match.getId(), joinMatch.getMatch().getId())){
				return true;
			}
		}
		return false;
	}

	void reserveCourt(Match match){
		Reservation reservation = new Reservation();
		reservation.setCourtType(match.getCourtType());
		reservation.setReservingMatch(match);
		reservation.setStartDate(match.getStartDate());
		reservation.setDuration(match.getDuration());
		match.setReservation(reservation);
		matchRepository.save(match);
		reservationRepository.save(reservation);
	}

	boolean isMatchFull(Match match){
		List<JoinMatch> joinMatchList = joinMatchRepository.findByMatch(match);
		return joinMatchList.size() == 4;
	}

	void cancelReservation(Match match) {
		Reservation reservation = match.getReservation();
		match.setReservation(null);
		reservation.setReservingMatch(null);
		reservationRepository.delete(reservation.getId());
	}

	boolean pendingResult(JoinMatch joinMatch){
		List<JoinMatch> joinMatches;
		List<MatchResult> matchResults;
		List<MatchResultVerification> matchResultVerifications;
		Player player = joinMatch.getPlayer();

		joinMatches = joinMatchRepository.findByPlayer(joinMatch.getPlayer());

		for(JoinMatch j : joinMatches){
			matchResults = matchResultRepository.findByMatch(j.getMatch());
			for(MatchResult matchResult : matchResults){
				matchResultVerifications = matchResultVerificationRepository.findByMatchToAgree(matchResult);
				for(MatchResultVerification matchResultVerification: matchResultVerifications){
					if(!matchResultVerification.isAgrees()){
						return true;
					}
				}
			}
		}
		return false;
	}
}
