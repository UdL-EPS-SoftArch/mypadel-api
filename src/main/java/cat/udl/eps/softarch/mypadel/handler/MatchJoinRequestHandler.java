package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.Admin;
import cat.udl.eps.softarch.mypadel.domain.MatchJoinRequest;
import cat.udl.eps.softarch.mypadel.domain.Player;
import cat.udl.eps.softarch.mypadel.repository.AdminRepository;
import cat.udl.eps.softarch.mypadel.repository.PlayerRepository;
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
public class MatchJoinRequestHandler {
	final Logger logger = LoggerFactory.getLogger(MatchJoinRequest.class);
	@Autowired
	AdminRepository adminRepository;

	@Autowired
	PlayerRepository playerRepository;

	@HandleBeforeCreate
	@Transactional
	public void handleMatchJoinRequestPreCreate(MatchJoinRequest matchJoinRequest){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(adminInputsInvalidPlayer(matchJoinRequest, auth)){
			throw new NullPointerException();
		}else if(auth.getPrincipal() instanceof Player){
			matchJoinRequest.setPlayer((Player) auth.getPrincipal());
		}
	}
	private boolean adminInputsInvalidPlayer(MatchJoinRequest matchJoinRequest, Authentication auth) {
		return auth.getPrincipal() instanceof Admin && matchJoinRequest.getPlayer() == null;
	}
}
