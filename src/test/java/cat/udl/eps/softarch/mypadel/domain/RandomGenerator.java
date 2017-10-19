package cat.udl.eps.softarch.mypadel.domain;

import javax.validation.constraints.NotNull;

public class RandomGenerator {

	public MatchResult generateMatchResult() {
		MatchResult matchResult = new MatchResult();
		return matchResult;
	}

	@NotNull
	public MatchResultVerification generateMatchResultVerification() {
		MatchResultVerification matchResultVerification = new MatchResultVerification();
		matchResultVerification.setMatchToAgree(generateMatchResult());
		return matchResultVerification;
	}

}
