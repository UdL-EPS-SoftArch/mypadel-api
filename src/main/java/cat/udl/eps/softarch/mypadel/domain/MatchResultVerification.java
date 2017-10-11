package cat.udl.eps.softarch.mypadel.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.ManyToOne;

/**
 *
 */
public class MatchResultVerification {

	private  boolean agrees;

	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private MatchResult matchToAgree;

	public void verify () {
	}
}
