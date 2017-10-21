package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.Admin;
import cat.udl.eps.softarch.mypadel.domain.JoinMatch;
import cat.udl.eps.softarch.mypadel.domain.Match;
import cat.udl.eps.softarch.mypadel.domain.Player;
import cat.udl.eps.softarch.mypadel.handler.exception.CreateMatchException;
import cat.udl.eps.softarch.mypadel.repository.JoinMatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RepositoryEventHandler
public class MatchEventHandler {
	final Logger logger = LoggerFactory.getLogger(Match.class);

	@Autowired
	private MatchDatesReviewer mdr;

	@Autowired
	private JoinMatchRepository joinMatchRepository;

	@HandleBeforeCreate
	@Transactional
	public void handleMatchPreCreate(Match match) throws CreateMatchException {
		handleMatchCreator(match);
		mdr.checkTimers(match);
		mdr.checkCreatorDisponibility(match);
		match.setCancelationDeadline(mdr.getCancelDeadline(match.getStartDate()));
	}

	private void handleMatchCreator(Match match) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(adminInputsInvalidPlayer(match, auth)){
			throw new NullPointerException();
		}else if(auth.getPrincipal() instanceof Player){
			match.setMatchCreator((Player) auth.getPrincipal());
		}
	}

	private boolean adminInputsInvalidPlayer(Match match, Authentication auth) {
		return auth.getPrincipal() instanceof Admin && match.getMatchCreator() == null;
	}

	@HandleAfterCreate
	@Transactional
	public void handleMatchPostCreate(Match match){
		JoinMatch joinMatch = new JoinMatch();
		joinMatch.setEventDate(match.getStartDate());
		joinMatch.setPlayer(match.getMatchCreator());
		joinMatch.setMatch(match);
		joinMatchRepository.save(joinMatch);
	}
}
