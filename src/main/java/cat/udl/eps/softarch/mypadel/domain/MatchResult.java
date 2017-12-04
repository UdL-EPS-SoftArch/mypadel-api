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

	@OneToOne(fetch = FetchType.EAGER)
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

	@Override
	public boolean equals(/* TODO Nullable */ Object object) {
		if (this == object) return true;
		if (!(object instanceof MatchResult)) return false;

		final MatchResult that = (MatchResult) object;

		return isDraw == that.isDraw
			&& isVerified == that.isVerified
			&& (id != null ? id.equals(that.id) : that.id == null)
			&& (match != null ? match.equals(that.match) : that.match == null)
			&& (winningPair != null ? winningPair.equals(that.winningPair) : that.winningPair == null)
			&& (losingPair != null ? losingPair.equals(that.losingPair) : that.losingPair == null)
			&& (verifications != null ? verifications.equals(that.verifications) : that.verifications == null);
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (match != null ? match.hashCode() : 0);
		result = 31 * result + (winningPair != null ? winningPair.hashCode() : 0);
		result = 31 * result + (losingPair != null ? losingPair.hashCode() : 0);
		result = 31 * result + (verifications != null ? verifications.hashCode() : 0);
		result = 31 * result + (isDraw ? 1 : 0);
		result = 31 * result + (isVerified ? 1 : 0);
		return result;
	}
}
