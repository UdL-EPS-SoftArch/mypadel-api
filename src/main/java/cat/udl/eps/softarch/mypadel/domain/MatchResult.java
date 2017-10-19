package cat.udl.eps.softarch.mypadel.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

	private Set<Player> winningPair;

	private Set<Player> losingPair;

	private Set<MatchResultVerification> verifications;

	private boolean isDraw;

	@OneToOne(mappedBy = "match", fetch=FetchType.LAZY)
	@JsonIdentityReference(alwaysAsId = true)
	private Match match;

	@OneToMany(mappedBy = "matchResultVerification", fetch=FetchType.LAZY)
	@JsonIdentityReference(alwaysAsId = true)
	public Set<MatchResultVerification> getVerifications () {
		return verifications;
	}

	public void setVerifications(Set<MatchResultVerification> verifications) {
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

	public void setWinningPair(Set<Player> winners) {
		this.winningPair = winners;
	}

	public Set<Player> getLosingPair() {
		return this.losingPair;
	}

	public void setLosingPair(Set<Player> losers) {
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
