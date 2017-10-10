package cat.udl.eps.softarch.mypadel.config;

import cat.udl.eps.softarch.mypadel.domain.*;
import cat.udl.eps.softarch.mypadel.handler.AdminEventHandler;
import cat.udl.eps.softarch.mypadel.handler.MatchResultEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import javax.annotation.PostConstruct;

/**
 * Created by albertberga on 04/05/16.
 */
@Configuration
public class RepositoryRestConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Admin.class);
        config.exposeIdsFor(MatchResult.class);
        config.exposeIdsFor(Player.class);
        config.exposeIdsFor(PublicMatch.class);
        config.exposeIdsFor(Court.class);
        config.exposeIdsFor(JoinMatch.class);
    }

    @PostConstruct
    public void init() {
    }

	@Bean
	public AdminEventHandler adminEventHandler() {
		return new AdminEventHandler();
	}

	@Bean
	public MatchResultEventHandler matchResultEventHandler() {
		return new MatchResultEventHandler();
	}

}
