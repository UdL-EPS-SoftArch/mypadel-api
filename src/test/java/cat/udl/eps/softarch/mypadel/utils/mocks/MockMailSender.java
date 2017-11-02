package cat.udl.eps.softarch.mypadel.utils.mocks;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class MockMailSender extends JavaMailSenderImpl {
	Logger logger = Logger.getLogger(MockMailSender.class.getName());
	private boolean sent = false;

	@Override
	public void send(final SimpleMailMessage simpleMailMessage) throws MailException {
		sent = true;

	}

	public boolean isSent() {
		return sent;
	}

}
