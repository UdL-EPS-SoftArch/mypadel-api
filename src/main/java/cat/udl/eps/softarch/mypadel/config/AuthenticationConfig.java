package cat.udl.eps.softarch.mypadel.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.authentication.configurers.*;
import org.springframework.security.crypto.bcrypt.*;

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
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user")
                    .password("$2a$04$Fh70rZF0oXCW3XA2uY83aOXi5mCMzaGfgmceRSXKNnL/CZwfGuisy")
                    .roles("PLAYER").and()
                .withUser("admin")
                    .password("$2a$10$B1dcscvS/lgiBnGdkhhupew8AhbjqUL7TjdA2ggvxQhs5jN7KVSMC")
                .roles("ADMIN").and()
                .withUser("nopolfavol")
                .password("$2a$10$B1dcscvS/lgiBnGdkhhupew8AhbjqUL7TjdA2ggvxQhs5jN7KVSMC")
                .roles("PLAYER");
    }
}