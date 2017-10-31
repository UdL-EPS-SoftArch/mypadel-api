package cat.udl.eps.softarch.mypadel.domain;

import javax.validation.constraints.NotNull;
import java.util.Random;

public class RandomGenerator {

	@NotNull
	private final Random random=new Random();

	@NotNull
	public MatchResult generateMatchResult() {
		final MatchResult matchResult = new MatchResult();
		matchResult.setDraw(random.nextBoolean());
		matchResult.setVerified(random.nextBoolean());
		return matchResult;
	}

	@NotNull
	public MatchResultVerification generateMatchResultVerification() {
		final MatchResultVerification matchResultVerification = new MatchResultVerification();
		matchResultVerification.setMatchToAgree(generateMatchResult());
		matchResultVerification.setAgrees(random.nextBoolean());
		matchResultVerification.setPlayer(new Player());
		return matchResultVerification;
	}

}
