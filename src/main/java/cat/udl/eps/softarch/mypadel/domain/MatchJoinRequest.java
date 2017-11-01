package cat.udl.eps.softarch.mypadel.domain;

	import com.fasterxml.jackson.annotation.JsonIdentityReference;
	import org.springframework.format.annotation.DateTimeFormat;

	import javax.persistence.*;
	import java.time.ZonedDateTime;

@Entity
	public class MatchJoinRequest extends UriEntity<Long>{
		private Status status=Status.PENDING;
	 	@Id
 		@GeneratedValue(strategy = GenerationType.IDENTITY)
 		private long id;

	 	@ManyToOne
 		@JsonIdentityReference(alwaysAsId = true)
 		private CustomMatch customMatch;

	 	@ManyToOne
		@JsonIdentityReference(alwaysAsId = true)
		private Player player;

		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
		private ZonedDateTime eventDate;

		private String message;

	 	@Override
 		public Long getId() {
				return id;
		}

		public ZonedDateTime getEventDate() {
			return eventDate;
		}
		public void setStatus(Status status){
	 		this.status=status;
		}

		public Status getStatus() {
			return status;
		}

	public String getMessage() { return message;}

		public void setMessage(String message){this.message=message;}

		public Player getPlayer(){return player;}
		public void setPlayer(Player player){this.player=player;}

		public CustomMatch getCustomMatch(){return customMatch;}
		public void setCustomMatch(CustomMatch match){this.customMatch=match;}
}
