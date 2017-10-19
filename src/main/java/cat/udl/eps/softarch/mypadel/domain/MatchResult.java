package cat.udl.eps.softarch.mypadel.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;

/**
 * Represents the result of a match
 */
@Entity
@Table(name = "MatchResult")
public class MatchResult extends UriEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JsonIdentityReference(alwaysAsId = true)
	private Match match;

	@JsonIdentityReference(alwaysAsId = true)
	private HashSet<Player> winningPair;

	@JsonIdentityReference(alwaysAsId = true)
	private HashSet<Player> losingPair;

	@JsonIdentityReference(alwaysAsId = true)
	private HashSet<MatchResultVerification> verifications;

	private boolean isDraw;

	private boolean isVerified;

	@NotNull
	@Override
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public HashSet<Player> getWinningPair() {
		return winningPair;
	}

	public void setWinningPair(HashSet<Player> winningPair) {
		this.winningPair = winningPair;
	}

	public HashSet<Player> getLosingPair() {
		return losingPair;
	}

	public void setLosingPair(HashSet<Player> losingPair) {
		this.losingPair = losingPair;
	}

	public HashSet<MatchResultVerification> getVerifications() {
		return verifications;
	}

	public void setVerifications(HashSet<MatchResultVerification> verifications) {
		this.verifications = verifications;
	}

	public boolean isDraw() {
		return isDraw;
	}

	public void setDraw(boolean draw) {
		isDraw = draw;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean verified) {
		isVerified = verified;
	}

}
