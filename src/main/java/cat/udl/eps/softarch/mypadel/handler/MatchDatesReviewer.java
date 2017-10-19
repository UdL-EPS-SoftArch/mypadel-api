package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.Match;
import cat.udl.eps.softarch.mypadel.domain.Player;
import cat.udl.eps.softarch.mypadel.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

class MatchDatesReviewer {

	private MatchRepository matchRepository;

	MatchDatesReviewer(MatchRepository matchRepository){
		this.matchRepository = matchRepository;
	}

	void checkTimers(Match match){
		if(durationIncorrect(match.getDuration())){
			throw new IllegalArgumentException();
		}
	}

	private boolean durationIncorrect(Duration matchDuration) {
		long durationMinutes = matchDuration.toMinutes();

		return durationMinutes > 90 || durationMinutes < 30;
	}

	ZonedDateTime getCancelDeadline(ZonedDateTime startDate) {
		return startDate.minusDays(1);
	}

	void checkCreatorDisp(Match match) {
		Player player = match.getMatchCreator();
		if(hasAnotherMatchOnThatTime(player, match)){
			throw new UnsupportedOperationException();
		}
	}

	private boolean hasAnotherMatchOnThatTime(Player player, Match match) {
		ZonedDateTime startDate = match.getStartDate();
		Duration matchDuration = match.getDuration();
		List<Match> possibleMatches = matchRepository.findByStartDateBetween(startDate,
			startDate.plusMinutes(matchDuration.toMinutes()));
		return !possibleMatches.isEmpty();
	}
}
