package cat.udl.eps.softarch.mypadel.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MyPadelUser") //Avoid collision with system table User in Postgres
public abstract class User extends UriEntity<String> implements UserDetails {

	public static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Id
    private String username;

    @Email
    private String email;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;





	@OneToMany       (mappedBy = "createdBy")// when I use One to many(List) for better performance
	//private MatchInvitation creatorOf;
	@JsonIdentityReference(alwaysAsId = true)
	private List<MatchInvitation> creatorOf = new ArrayList<>();



	public List<MatchInvitation> getCreatorOf() {
		return creatorOf;
	}

	public void setCreatorOf(List<MatchInvitation> creatorOf) {
		this.creatorOf = creatorOf;
	}






	@Override
    public String getId() {
        return username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
		this.password = passwordEncoder.encode(password);
    }

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
