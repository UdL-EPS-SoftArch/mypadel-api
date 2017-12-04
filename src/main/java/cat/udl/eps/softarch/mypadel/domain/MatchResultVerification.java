package cat.udl.eps.softarch.mypadel.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Represents the verification of a match result
 */
@Entity
@Table(name = "MatchResultVerification")
public class MatchResultVerification extends UriEntity<Integer> {

	//region Variables

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private int id;

	@NotNull
	private boolean agrees;

	@JsonIdentityReference(alwaysAsId = true)
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	private MatchResult matchToAgree;

	@JsonIdentityReference(alwaysAsId = true)
	@NotNull
	@OneToOne(fetch = FetchType.EAGER)
	private Player player;

	//endregion

	//region Get/Set

	@NotNull
	@Override
	public Integer getId() {
		return this.id;
	}

	@NotNull
	public boolean isAgrees() {
		return agrees;
	}

	public void setAgrees(@NotNull boolean agrees) {
		this.agrees = agrees;
	}

	@NotNull
	public MatchResult getMatchToAgree() {
		return matchToAgree;
	}

	public void setMatchToAgree(@NotNull MatchResult matchToAgree) {
		this.matchToAgree = matchToAgree;
	}

	@NotNull
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(@NotNull Player player) {
		this.player = player;
	}

	@Override
	public boolean equals(/* TODO Nullable */Object object) {
		if (this == object) return true;
		if (!(object instanceof MatchResultVerification)) return false;
		final MatchResultVerification that = (MatchResultVerification) object;

		return id == that.id && agrees == that.agrees
			&& matchToAgree.equals(that.matchToAgree)
			&& player.equals(that.player);
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (agrees ? 1 : 0);
		result = 31 * result + matchToAgree.hashCode();
		result = 31 * result + player.hashCode();
		return result;
	}

	//endregion

}
