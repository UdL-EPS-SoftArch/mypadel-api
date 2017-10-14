package cat.udl.eps.softarch.mypadel.domain;

	import com.fasterxml.jackson.annotation.JsonIdentityReference;

	import javax.persistence.*;

	public class MatchJoinRequest extends UriEntity<Long>{

	 	@Id
 		@GeneratedValue(strategy = GenerationType.IDENTITY)
 		private long id;

	 	@ManyToOne
 		@JsonIdentityReference(alwaysAsId = true)
 		private CustomMatch customMatch;

	 	@Override
 		public Long getId() {
				return id;
		}
 }
