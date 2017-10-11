package cat.udl.eps.softarch.mypadel.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
public class JoinMatch extends UriEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime eventDate;

	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
    private Player player;

	@ManyToOne
	@JsonIdentityReference(alwaysAsId = true)
    private Match match;

    @Override
    public Long getId() { return id; }

    public ZonedDateTime getDate() { return eventDate; }

    public void setEventDate(ZonedDateTime date) {
    	this.eventDate = date;
    }

    public void setPlayer(Player p){
    	this.player = p;
	}

	public Player getPlayer(){
    	return this.player;
	}

	public void setMatch(Match m){
		this.match = match;
	}

	public Match getMatch(){
		return this.match;
	}

}
