package cat.udl.eps.softarch.mypadel.utils;

import cat.udl.eps.softarch.mypadel.domain.Match;
import cat.udl.eps.softarch.mypadel.domain.MatchInvitation;
import cat.udl.eps.softarch.mypadel.domain.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class CancellationMailSender {

	private static final Logger logger = LoggerFactory.getLogger(CancellationMailSender.class);

	@Autowired
	@Qualifier(value = "getJavaMailSender")
	private JavaMailSender mailer;

	public void sendCancellationMailsToPlayers(Match match) {
		for (MatchInvitation invitation : match.getInvitations()) {
			Player player = invitation.getInvites();
			sendMailTo(player);
		}
	}

	private void sendMailTo(Player player) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(player.getEmail());
		mailMessage.setSubject("Match cancelled");
		mailMessage.setText("Your match has been cancelled");
		try {
			mailer.send(mailMessage);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
