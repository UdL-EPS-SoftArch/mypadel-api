package cat.udl.eps.softarch.mypadel.config;

import cat.udl.eps.softarch.mypadel.utils.mocks.MockMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class MailConfigTest {
	@Bean
	public static MockMailSender getMockMailSender() {
		return new MockMailSender();
	}


}
