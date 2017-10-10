package cat.udl.eps.softarch.mypadel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.util.Collection;

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

    @ManyToOne
	private MatchInvitation invitations;

	public MatchInvitation getInvitations() {
		return invitations;
	}

	public void setInvitations(MatchInvitation invitations) {
		this.invitations = invitations;
	}

	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_PLAYER");
	}
}
