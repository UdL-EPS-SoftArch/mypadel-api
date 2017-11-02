package cat.udl.eps.softarch.mypadel.steps;

import cat.udl.eps.softarch.mypadel.utils.mocks.MockMailSender;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;

import static cat.udl.eps.softarch.mypadel.steps.AuthenticationStepDefs.authenticate;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CheckAvailableCourtsStepdefs {

	@Autowired
	private StepDefs stepDefs;

	@Autowired
	private MockMailSender mailSender;

	@Then("^The match has been canceled$")
	public void theMatchHasBeenCanceled() throws Throwable {
		stepDefs.result = stepDefs.mockMvc.perform(
			get("/publicMatches/1")
				.accept(MediaType.APPLICATION_JSON)
				.with(authenticate()))
			.andDo(print())
			.andExpect(jsonPath("$.cancelled", is(true)));

	}


	@And("^An email will be sent to the canceled match players with emails: \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
	public void anEmailWillBeSentToTheCanceledMatchPlayersWithEmailsAnd(String player1Mail, String player2Mail,
																		String player3Mail) throws Throwable {
		String from = "admin@mypadel.cat";
		String[] to = new String[]{player1Mail, player2Mail, player3Mail};
		String subject = "Paddle match cancelled";
		String text = "Paddle match cancelled ...";
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(text);
		mailSender.send(simpleMailMessage);
		assertThat(mailSender.isSent(), is(true));
	}
}
