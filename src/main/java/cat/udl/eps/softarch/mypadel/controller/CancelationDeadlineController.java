package cat.udl.eps.softarch.mypadel.controller;

import cat.udl.eps.softarch.mypadel.domain.JoinMatch;
import cat.udl.eps.softarch.mypadel.domain.Match;
import cat.udl.eps.softarch.mypadel.repository.JoinMatchRepository;
import cat.udl.eps.softarch.mypadel.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Component
public class CancelationDeadlineController {

	private static int REVIEW_TIME = 30;

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private JoinMatchRepository joinMatchRepository;

	//This method will be called every 30 minutes after the last completion of this method.
	@Scheduled(fixedDelay = 1800000)
	public void searchReachedDeadlines() {
		List<Match> matchesToReview = getPossibleCancelledMatches();
		reviewMatches(matchesToReview);
	}

	private List<Match> getPossibleCancelledMatches() {
		return matchRepository.findByCancelationDeadlineBetween(
			ZonedDateTime.now().minusMinutes(REVIEW_TIME), ZonedDateTime.now());
	}

	private void reviewMatches(List<Match> matchesToReview) {
		for (Match reviewedMatch : matchesToReview) {
			List<JoinMatch> joinMatches = joinMatchRepository.findByMatch(reviewedMatch);
			if (joinMatches.size() != 4){
//				cancelMatch();
//				sendMail();
			}

		}
	}
}
