package cat.udl.eps.softarch.mypadel.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class MatchInvitation {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private ZonedDateTime eventDate;
	private String message;




	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private User createdBy;

	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private  Player invites;

	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
	private	Match invitesTo;







	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Player getInvites() {
		return invites;
	}

	public void setInvites(Player invites) {
		this.invites = invites;
	}

	public Match getInvitesTo() {
		return invitesTo;
	}

	public void setInvitesTo(Match invitesTo) {
		this.invitesTo = invitesTo;
	}

	//methods
	public void setId(Long id) {
		this.id = id;
	}

	public void setEventDate(ZonedDateTime eventDate) {
		this.eventDate = eventDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
