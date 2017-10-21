package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.Match;
import cat.udl.eps.softarch.mypadel.domain.Player;
import cat.udl.eps.softarch.mypadel.handler.exception.CreateMatchException;
import cat.udl.eps.softarch.mypadel.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

@Service
class MatchDatesReviewer {

	@Autowired
	private MatchRepository matchRepository;

	void checkTimers(Match match) throws CreateMatchException {
		if(durationIncorrect(match.getDuration())){
			throw new CreateMatchException("The duration must be between 30 to 90 minutes");
		}
	}

	private boolean durationIncorrect(Duration matchDuration) {
		long durationMinutes = matchDuration.toMinutes();

		return durationMinutes > 90 || durationMinutes < 30;
	}

	ZonedDateTime getCancelDeadline(ZonedDateTime startDate) {
		return startDate.minusDays(1);
	}

	void checkCreatorDisponibility(Match match) throws CreateMatchException {
		Player player = match.getMatchCreator();
		if(hasAnotherMatchOnThatTime(player, match)){
			throw new CreateMatchException("The player has a match created on that time");
		}
	}

	private boolean hasAnotherMatchOnThatTime(Player player, Match match) {
		ZonedDateTime startDate = match.getStartDate();
		Duration matchDuration = match.getDuration();
		List<Match> possibleMatches = matchRepository.findByStartDateBetween(startDate,
			startDate.plusMinutes(matchDuration.toMinutes()));

		return !possibleMatches.isEmpty() && playerHasMatchCreated(possibleMatches, player);
	}

	private boolean playerHasMatchCreated(List<Match> possibleMatches, Player player) {
		for(Match match : possibleMatches){
			Player matchCreator = match.getMatchCreator();
			if(matchCreator.equals(player)){
				return true;
			}
		}
		return false;
	}
}
