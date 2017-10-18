package cat.udl.eps.softarch.mypadel.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Table(name = "MatchResultVerification")
public class MatchResultVerification {

	private  boolean agrees;

	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private MatchResult matchToAgree;

	public boolean isAgrees() {
		return agrees;
	}

	public void setAgrees(boolean agrees) {
		this.agrees = agrees;
	}

	public MatchResult getMatchToAgree() {
		return matchToAgree;
	}

	public void setMatchToAgree(MatchResult matchToAgree) {
		this.matchToAgree = matchToAgree;
	}

}
