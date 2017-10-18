package cat.udl.eps.softarch.mypadel.handler;

import cat.udl.eps.softarch.mypadel.domain.Match;

import java.time.Duration;
import java.time.ZonedDateTime;

class MatchDatesReviewer {

	void checkTimers(Match match){
		if(cancelationDateIncorrect(match.getStartDate(), match.getCancelationDeadline())
			|| durationIncorrect(match.getDuration())){
			throw new IllegalArgumentException();
		}
	}

	private boolean durationIncorrect(Duration matchDuration) {
		String duration = matchDuration.toString().split("T")[1].split("M")[0];
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

	int lastDayOfMonth(int month) {
		if(month == 2){
			return 28;
		}
		return (month < 8 && month % 2 == 0)||(month > 7 && month % 2 == 1) ? 30 : 31;
	}
}
