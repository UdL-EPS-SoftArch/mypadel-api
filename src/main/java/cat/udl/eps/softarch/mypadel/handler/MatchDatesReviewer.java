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
		String duration = matchDuration.toString().split("T")[1].split("M")[0];
		int durationMinutes = Integer.parseInt(duration);

		return durationMinutes > 90 || durationMinutes < 30;
	}

	ZonedDateTime getCancelDeadline(ZonedDateTime startDate) {
		return startDate.minusDays(1);
	}
}
