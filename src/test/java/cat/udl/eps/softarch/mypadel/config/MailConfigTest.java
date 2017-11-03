package cat.udl.eps.softarch.mypadel.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Configuration
@Profile("Test")
public class MailConfigTest {

	private static final Logger logger = LoggerFactory.getLogger(MailConfigTest.class);

	@Bean
	public static JavaMailSender getJavaMailSender() {
		JavaMailSender mailer = mock(JavaMailSender.class);
		doAnswer((invocationOnMock) -> {
			SimpleMailMessage mailMessage = (SimpleMailMessage) invocationOnMock.getArguments()[0];
			logger.info("Mock JavaMailSender sending e-mail: {}", mailMessage);
			return null;
		})
			.when(mailer)
			.send(any(SimpleMailMessage.class));
		return mailer;
	}

}
