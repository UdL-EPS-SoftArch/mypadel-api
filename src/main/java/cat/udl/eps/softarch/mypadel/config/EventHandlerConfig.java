package cat.udl.eps.softarch.mypadel.config;

import cat.udl.eps.softarch.mypadel.eventlistener.AdminEventListener;
import cat.udl.eps.softarch.mypadel.eventlistener.MatchResultEventListener;
import cat.udl.eps.softarch.mypadel.eventlistener.MatchResultVerificationEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

@Configuration
public class EventHandlerConfig {

	@Bean
	@NotNull
	public AdminEventListener adminEventListener() {
		return new AdminEventListener();
	}

	@Bean
	@NotNull
	public MatchResultEventListener matchResultEventListener() {
		return new MatchResultEventListener();
	}

	@Bean
	@NotNull
	public MatchResultVerificationEventListener matchResultVerificationEventListener() {
		return new MatchResultVerificationEventListener();
	}

}
