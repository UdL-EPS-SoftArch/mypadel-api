package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.Match;

import java.time.Duration;
import java.time.ZonedDateTime;

class MatchDatesReviewer {

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
}
