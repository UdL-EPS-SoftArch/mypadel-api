package cat.udl.eps.softarch.mypadel.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

class CustomMatch extends Match {

	@OneToMany(mappedBy = "customMatch", fetch = FetchType.EAGER)
	@JsonIdentityReference(alwaysAsId = true)
	private List<MatchJoinRequest> matchJoinRequests = new ArrayList<>();

}
