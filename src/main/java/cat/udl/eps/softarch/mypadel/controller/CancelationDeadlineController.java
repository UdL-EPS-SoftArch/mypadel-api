package cat.udl.eps.softarch.mypadel.controller;

import cat.udl.eps.softarch.mypadel.domain.Match;
import cat.udl.eps.softarch.mypadel.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
public class CancelationDeadlineController {

	@Autowired
	private MatchRepository matchRepository;

	//This method will be called every 30 minutes after the last completion of this method.
	@Scheduled(fixedDelay = 1800000)
	public void searchReachedDeadlines() {
		List<Match> matchesToReview = matchRepository.findByCancelationDeadlineBetween(
			ZonedDateTime.now().minusMinutes(30), ZonedDateTime.now());

		//get the players on the match
		//not 4 joined
		//cancel match
		//send mail
	}
}
