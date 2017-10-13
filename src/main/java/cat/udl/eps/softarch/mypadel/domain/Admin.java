package cat.udl.eps.softarch.mypadel.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Collection;

@Entity
public class Admin extends User {
	@Override
	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
	}

}
