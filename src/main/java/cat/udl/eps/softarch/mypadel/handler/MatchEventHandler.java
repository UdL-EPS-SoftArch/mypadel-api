package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.Admin;
import cat.udl.eps.softarch.mypadel.domain.Match;
import cat.udl.eps.softarch.mypadel.domain.Player;
import cat.udl.eps.softarch.mypadel.repository.AdminRepository;
import cat.udl.eps.softarch.mypadel.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RepositoryEventHandler
public class MatchEventHandler {
	final Logger logger = LoggerFactory.getLogger(Match.class);

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	PlayerRepository playerRepository;

	@HandleBeforeCreate
	@Transactional
	public void handleMatchCreatorPreCreate(Match match){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(adminInputsInvalidPlayer(match, auth)){
			throw new NullPointerException();
		}else if(auth.getPrincipal() instanceof Player){
			match.setMatchCreator((Player) auth.getPrincipal());
		}
	}

	@HandleBeforeCreate
	@Transactional
	public void handleMatchPropertiesPreCreate(Match match){
		if(cancelationDateOrDurationIncorrects(match)){
			throw new IllegalArgumentException();
		}
	}

	private boolean cancelationDateOrDurationIncorrects(Match match) {
		String startDate = match.getStartDate().toString();
		String cancelationDate = match.getCancelationDeadline().toString();
		String duration = match.getDuration().toString();

		if(Integer.parseInt(duration) > 90){

		}

		return false;
	}

	private boolean adminInputsInvalidPlayer(Match match, Authentication auth) {
		return auth.getPrincipal() instanceof Admin && match.getMatchCreator() == null;
	}
}
