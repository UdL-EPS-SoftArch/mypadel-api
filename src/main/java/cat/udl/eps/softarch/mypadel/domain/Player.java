package cat.udl.eps.softarch.mypadel.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Player extends User {
    public enum Level{NOVICE,BEGINNER,INTERMEDIATE,ADVANCED,BRUTAL}
    @JsonIgnore
    private int score;
    private Level level;
    public Level getLevel() {
        return level;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setLevel(Level level) {
        this.level = level;
    }

	@OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
	@JsonIdentityReference(alwaysAsId = true)
	private List<MatchJoinRequest> matchJoinRequests = new ArrayList<>();




    @OneToMany (mappedBy = "invites")
	@JsonIdentityReference(alwaysAsId = true)
	private List<MatchInvitation> invitations = new ArrayList<>();

	public List<MatchInvitation> getInvitations() {
		return invitations;
	}

	public void setInvitations(List<MatchInvitation> invitations) {
		this.invitations = invitations;
	}






	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_PLAYER");
	}
}
