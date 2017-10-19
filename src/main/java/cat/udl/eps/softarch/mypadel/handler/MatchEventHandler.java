package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.Admin;
import cat.udl.eps.softarch.mypadel.domain.Match;
import cat.udl.eps.softarch.mypadel.domain.Player;
import cat.udl.eps.softarch.mypadel.handler.exception.CreateMatchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	@HandleBeforeCreate
	@Transactional
	public void handleMatchCreatorPreCreate(Match match) throws CreateMatchException {
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
}
