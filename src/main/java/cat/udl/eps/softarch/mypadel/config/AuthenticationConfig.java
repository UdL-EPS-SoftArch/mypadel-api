package cat.udl.eps.softarch.mypadel.config;

import cat.udl.eps.softarch.mypadel.domain.Admin;
import cat.udl.eps.softarch.mypadel.domain.Player;
import cat.udl.eps.softarch.mypadel.domain.User;
import cat.udl.eps.softarch.mypadel.repository.AdminRepository;
import cat.udl.eps.softarch.mypadel.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by http://rhizomik.net/~roberto/
 */
@Configuration
@Profile("!Test")
public class AuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	BasicUserDetailsService basicUserDetailsService;

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	PlayerRepository playerRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(basicUserDetailsService)
			.passwordEncoder(User.passwordEncoder);

		Admin admin = new Admin();
		admin.setEmail("admin@mypadel.cat");
		admin.setUsername("admin");
		admin.setPassword("password");
		adminRepository.save(admin);

		Player player = new Player();
		player.setEmail("player@mypadel.cat");
		player.setUsername("player");
		player.setPassword("password");
		playerRepository.save(player);
    }
}
