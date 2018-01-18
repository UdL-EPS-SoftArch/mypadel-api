package cat.udl.eps.softarch.mypadel.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;

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

	@JsonIdentityReference(alwaysAsId = true)
	@OneToOne(fetch = FetchType.EAGER)
	private Match match;

	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIdentityReference(alwaysAsId = true)
	@OneToMany(fetch = FetchType.EAGER)
	private List<Player> winningPair;

	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIdentityReference(alwaysAsId = true)
	@OneToMany(fetch = FetchType.EAGER)
	private List<Player> losingPair;

	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIdentityReference(alwaysAsId = true)
	@OneToMany(fetch = FetchType.EAGER)
	private List<MatchResultVerification> verifications;

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

	public List<Player> getWinningPair() {
		return winningPair;
	}

	public void setWinningPair(List<Player> winningPair) {
		this.winningPair = winningPair;
	}

	public List<Player> getLosingPair() {
		return losingPair;
	}

	public void setLosingPair(List<Player> losingPair) {
		this.losingPair = losingPair;
	}

	public List<MatchResultVerification> getVerifications() {
		return verifications;
	}

	public void setVerifications(List<MatchResultVerification> verifications) {
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
