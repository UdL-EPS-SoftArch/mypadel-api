package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.JoinMatch;
import cat.udl.eps.softarch.mypadel.domain.Match;
import cat.udl.eps.softarch.mypadel.handler.exception.CreateMatchException;
import cat.udl.eps.softarch.mypadel.repository.JoinMatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class MatchEventHandler {
	final Logger logger = LoggerFactory.getLogger(Match.class);

	@Autowired
	private MatchChecker matchChecker;

	@Autowired
	private JoinMatchRepository joinMatchRepository;

	@HandleBeforeCreate
	@Transactional
	public void handleMatchPreCreate(Match match) throws CreateMatchException {
		matchChecker.handleMatchCreator(match);
		matchChecker.checkTimers(match);
		matchChecker.checkCreatorDisponibility(match);
		match.setCancelationDeadline(match.getStartDate().minusDays(1));
	}

	@HandleAfterCreate
	@Transactional
	public void createJoinMatch(Match match) {
		JoinMatch joinMatch = new JoinMatch();
		joinMatch.setPlayer(match.getMatchCreator());
		ZonedDateTime eventDate = ZonedDateTime.now(ZoneId.systemDefault());
		joinMatch.setEventDate(eventDate);
		joinMatch.setMatch(match);
		joinMatchRepository.save(joinMatch);
	}
}
