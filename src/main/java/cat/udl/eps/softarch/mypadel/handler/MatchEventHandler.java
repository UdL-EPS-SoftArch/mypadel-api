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
import java.time.Duration;
import java.time.ZonedDateTime;

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
		checkTimers(match);
	}

	private boolean adminInputsInvalidPlayer(Match match, Authentication auth) {
		return auth.getPrincipal() instanceof Admin && match.getMatchCreator() == null;
	}

	public void checkTimers(Match match){
		if(cancelationDateIncorrect(match.getStartDate(), match.getCancelationDeadline())
																			|| durationIncorrect(match.getDuration())){
			throw new IllegalArgumentException();
		}
	}

	private boolean durationIncorrect(Duration matchDuration) {
		String duration = matchDuration.toString();
		int durationMinutes = Integer.parseInt(duration);

		return durationMinutes > 90 || durationMinutes < 30;
	}

	private boolean cancelationDateIncorrect(ZonedDateTime matchStartDate, ZonedDateTime matchCancelDate) {
		String startDate = matchStartDate.toString();
		String cancelationDate = matchCancelDate.toString();

		String[] parsedStartDate = startDate.split("-");
		String[] parsedCancelDate = cancelationDate.split("-");

		return !cancelationDateHasADayLess(parsedStartDate, parsedCancelDate);

	}

	private boolean cancelationDateHasADayLess(String[] parsedStartDate, String[] parsedCancelDate) {
		String[] startDay = parsedStartDate[2].split("T");
		String[] cancelDay = parsedCancelDate[2].split("T");

		return parsedStartDate[0].equals(parsedCancelDate[0]) && (parsedStartDate[1].equals(parsedCancelDate[1])
			|| Integer.parseInt(parsedCancelDate[1]) == Integer.parseInt(parsedStartDate[1])-1)
			&& (Integer.parseInt(startDay[0])-1 == Integer.parseInt(cancelDay[0])
			|| lastDayOfMonth(Integer.parseInt(parsedStartDate[1])-1) == Integer.parseInt(cancelDay[0]))
			&& startDay[1].equals(cancelDay[1]);
	}

	/**
	 *
	 * @param month
	 * @return
	 */
	private int lastDayOfMonth(int month) {
		if(month == 2){
			return 28;
		}else if(month < 8 ){

		}
	}
}
