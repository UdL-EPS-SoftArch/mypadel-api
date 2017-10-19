package cat.udl.eps.softarch.mypadel.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the result of a match
 */
@Entity
@Table(name = "MatchResult")
public class MatchResult extends UriEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private HashSet<Player> winningPair;

	private HashSet<Player> losingPair;

	private HashSet<MatchResultVerification> verifications;

	private boolean isDraw;

	@OneToOne(fetch = FetchType.LAZY)
	@JsonIdentityReference(alwaysAsId = true)
	private Match match;

	@OneToMany(mappedBy = "matchResultVerification", fetch=FetchType.LAZY)
	@JsonIdentityReference(alwaysAsId = true)
	public Set<MatchResultVerification> getVerifications () {
		return verifications;
	}

	public void setVerifications(HashSet<MatchResultVerification> verifications) {
		this.verifications = verifications;
	}

	@NotNull
	@Override
	public Integer getId() {
		return this.id;
	}

	public Set<Player> getWinningPair() {
		return this.winningPair;
	}

	public void setWinningPair(HashSet<Player> winners) {
		this.winningPair = winners;
	}

	public Set<Player> getLosingPair() {
		return this.losingPair;
	}

	public void setLosingPair(HashSet<Player> losers) {
		this.losingPair = losers;
	}

	public boolean isDraw() {
		return isDraw;
	}

	public void setDraw(boolean draw) {
		isDraw = draw;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
}
