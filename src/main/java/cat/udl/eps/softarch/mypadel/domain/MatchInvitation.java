package cat.udl.eps.softarch.mypadel.domain;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.List;

@Entity
public class MatchInvitation {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private ZonedDateTime eventDate;
	private String message;

	@OneToMany
	private User createdBy;

	@OneToMany
	private  Player invites;

	@OneToMany
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
