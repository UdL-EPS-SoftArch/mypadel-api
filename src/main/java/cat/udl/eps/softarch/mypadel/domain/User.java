package cat.udl.eps.softarch.mypadel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Table(name = "MyPadelUser") //Avoid collision with system table User in Postgres
public abstract class User extends UriEntity<String> {

    @Id
    private String username;

    @Email
    private String email;

    @JsonIgnore
    private String password;


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
        this.password = password;
    }
}
