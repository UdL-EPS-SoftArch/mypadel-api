package cat.udl.eps.softarch.mypadel.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

/**
 * Represents the result of a match
 */
@Entity
@Table(name = "MatchResult")
public class MatchResult extends UriEntity<Integer> {

	//TODO Add Players and NotNull Checkings
	//TODO Talk about if having Lists as winning or losing its the best way (also the isDraw property)

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private ArrayList<Player> winningPair;

	private ArrayList<Player> losingPair;

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

	@Override
	public Integer getId() {
		return this.id;
	}

	public ArrayList<Player> getWinningPair () { return this.winningPair; }

	public void setWinningPair (ArrayList<Player> winners) { this.winningPair = winners; }

	public ArrayList<Player> getLosingPair () { return this.losingPair; }

	public void setLosingPair (ArrayList<Player> losers) { this.losingPair = losers; }

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

	public void setVerifications(Set<MatchResultVerification> verifications) {
		this.verifications = verifications;
	}
}
