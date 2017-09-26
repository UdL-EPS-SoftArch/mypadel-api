package cat.udl.eps.softarch.mypadel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by http://rhizomik.net/~roberto/
 */
@Configuration
@Profile("!Test")
public class AuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("player")
                .password("playerpassword")
                .roles("PLAYER").and()
                .withUser("admin")
                .password("adminpassword")
                .roles("ADMIN");
    }
}