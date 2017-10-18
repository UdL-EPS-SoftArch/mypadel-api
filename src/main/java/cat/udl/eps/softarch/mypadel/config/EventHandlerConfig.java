package cat.udl.eps.softarch.mypadel.config;

import cat.udl.eps.softarch.mypadel.eventlistener.AdminEventListener;
import cat.udl.eps.softarch.mypadel.eventlistener.MatchResultEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventHandlerConfig {

	@Bean
	public AdminEventListener adminEventListener() {
		return new AdminEventListener();
	}

	@Bean
	public MatchResultEventListener matchResultEventListener() {
		return new MatchResultEventListener();
	}

}
